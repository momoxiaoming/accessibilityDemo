package com.mmo.accessibilitydemo.script;

import android.content.Intent;
import android.provider.Settings;

import com.auto.assist.accessibility.api.UiApi;
import com.auto.assist.accessibility.util.LogUtil;
import com.mmo.accessibilitydemo.UiApplication;

/**
 * <pre>
 *     author: momoxiaoming
 *     time  : 2019/4/23
 *     desc  : 前往 设置->移动网络界面 脚本
 * </pre>
 */
public class ToNetPageScript
{

    public static void doWrok()
    {
        /*首先让手机回退到桌面,注意:由于每个手机的桌面包名不一致,
        请添加你的测试机桌面包名至com.auto.assist.accessibility.util.Config中,
        否者无法成功回退*/
        UiApi.backToDesk();

        //启动设置应用
        startSetting();

        //
        doAction();

    }

    private static void doAction()
    {
        if (isSettingPage())
        {
            //已进入设置界面
            LogUtil.D("已在设置界面");

            //前往移动网络界面
            if (toNetPage())
            {
                LogUtil.D("前往移动网络页面成功");

            } else
            {
                LogUtil.E("前往移动网络页面异常,暂停操作");

            }


        } else
        {
            LogUtil.E("当前不在设置界面,暂停操作");
        }

    }

    //是否在设置界面
    private static boolean isSettingPage()
    {

        String pageStr = "{"
                + "'maxMustMills':5000,"
                +"'maxOptionMills':5000,"
                + "'must':{'text':['设置'],'id':[],'desc':[]},"
                +"'option':{'text':['更多'],'id':[],'desc':[]}"
                + "}";

        return UiApi.isMyNeedPage(pageStr);

    }
    //前往移动网络页面
    private static boolean toNetPage()
    {
        String temp1 = "{"
                + "'maxWClickMSec':1000,"
                + "'click':{'text':'更多'},"
                + "'page':"
                +"{"
                + "'maxMustMills':5000,"
                + "'maxOptionMills':5000,"
                + "'must':{'text':['更多','设置'],'id':[],'desc':[]},"
                + "'option':{'text':[],'id':[],'desc':[]}"
                +"}"
                +     "}";

        String temp2 = "{"
                + "'maxWClickMSec':1000,"
                + "'click':{'text':'移动网络'},"
                + "'page':"
                +"{"
                + "'maxMustMills':5000,"
                + "'maxOptionMills':5000,"
                + "'must':{'text':['移动网络','更多'],'id':[],'desc':[]},"
                + "'option':{'text':[],'id':[],'desc':[]}"
                +"}"
                +     "}";


        return UiApi.jumpToNeedPage(new String[]{temp1, temp2});

    }

    private static void startSetting()
    {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        UiApplication.context.startActivity(intent);
    }

}
