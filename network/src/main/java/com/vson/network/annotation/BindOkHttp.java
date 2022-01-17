package com.vson.network.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier // 此注解就是为了让 hilt 区分这个注解（做自己的逻辑处理）  自己定义标识，限定符号
@Retention(RetentionPolicy.RUNTIME)
public @interface BindOkHttp {}  // 业主三 的标记
