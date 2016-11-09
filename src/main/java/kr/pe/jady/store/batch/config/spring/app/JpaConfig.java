package kr.pe.jady.store.batch.config.spring.app;

import org.hibernate.jpa.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by jhlee7854 on 2016. 11. 7..
 */
@Configuration
public class JpaConfig {
    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();
        //properties.setProperty(AvailableSettings.SCHEMA_GEN_DATABASE_ACTION, "drop-and-create");
        properties.setProperty(AvailableSettings.SCHEMA_GEN_SCRIPTS_ACTION, "drop-and-create");
        properties.setProperty(AvailableSettings.SCHEMA_GEN_SCRIPTS_DROP_TARGET, "build/getnerated-script/jpa/schema.sql");
        properties.setProperty(AvailableSettings.SCHEMA_GEN_SCRIPTS_CREATE_TARGET, "build/getnerated-script/jpa/schema.sql");
        return properties;
    }

    public static LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanFactory(Properties jpaProperties, DataSource dataSource, String persistenceUnitName) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName(persistenceUnitName);
        factoryBean.setJpaProperties(jpaProperties);
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("kr.pe.jady.store.model");
        factoryBean.setDataSource(dataSource);
        factoryBean.afterPropertiesSet();

        return factoryBean;
    }
}
