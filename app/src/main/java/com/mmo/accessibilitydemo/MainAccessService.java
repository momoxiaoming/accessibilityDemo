package com.mmo.accessibilitydemo;

import android.view.accessibility.AccessibilityEvent;

import com.auto.assist.accessibility.AutoCoreService;

/**
 * <pre>
 *     author: momoxiaoming
 *     time  : 2019/4/13
 *     desc  : 继承module 中的AutoCoreService
 * </pre>
 */
public class MainAccessService extends AutoCoreService
{



    @Override
    public void onAccessEvent(AccessibilityEvent event)
    {
        //如果需要通过监听除模拟点击之外的其他事情,再此写具体逻辑.否者无需任何操作
    }
}
