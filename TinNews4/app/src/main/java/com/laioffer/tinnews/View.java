package com.wayne.tinnews;


public class View {
    private onClickListener onClickListener;

    public interface onClickListener {
        void onClick();
    }


    public void setOnListener(onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    void humanClick() {
        onClickListener.onClick();
    }
}
