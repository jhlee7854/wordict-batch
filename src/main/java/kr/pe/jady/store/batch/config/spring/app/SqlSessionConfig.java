package kr.pe.jady.store.batch.config.spring.app;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

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
