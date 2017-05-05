package com.example.jianqiang.myannotation1.d4;

import android.app.Activity;
import android.view.View;

import com.example.jianqiang.myannotation1.d3.ContentView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class EventUtils {
    public static void injectEvents(Activity activity) {
        Class a = activity.getClass();

        // 得到Activity的所有方法
        Method[] methods = a.getDeclaredMethods();

        for (Method method : methods) {

            // 得到被OnClick注解的方法
            if (method.isAnnotationPresent(OnClick.class)) {

                // 得到该方法的OnClick注解
                OnClick onClick = method.getAnnotation(OnClick.class);

                // 得到OnClick注解的值
                int[] viewIds = onClick.value();

                // 得到OnClick注解上的EventBase注解
                EventBase eventBase = onClick.annotationType().getAnnotation(EventBase.class);
                String listenerSetter = eventBase.listenerSetter();
                Class<?> listenerType = eventBase.listenerType();
                String methodName = eventBase.methodName();

                // 使用动态代理
                DynamicHandler handler = new DynamicHandler(activity);
                Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class<?>[] { listenerType }, handler);
                handler.addMethod(methodName, method);

                // 为注解中声明的每个view设置点击事件
                for (int viewId : viewIds) {
                    try {
                        Method findViewByIdMethod = a.getMethod("findViewById", int.class);
                        findViewByIdMethod.setAccessible(true);
                        View view  = (View) findViewByIdMethod.invoke(activity, viewId);
                        Method setEventListenerMethod = view.getClass().getMethod(listenerSetter, listenerType);
                        setEventListenerMethod.setAccessible(true);
                        setEventListenerMethod.invoke(view, listener);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}