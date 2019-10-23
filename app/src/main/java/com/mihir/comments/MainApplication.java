package com.mihir.comments;

import android.app.Application;

import com.mihir.comments.swipe.SwipeActivityManager;


public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SwipeActivityManager.getInstance().init(this);
    }
}
