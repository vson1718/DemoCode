package com.vson.xutils.annation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnBaseAction {

    /**
     * 设置监听方法名称
     */
    String setNameListener();

    /**
     * 监听对象
     */
    Class setObjectListener();

    /**
     * 监听回调方法名称
     */
    String callBackMethod();

}
