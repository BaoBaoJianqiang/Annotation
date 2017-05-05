package com.example.jianqiang.myannotation1.d1;

import android.view.View;

import com.example.jianqiang.myannotation1.d4.EventBase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@MyEventBase(helloJianqiang = "123456abcd")
public @interface MyMethodAnnotation {
    String hello () default "hello";
    String world();
}