package kr.pe.jady.store.batch.config.spring.sqlsession;

import kr.pe.jady.store.batch.config.spring.app.DataSourceConfig;
import kr.pe.jady.store.batch.config.spring.app.SqlSessionConfig;
import kr.pe.jady.store.batch.system.annotation.SummaryWriteMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * Created by jhlee7854 on 2016. 10. 28..
 */
@Configuration
@Import({DataSourceConfig.class})
public class WriteSummarySqlSessionConfig {
    @Bean
    public SqlSessionFactory writeSummarySqlSessionFactory(DataSource writeSummaryDataSource) throws Exception {
        return SqlSessionConfig.sqlSessionFactoryBeanFactory(writeSummaryDataSource, "**/mapper/summary/*WriteMapper.xml").getObject();
    }

    @Bean
    public SqlSessionTemplate writeSummarySqlSessionTemplate(SqlSessionFactory writeSummarySqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(writeSummarySqlSessionFactory, ExecutorType.BATCH);
    }

    @Bean
    public MapperScannerConfigurer writeSummaryMapperScannerConfigurer() {
        return SqlSessionConfig.mapperScannerConfigurerFactory(SummaryWriteMapper.class, "writeSummarySqlSessionTemplate");
    }
}
