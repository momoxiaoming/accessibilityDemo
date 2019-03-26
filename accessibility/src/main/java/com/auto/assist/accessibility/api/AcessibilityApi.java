package com.auto.assist.accessibility.api;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;


import java.util.ArrayList;
import java.util.List;

/**
 * 辅助功能API
 */
public class AcessibilityApi
{
    public enum ActionType
    {
        BACK,  //返回键
        HOME,  //home
        SETTING,  //设置
        POWER,  //锁屏
        RECENTS,  //应用列表
        NOTIFICATIONS, //通知
        SCROLL_BACKWARD,  //下滑
        SCROLL_FORWARD, //上划

    }


    private static AccessibilityEvent mAccessibilityEvent = null;
    private static AccessibilityService mAccessibilityService = null;


    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        AcessibilityApi.context = context;
    }

    /**
     * 设置数据
     *
     * @param service
     * @param
     */
    public static void setAccessibilityService(AccessibilityService service) {
        synchronized (AcessibilityApi.class)
        {
            if (service != null && mAccessibilityService == null)
            {
                mAccessibilityService = service;
            }

        }

    }

    public static void setAccessibilityEvent(AccessibilityEvent event) {
        synchronized (AcessibilityApi.class)
        {
            if (event != null && mAccessibilityEvent == null)
            {
                mAccessibilityEvent = event;
            }

        }

    }


    /**
     * 模拟点击系统相关操作
     */
    public static void performAction(ActionType action) {
        if (mAccessibilityService == null)
        {
            return;
        }

        try
        {
            Thread.sleep(500);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        switch (action)
        {
            case BACK:
                mAccessibilityService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);

                break;
            case HOME:
                mAccessibilityService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME);

                break;
            case RECENTS:
                mAccessibilityService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);

                break;
            case NOTIFICATIONS:
                mAccessibilityService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_NOTIFICATIONS);

                break;

            case POWER:
                mAccessibilityService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_POWER_DIALOG);

                break;

            case SETTING:
                mAccessibilityService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_QUICK_SETTINGS);


                break;
            case SCROLL_BACKWARD:
                mAccessibilityService.performGlobalAction(AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD);


                break;
            case SCROLL_FORWARD:
                mAccessibilityService.performGlobalAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);


                break;
        }
    }


    //==============================上层api========================================

    /**
     * 根据text查找并点击该节点
     *
     * @param text
     */
    public static boolean clickTextViewByText(String text) {
        boolean flg = false;
        AccessibilityNodeInfo accessibilityNodeInfo = getRootNodeInfo();
        if (accessibilityNodeInfo == null)
        {
            return flg;
        }
        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByText(text);
        if (nodeInfoList != null && !nodeInfoList.isEmpty())
        {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList)
            {
                if (nodeInfo != null)
                {
                    flg = performViewClick(nodeInfo);

                    break;
                }
            }
        }
        return flg;
    }


    /**
     * 根据Id查找并点击该节点
     *
     * @param id
     * @return
     */
    public static boolean clickTextViewByID(String id) {

        boolean flg = false;
        AccessibilityNodeInfo accessibilityNodeInfo = getRootNodeInfo();
        if (accessibilityNodeInfo == null)
        {
            return flg;
        }
        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        if (nodeInfoList != null && !nodeInfoList.isEmpty())
        {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList)
            {
                if (nodeInfo != null)
                {
                    flg = performViewClick(nodeInfo);
                    break;
                }
            }
        }

        return flg;
    }

    public static boolean ScrollNode(AccessibilityNodeInfo nodeInfo) {

        boolean flg = false;
        if (nodeInfo == null)
        {
            return flg;
        }
        while (nodeInfo != null)
        {
            if (nodeInfo.isScrollable())
            {
                flg = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                break;
            }
            nodeInfo = nodeInfo.getParent();


        }

        return flg;
    }


    /**
     * 模拟输入
     *
     * @param nodeInfo nodeInfo
     * @param text     text
     */
    public static boolean inputTextByNode(AccessibilityNodeInfo nodeInfo, String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Bundle arguments = new Bundle();
            arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);

            return nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);

        } else
        {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", text);
            assert clipboard != null;
            clipboard.setPrimaryClip(clip);
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
            return nodeInfo.performAction(AccessibilityNodeInfo.ACTION_PASTE);
        }

    }


    /**
     * 模拟点击某个节点
     *
     * @param nodeInfo nodeInfo
     */
    public static boolean performViewClick(AccessibilityNodeInfo nodeInfo) {
        boolean flg = false;
        if (nodeInfo == null)
        {
            return flg;
        }
        while (nodeInfo != null)
        {
            if (nodeInfo.isClickable())
            {
                flg = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                break;
            }
            nodeInfo = nodeInfo.getParent();


        }
        return flg;
    }




    /**
     * 查找对应文本的View
     *
     * @param text text
     * @return View
     */
    public static AccessibilityNodeInfo findViewByText(String text) {
        AccessibilityNodeInfo accessibilityNodeInfo = getRootNodeInfo();
        if (accessibilityNodeInfo == null)
        {
            return null;
        }
        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByText(text);
        if (nodeInfoList != null && !nodeInfoList.isEmpty())
        {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList)
            {
                if (nodeInfo != null && nodeInfo.getText() != null && nodeInfo.getText().toString().equals(text))
                {
                    return nodeInfo;


                }
            }
        }
        return null;
    }


    /**
     * 查找对应ID的View
     *
     * @param id id
     * @return View
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static AccessibilityNodeInfo findViewByID(String id) {
        AccessibilityNodeInfo accessibilityNodeInfo = getRootNodeInfo();
        if (accessibilityNodeInfo == null)
        {
            return null;
        }
        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        if (nodeInfoList != null && !nodeInfoList.isEmpty())
        {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList)
            {
                if (nodeInfo != null)
                {
                    return nodeInfo;
                }
            }
        }
        return null;
    }

    /**
     * 根据描述查找控件
     *
     * @param des
     * @return
     */
    public static AccessibilityNodeInfo findViewByDes(String des) {
        if (des == null || "".equals(des))
        {
            return null;
        }
        List<AccessibilityNodeInfo> lists = getAllNode(null, null);

        for (AccessibilityNodeInfo node : lists)
        {
            CharSequence desc = node.getContentDescription();
            if (desc != null && des.equals(desc.toString()))
            {
                return node;
            }
        }
        return null;
    }

    /**
     * 根据类名模糊查找控件
     *
     * @param cls
     * @return
     */
    public static List<AccessibilityNodeInfo> findViewByCls(String cls) {
        List<AccessibilityNodeInfo> mlist = new ArrayList<>();
        if (cls == null || "".equals(cls))
        {
            return null;
        }
        List<AccessibilityNodeInfo> lists = getAllNode(null, null);

        for (AccessibilityNodeInfo node : lists)
        {
            CharSequence desc = node.getClassName();
            if (desc != null && cls.equals(desc.toString()))
            {
                mlist.add(node);
            }
        }
        return mlist;
    }

    /**
     * 获取根节点
     *
     * @return
     */
    private static AccessibilityNodeInfo getRootNodeInfo() {
        AccessibilityEvent curEvent = mAccessibilityEvent;
        AccessibilityNodeInfo nodeInfo = null;

        if (Build.VERSION.SDK_INT >= 19)
        {
            // 建议使用getRootInActiveWindow，这样不依赖当前的事件类型
            if (mAccessibilityService != null)
            {
                nodeInfo = mAccessibilityService.getRootInActiveWindow();

            }

        } else
        {
            nodeInfo = curEvent.getSource();
        }


        return nodeInfo;
    }

    /**
     * 根据Text搜索所有符合条件的节点, 模糊搜索方式
     */
    public static List<AccessibilityNodeInfo> findNodesByText(String text) {
        AccessibilityNodeInfo nodeInfo = getRootNodeInfo();
        if (nodeInfo != null)
        {
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(text);
            if (list != null || list.size() > 0)
            {
                return list;

            }
        }
        return null;

    }


    public static String getEventPkg() {
        if (mAccessibilityService != null)
        {
            AccessibilityNodeInfo node = getRootNodeInfo();
            if (node != null)
            {
                return node.getPackageName() == null ? "" : node.getPackageName().toString();

            }
        }
        return "";
    }


    /**
     * 根据Text搜索所有符合条件的节点, 模糊搜索方式
     */
    public static AccessibilityNodeInfo findNodesById(String text) {
        AccessibilityNodeInfo nodeInfo = getRootNodeInfo();
        if (nodeInfo != null)
        {
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(text);
            if (list != null || list.size() > 0)
            {
                return list.get(0);

            }
        }
        return null;

    }


    /**
     * 查找所有节点,比较耗时
     *
     * @param node
     * @param lists
     * @return
     */
    public static List<AccessibilityNodeInfo> getAllNode(AccessibilityNodeInfo node, List<AccessibilityNodeInfo> lists) {
        if (lists == null)
        {
            lists = new ArrayList<>();
        }
        if (node == null)
        {
            node = getRootNodeInfo();

        }

        if (node != null)
        {
            int childNum = node.getChildCount();


            if (childNum > 0)
            {
                for (int i = 0; i < childNum; i++)
                {
                    AccessibilityNodeInfo nodeInfo = node.getChild(i);
                    if (nodeInfo != null)
                    {
                        getAllNode(nodeInfo, lists);
                        lists.add(nodeInfo);

                    }
                }


            } else
            {

                return lists;
            }
        }

        return lists;


    }

    /**
     * 关闭软件盘,需要7.0版本
     */
    public static void closeKeyBoard() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            if (mAccessibilityService != null)
            {

                AccessibilityService.SoftKeyboardController softKeyboardController = mAccessibilityService.getSoftKeyboardController();
                int mode = softKeyboardController.getShowMode();
                if (mode == AccessibilityService.SHOW_MODE_AUTO)
                {
                    //如果软键盘开启,就关闭软件拍
                    softKeyboardController.setShowMode(AccessibilityService.SHOW_MODE_HIDDEN);

                }


            }
        }
    }


    /**
     * 获取节点所有包含的动作类型
     *
     * @param node
     */
    public static void getNodeActions(AccessibilityNodeInfo node) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
        {
            List<AccessibilityNodeInfo.AccessibilityAction> accessibilityActions = node.getActionList();

            for (AccessibilityNodeInfo.AccessibilityAction action : accessibilityActions)
            {

                System.out.print(action.toString());
            }
        }
    }

}
