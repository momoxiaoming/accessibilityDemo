package com.auto.assist.accessibility;

import com.auto.assist.accessibility.api.UiApi;

public class demo
{

    /*****************多个页面点击*******************/
//前往设置->移动网络页面
public void test(){
        String temp1 = "{" +
                "'maxWClickMSec':1000," +
                "'click':{'text':'更多'}," +
                "'page':{" +
                "'maxMustMills':5000," +
                "'maxOptionMills':5000," +
                "'must':{'text':['更多','设置'],'id':[],'des':[]}" +
                "'option':{'text':[],'id':[],'des':[]}"+
                "}}";

        String temp2 = "{" +
                "'maxWClickMSec':1000," +
                "'click':{'text':'移动网络'}," +
                "'page':{" +
                "'maxMustMills':5000," +
                "'maxOptionMills':5000," +
                "'must':{'text':['移动网络'],'id':[],'des':[]}" +
                "'option':{'text':['VPN','设置'],'id':[],'des':[]}"+
                "}}";




             UiApi.jumpToNeedPage(new String[]{temp1,temp2});

}


    /**************************单个页面判断****************/

//判断是否在设置界面
    public void test1()
    {
        String pageStr = "{" +
                "'maxMustMills':3000," +
                "'maxOptionMills':3000," +
                "'must':{'text':['设置'],'id':[],'des':[]}" +
                "'option':{'text':['设置'],'id':[],'des':[]}"+
                "}";
        UiApi.isMyNeedPage(pageStr);

    }

}
