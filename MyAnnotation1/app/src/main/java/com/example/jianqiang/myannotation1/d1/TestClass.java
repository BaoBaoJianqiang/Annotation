package com.example.jianqiang.myannotation1.d1;

import android.util.Log;

@MyClassAnnotation("abcd")
public class TestClass {
    @MyMethodAnnotation(hello = "Hello,Beijing",world = "Hello,world")
    public void output() {
        Log.v("baobao", "method output is running ");
    }



    @FruitName("Apple666666~~~")
    private String appleName;

    @FruitColor(fruitColor= FruitColor.Color.RED)
    private String appleColor;


    public TestClass(String appleName, String appleColor) {
        this.appleColor = appleColor;
        this.appleName = appleName;
    }
}
