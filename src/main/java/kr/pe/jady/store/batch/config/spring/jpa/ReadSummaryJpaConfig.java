package kr.pe.jady.store.batch.config.spring.jpa;

import kr.pe.jady.store.batch.config.spring.app.DataSourceConfig;
import kr.pe.jady.store.batch.config.spring.app.JpaConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by jhlee7854 on 2016. 11. 7..
 */
@Configuration
@EnableJpaRepositories(basePackages = {"kr.pe.jady.store.batch.web"}, includeFilters = @ComponentScan.Filter(value = {Repository.class}), entityManagerFactoryRef = "readSummaryEntityManagerFactory", transactionManagerRef = "readSummaryTransactionManager")
@Import({DataSourceConfig.class, JpaConfig.class})
public class ReadSummaryJpaConfig {
    @Bean
    public EntityManagerFactory readSummaryEntityManagerFactory(Properties jpaProperties, DataSource readSummaryDataSource) {
        return JpaConfig.entityManagerFactoryBeanFactory(jpaProperties, readSummaryDataSource, "readSummary").getObject();
    }

    @Bean
    public PlatformTransactionManager readSummaryTransactionManager(EntityManagerFactory readSummaryEntityManagerFactory) {
        return new JpaTransactionManager(readSummaryEntityManagerFactory);
    }
}
