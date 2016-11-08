package kr.pe.jady.store.batch.config.spring.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

/**
 * Created by jhlee7854 on 2016. 10. 28..
 */
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource batchDataSource() {
        JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
        jndiDataSourceLookup.setResourceRef(true);
        return jndiDataSourceLookup.getDataSource("jdbc/batch");
    }

    @Bean
    public DataSource readLogDataSource() {
        JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
        jndiDataSourceLookup.setResourceRef(true);
        return jndiDataSourceLookup.getDataSource("jdbc/readLog");
    }

    @Bean
    public DataSource writeLogDataSource() {
        JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
        jndiDataSourceLookup.setResourceRef(true);
        return jndiDataSourceLookup.getDataSource("jdbc/writeLog");
    }

    @Bean
    public DataSource readSummaryDataSource() {
        JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
        jndiDataSourceLookup.setResourceRef(true);
        return jndiDataSourceLookup.getDataSource("jdbc/readSummary");
    }

    @Bean
    public DataSource writeSummaryDataSource() {
        JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
        jndiDataSourceLookup.setResourceRef(true);
        return jndiDataSourceLookup.getDataSource("jdbc/writeSummary");
    }
}
