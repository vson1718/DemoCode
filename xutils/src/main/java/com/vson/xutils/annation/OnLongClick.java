package com.vson.xutils.annation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnBaseAction(
        setNameListener = "setOnLongClickListener",
        setObjectListener = View.OnLongClickListener.class,
        callBackMethod = "onClick")
public @interface OnLongClick {
    int[] value();
}
