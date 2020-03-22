package com.example.demo_sanm.interfacesvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/22.
 * @by: DELL)
 *
 *
 * 用于配置实体类字段说明信息
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnConfig {

    /**
     * 字段的中文说明
     * @return
     */
    String descriptionyun() default "";
}
