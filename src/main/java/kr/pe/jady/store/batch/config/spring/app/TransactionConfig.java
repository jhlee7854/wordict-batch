package kr.pe.jady.store.batch.config.spring.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by jhlee7854 on 2016. 11. 7..
 */
@Configuration
@EnableTransactionManagement
@Import({DataSourceConfig.class})
public class TransactionConfig {
    @Bean
    public DataSourceTransactionManager batchTransactionManager(DataSource batchDataSource) {
        return new DataSourceTransactionManager(batchDataSource);
    }

    @Bean
    public DataSourceTransactionManager readLogTransactionManager(DataSource readLogDataSource) {
        return new DataSourceTransactionManager(readLogDataSource);
    }

    @Bean
    public DataSourceTransactionManager writeLogTransactionManager(DataSource writeLogDataSource) {
        return new DataSourceTransactionManager(writeLogDataSource);
    }

    @Bean
    public DataSourceTransactionManager readSummaryTransactionManager(DataSource readSummaryDataSource) {
        return new DataSourceTransactionManager(readSummaryDataSource);
    }

    @Bean
    public DataSourceTransactionManager writeSummaryTransactionManager(DataSource writeSummaryDataSource) {
        return new DataSourceTransactionManager(writeSummaryDataSource);
    }
}
