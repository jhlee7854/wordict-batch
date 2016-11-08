package kr.pe.jady.store.batch.config.spring.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by jhlee7854 on 2016. 11. 7..
 */
@Configuration
public class DataSourceTestConfig {
    @Bean(destroyMethod = "shutdown")
    public DataSource embeddedDataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder
                .setType(EmbeddedDatabaseType.H2)
                .addScripts(
                        "org/springframework/batch/core/schema-drop-h2.sql",
                        "org/springframework/batch/core/schema-h2.sql",
                        "kr/pe/jady/store/schema/schema-book.sql")
                .build();
    }
}
