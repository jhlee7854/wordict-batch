package kr.pe.jady.store.batch.config.spring.sqlsession;

import kr.pe.jady.store.batch.config.spring.app.DataSourceConfig;
import kr.pe.jady.store.batch.config.spring.app.SqlSessionConfig;
import kr.pe.jady.store.batch.system.annotation.LogReadMapper;
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
public class ReadLogSqlSessionConfig {
    @Bean
    public SqlSessionFactory readLogSqlSessionFactory(DataSource readLogDataSource) throws Exception {
        return SqlSessionConfig.sqlSessionFactoryBeanFactory(readLogDataSource, "**/mapper/log/*ReadMapper.xml").getObject();
    }

    @Bean
    public SqlSessionTemplate readLogSqlSessionTemplate(SqlSessionFactory readLogSqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(readLogSqlSessionFactory, ExecutorType.BATCH);
        return sqlSessionTemplate;
    }

    @Bean
    public MapperScannerConfigurer readLogMapperScannerConfigurer() {
        return SqlSessionConfig.mapperScannerConfigurerFactory(LogReadMapper.class, "readLogSqlSessionTemplate");
    }
}
