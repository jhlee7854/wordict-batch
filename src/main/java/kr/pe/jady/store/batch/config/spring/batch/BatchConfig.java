package kr.pe.jady.store.batch.config.spring.batch;

import kr.pe.jady.store.batch.config.spring.app.DataSourceConfig;
import kr.pe.jady.store.batch.config.spring.app.TransactionConfig;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by jhlee7854 on 2016. 11. 3..
 */
@Configuration
@EnableBatchProcessing
@Import({DataSourceConfig.class, TransactionConfig.class})
public class BatchConfig implements BatchConfigurer {
    @Autowired
    private DataSource batchDataSource;
    @Autowired
    private  DataSourceTransactionManager batchTransactionManager;

    @Override
    public JobRepository getJobRepository() throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(batchDataSource);
        jobRepositoryFactoryBean.setTransactionManager(getTransactionManager());
        jobRepositoryFactoryBean.afterPropertiesSet();
        return jobRepositoryFactoryBean.getObject();
    }

    @Override
    public PlatformTransactionManager getTransactionManager() throws Exception {
        return batchTransactionManager;
    }

    @Override
    public JobLauncher getJobLauncher() throws Exception {SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(getJobRepository());
        simpleJobLauncher.setTaskExecutor(taskExecutor());
        simpleJobLauncher.afterPropertiesSet();
        return simpleJobLauncher;
    }

    @Override
    public JobExplorer getJobExplorer() throws Exception {
        JobExplorerFactoryBean jobExplorer = new JobExplorerFactoryBean();
        jobExplorer.setDataSource(batchDataSource);
        jobExplorer.afterPropertiesSet();
        return jobExplorer.getObject();
    }

    @Bean(name = "simpleJobOperator")
    public JobOperator getJobOperator(JobRegistry jobRegistry, JobExplorer jobExplorer) throws Exception {
        SimpleJobOperator simpleJobOperator = new SimpleJobOperator();
        simpleJobOperator.setJobRegistry(jobRegistry);
        simpleJobOperator.setJobRepository(getJobRepository());
        simpleJobOperator.setJobExplorer(jobExplorer);
        simpleJobOperator.setJobLauncher(getJobLauncher());
        return simpleJobOperator;
    }

    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
        JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
        jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
        return jobRegistryBeanPostProcessor;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SyncTaskExecutor();
    }
}
