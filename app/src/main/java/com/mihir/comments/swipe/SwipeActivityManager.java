package com.mihir.comments.swipe;


import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.Stack;

public class SwipeActivityManager extends ActivityLifecycleCallbacksAdapter {
    private static final SwipeActivityManager instance = new SwipeActivityManager();
    private Stack<Activity> mActivityStack = new Stack<>();

    private SwipeActivityManager() {
    }

    public static SwipeActivityManager getInstance() {
        return instance;
    }

    public void init(Application mApplication) {
        mApplication.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mActivityStack.add(activity);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivityStack.remove(activity);
    }

    public Activity getPenultimateActivity() {
        return mActivityStack.size() >= 2 ? mActivityStack.get(mActivityStack.size() - 2) : null;
    }

}
