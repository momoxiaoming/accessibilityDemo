package com.auto.assist.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

import com.auto.assist.accessibility.api.AcessibilityApi;


public abstract class AutoCoreService extends AccessibilityService
{


    @Override
    public void onCreate() {
        super.onCreate();
        AcessibilityApi.setAccessibilityService(this);

    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        AcessibilityApi.setAccessibilityEvent(event);
        onAccessEvent(event);
    }

    @Override
    public void onInterrupt()
    {

    }

    public  abstract void  onAccessEvent(AccessibilityEvent event);


}
