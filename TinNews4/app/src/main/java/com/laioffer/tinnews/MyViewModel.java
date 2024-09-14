package com.wayne.tinnews;

import java.util.Random;

public class MyViewModel {

    private int a = 0;

    MyViewModel() {
        setA(new Random().nextInt());
    }
    public void setA(int a) {
        this.a = a;
    }
}
