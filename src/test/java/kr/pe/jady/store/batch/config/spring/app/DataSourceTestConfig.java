package kr.pe.jady.store.batch.config.spring.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import javax.naming.NamingException;
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

    public static void buildNamingContext() throws NamingException {
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("jdbc/batch", new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa", ""));
        SimpleNamingContextBuilder.getCurrentContextBuilder().bind("jdbc/readLog", new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa", ""));
        SimpleNamingContextBuilder.getCurrentContextBuilder().bind("jdbc/writeLog", new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa", ""));
        SimpleNamingContextBuilder.getCurrentContextBuilder().bind("jdbc/readSummary", new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa", ""));
        SimpleNamingContextBuilder.getCurrentContextBuilder().bind("jdbc/writeSummary", new DriverManagerDataSource("jdbc:h2:mem:testdb", "sa", ""));
    }
}
