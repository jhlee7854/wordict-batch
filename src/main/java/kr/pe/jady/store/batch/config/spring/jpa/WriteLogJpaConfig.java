package kr.pe.jady.store.batch.config.spring.jpa;

import kr.pe.jady.store.batch.config.spring.app.DataSourceConfig;
import kr.pe.jady.store.batch.config.spring.app.JpaConfig;
import org.hibernate.jpa.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by jhlee7854 on 2016. 11. 7..
 */
@Configuration
@EnableJpaRepositories(basePackages = {"kr.pe.jady.store.batch.web"}, includeFilters = @ComponentScan.Filter(value = {Repository.class}), entityManagerFactoryRef = "writeLogEntityManagerFactory", transactionManagerRef = "writeLogTransactionManager")
@Import({DataSourceConfig.class, JpaConfig.class})
public class WriteLogJpaConfig {
    @Bean
    public EntityManagerFactory writeLogEntityManagerFactory(Properties jpaProperties, DataSource writeLogDataSource) {
        return JpaConfig.entityManagerFactoryBeanFactory(jpaProperties, writeLogDataSource).getObject();
    }

    @Bean
    public PlatformTransactionManager writeLogTransactionManager(EntityManagerFactory writeLogEntityManagerFactory) {
        return new JpaTransactionManager(writeLogEntityManagerFactory);
    }
}
