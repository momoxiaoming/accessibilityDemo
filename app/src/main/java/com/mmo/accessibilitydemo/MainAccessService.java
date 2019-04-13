package com.mmo.accessibilitydemo;

import android.view.accessibility.AccessibilityEvent;

import com.auto.assist.accessibility.AutoCoreService;

/**
 * <pre>
 *     author: momoxiaoming
 *     time  : 2019/4/13
 *     desc  : new class
 * </pre>
 */
public class MainAccessService extends AutoCoreService
{



    @Override
    public void onAccessEvent(AccessibilityEvent event)
    {
        //实现自己的逻辑
    }
}
