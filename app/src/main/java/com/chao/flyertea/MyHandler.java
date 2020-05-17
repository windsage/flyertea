package com.chao.flyertea;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class MyHandler extends Handler {
    private final WeakReference<Activity> mActivityReference;

    public MyHandler(Activity activity) {
        this.mActivityReference = new WeakReference<Activity>(activity);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        MainActivity activity = (MainActivity) mActivityReference.get();
        SharedPreferences sp = activity.getSharedPreferences("flyertea", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        switch (msg.what) {
            case 1:
                activity.successInfo.setText("登录成功！");
                break;
            case 2:
                int count = (int) msg.obj;
                activity.successInfo.setText("已回复" + count + "条");
                editor.putLong("lastRequest", System.currentTimeMillis());
                editor.apply();
                break;
            case 3:
                String tips = (String) msg.obj;
                activity.errorInfo.setText(tips);
                editor.putLong("lastRequest", System.currentTimeMillis());
                editor.apply();
                break;
        }

    }
}
