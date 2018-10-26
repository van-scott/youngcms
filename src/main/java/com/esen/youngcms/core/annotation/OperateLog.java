package com.esen.youngcms.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {
 
    /**
     * 模块名称
     * @return
     */
    String moudleName() default "";
    
    /**
     * 操作名称
     * @return
     */
    String optName() default "";
 
    /**
     * 业务类型描述
     * @return
     */
    String description() default "";

}
