package com.jxnu.hotfixdemo;

import android.app.Application;
import android.content.Context;

import com.jxnu.hotfixlib.HotFix;

import java.io.File;

/**
 * Created by DaiQing.Zha on 2017/5/9 0009.
 */
public class HotFixApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        File dexPath = new File(getDir("dex", Context.MODE_PRIVATE), "hack_dex.jar");
        Utils.prepareDex(this.getApplicationContext(), dexPath, "hack_dex.jar");
        HotFix.patch(this, dexPath.getAbsolutePath(), "com.jxnu.hackdex.AntiLazyLoad");
        try {
            this.getClassLoader().loadClass("com.jxnu.hackdex.AntiLazyLoad");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
