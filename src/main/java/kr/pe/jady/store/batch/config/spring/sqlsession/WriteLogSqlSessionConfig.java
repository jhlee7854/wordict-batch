package kr.pe.jady.store.batch.config.spring.sqlsession;

import kr.pe.jady.store.batch.config.spring.app.DataSourceConfig;
import kr.pe.jady.store.batch.config.spring.app.SqlSessionConfig;
import kr.pe.jady.store.batch.system.annotation.LogReadMapper;
import kr.pe.jady.store.batch.system.annotation.LogWriteMapper;
import kr.pe.jady.store.batch.system.annotation.SummaryReadMapper;
import kr.pe.jady.store.batch.system.annotation.SummaryWriteMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by jhlee7854 on 2016. 10. 28..
 */
@Configuration
@Import({DataSourceConfig.class})
public class WriteLogSqlSessionConfig {
    @Bean
    public SqlSessionFactory writeLogSqlSessionFactory(DataSource writeLogDataSource) throws Exception {
        return SqlSessionConfig.sqlSessionFactoryBeanFactory(writeLogDataSource, "**/mapper/log/*WriteMapper.xml").getObject();
    }

    @Bean
    public SqlSessionTemplate writeLogSqlSessionTemplate(SqlSessionFactory writeLogSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(writeLogSqlSessionFactory, ExecutorType.BATCH);
    }

    @Bean
    public MapperScannerConfigurer writeLogMapperScannerConfigurer() {
        return SqlSessionConfig.mapperScannerConfigurerFactory(LogWriteMapper.class, "writeLogSqlSessionTemplate");
    }
}
