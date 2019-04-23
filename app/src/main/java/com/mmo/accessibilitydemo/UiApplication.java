package com.mmo.accessibilitydemo;

import android.app.Application;
import android.content.Context;

/**
 * <pre>
 *     author: momoxiaoming
 *     time  : 2019/4/23
 *     desc  : new class
 * </pre>
 */
public class UiApplication extends Application
{
    public static Context context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        if(context==null){
            context=this;
        }
    }
}
