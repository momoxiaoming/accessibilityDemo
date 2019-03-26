package com.auto.assist.accessibility;

public class demo
{

    /*****************多个页面点击*******************/

//    String mainPageStr = "{" +
//            "'maxWClickMSec':1000," +
//            "'click':{'text':'我'}," +
//            "'page':{" +
//            "'maxMustMills':5000," +
//            "'maxOptionMills':5000," +
//            "'must':{'text':['微信','通讯录']}" +
//            "}}";
//
//
//    String mePageStr = "{" +
//            "'maxWClickMSec':1000," +
//            "'click':{'text':'钱包'}," +
//            "'page':{" +
//            "'maxMustMills':3000," +
//            "'maxOptionMills':3000," +
//            "'must':{'text':['钱包','相册']}" +
//            "}}";
//
//    String payMentStr = "{" +
//            "'maxWClickMSec':1000," +
//            "'click':{'text':'收付款'}," +
//            "'page':{" +
//            "'maxMustMills':3000," +
//            "'maxOptionMills':3000," +
//            "'must':{'text':['收付款','零钱']}" +
//            "}}";
//
//    String payPageStr = "{" +
//            "'maxWClickMSec':1000," +
//            "'click':{'text':'二维码收款'}," +
//            "'page':{" +
//            "'maxMustMills':3000," +
//            "'maxOptionMills':3000," +
//            "'must':{'text':['二维码收款']}" +
//            "}}";
//
//
//    ActionSelector mainPage = new Gson().fromJson(mainPageStr, ActionSelector.class);
//    ActionSelector mePage = new Gson().fromJson(mePageStr, ActionSelector.class);
//    ActionSelector payMent = new Gson().fromJson(payMentStr, ActionSelector.class);
//    ActionSelector payPage = new Gson().fromJson(payPageStr, ActionSelector.class);
//
//    List<ActionSelector> lists = new ArrayList<>();
//        lists.add(mainPage);
//        lists.add(mePage);
//        lists.add(payMent);
//        lists.add(payPage);
//
//
//         UiApi.jumpToNeedPage(lists);




    /**************************单个页面判断****************/

//    String codePageStr = "{" +
//            "'maxMustMills':3000," +
//            "'maxOptionMills':3000," +
//            "'must':{'text':['清除金额','保存收款码']}" +
//            "}";
//
//    NodeSelector mainPage = new Gson().fromJson(codePageStr, NodeSelector.class);
//         UiApi.isMyNeedPage(mainPage);
}
