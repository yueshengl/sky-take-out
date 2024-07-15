package com.sky.annotation;


import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Dai
 * @Date: 2024/07/15 19:09
 * @Description: AutoFill 自定义注解，用于标识某个方法需要进行公共字段自动填充处理
 * @Version: 1.0
 */
@Target(ElementType.METHOD)  //指定注解只能作用于方法上
@Retention(RetentionPolicy.RUNTIME)  //指定注解作用的时间
public @interface AutoFill {
    //数据库操作类型，UPDATE, INSERT
    OperationType value();
}
