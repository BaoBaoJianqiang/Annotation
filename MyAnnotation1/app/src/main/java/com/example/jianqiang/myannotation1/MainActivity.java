package com.example.jianqiang.myannotation1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jianqiang.myannotation1.d1.FruitName;
import com.example.jianqiang.myannotation1.d1.MyClassAnnotation;
import com.example.jianqiang.myannotation1.d1.MyEventBase;
import com.example.jianqiang.myannotation1.d1.MyMethodAnnotation;
import com.example.jianqiang.myannotation1.d1.TestClass;
import com.example.jianqiang.myannotation1.d2.AnnotateUtils;
import com.example.jianqiang.myannotation1.d2.ViewInject;
import com.example.jianqiang.myannotation1.d3.ContentView;
import com.example.jianqiang.myannotation1.d3.ViewUtils;
import com.example.jianqiang.myannotation1.d4.EventUtils;
import com.example.jianqiang.myannotation1.d4.OnClick;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@ContentView(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewInject(R.id.buy)
    private Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Demo1
        getMetaFromClass();

        //Demo2
        try {
            getMetaFromMethod();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //Demo3
        try {
            getMetaFromField();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        //Demo4
        //Demo5
        //Demo6
        ViewUtils.injectContentView(this);
        AnnotateUtils.injectViews(this);
        EventUtils.injectEvents(this);
    }

    @OnClick({R.id.buy})
    public void clickBtnInvoked(View view) {
        switch (view.getId()) {
            case R.id.buy:
                buy.setText("baobao");
                break;
        }
    }

    void getMetaFromClass() {
        Class<TestClass> myTestClass = TestClass.class;
        if(myTestClass.isAnnotationPresent(MyClassAnnotation.class)) {
            MyClassAnnotation annotation = myTestClass.getAnnotation(MyClassAnnotation.class);
            Log.v("jianqiang Class", annotation.value());
        }
    }

    void getMetaFromMethod() throws NoSuchMethodException {
        Class<TestClass> myTestClass = TestClass.class;

        Method method = myTestClass.getMethod("output", new Class[]{});

        if(method.isAnnotationPresent(MyMethodAnnotation.class)) {
            MyMethodAnnotation annotation = method.getAnnotation(MyMethodAnnotation.class);
            Log.v("jianqiang Class", annotation.hello());
            Log.v("jianqiang Class", annotation.world());

            //获取注解上的注解MyEventBase
            MyEventBase myEventBase = annotation.annotationType().getAnnotation(MyEventBase.class);
            Log.v("jianqiang Class2222", myEventBase.helloJianqiang());

        }

        // 获得所有注解。必须是runtime类型的
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations)
        {
            // 遍历所有注解的名字
            Log.v("jianqiang Class", annotation.annotationType().getName());
        }
    }

    void getMetaFromField() throws NoSuchFieldException {
        Class<TestClass> myTestClass = TestClass.class;

        Field field = myTestClass.getDeclaredField("appleName");

        if(field.isAnnotationPresent(FruitName.class)) {
            FruitName annotation = field.getAnnotation(FruitName.class);
            Log.v("jianqiang Class", annotation.value());
        }
    }
}
