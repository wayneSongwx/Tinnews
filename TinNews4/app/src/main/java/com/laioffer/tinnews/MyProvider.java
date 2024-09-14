package com.wayne.tinnews;

import java.util.Random;

public class MyProvider {


    public MyViewModel get() {
        MyViewModel result = new MyViewModel();
        result.setA(new Random().nextInt());
        return result;
    }
}
