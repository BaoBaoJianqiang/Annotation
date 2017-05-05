package com.example.jianqiang.myannotation1.d3;

import android.app.Activity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ViewUtils {
    public static void injectContentView(Activity activity) {
        Class a = activity.getClass();
        if (a.isAnnotationPresent(ContentView.class)) {
            // 得到activity这个类的ContentView注解
            ContentView contentView = (ContentView) a.getAnnotation(ContentView.class);
            // 得到注解的值
            int layoutId = contentView.value();
            // 使用反射调用setContentView
            try {
                Method method = a.getMethod("setContentView", int.class);
                method.setAccessible(true);
                method.invoke(activity, layoutId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}