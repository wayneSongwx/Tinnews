package com.wayne.tinnews.test;

import com.wayne.tinnews.View;

public class Activity implements View.onClickListener {
    private int a;

    void onCreate() {
        View view = new View();
        setContent(view);
        view.setOnListener(new View.onClickListener() {
            @Override
            public void onClick() {
                a++;
            }
        });
        view.setOnListener(this);
    }

    private void setContent(View view) {
        //attach screen and display;
    }

    void main() {
        Activity activity = new Activity();
        activity.onCreate();
    }

    public void onViewClicked() {

    }

    @Override
    public void onClick() {

    }
}
