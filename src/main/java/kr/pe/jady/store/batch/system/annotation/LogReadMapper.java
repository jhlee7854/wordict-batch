package kr.pe.jady.store.batch.system.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by jhlee7854 on 2016. 10. 28..
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface LogReadMapper {
}
