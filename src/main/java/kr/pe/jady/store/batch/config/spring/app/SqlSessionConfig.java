package kr.pe.jady.store.batch.config.spring.app;

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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by jhlee7854 on 2016. 10. 28..
 */
public class SqlSessionConfig {
    public static SqlSessionFactoryBean sqlSessionFactoryBeanFactory(DataSource dataSource, String mapperLocation) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        sqlSessionFactoryBean.setTypeAliasesPackage("kr.pe.jady.store.model");
        sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("kr/pe/jady/store/batch/config/mybatis/MyBatisConfig.xml"));
        return sqlSessionFactoryBean;
    }

    public static MapperScannerConfigurer mapperScannerConfigurerFactory(Class annotationClass, String SqlSessionTemplateBeanName) {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("kr.pe.jady.store.batch.job.mapper");
        mapperScannerConfigurer.setAnnotationClass(annotationClass);
        mapperScannerConfigurer.setSqlSessionTemplateBeanName(SqlSessionTemplateBeanName);
        return mapperScannerConfigurer;
    }
}
