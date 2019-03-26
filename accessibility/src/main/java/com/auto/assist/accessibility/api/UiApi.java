package com.auto.assist.accessibility.api;

import android.view.accessibility.AccessibilityNodeInfo;

import com.auto.assist.accessibility.selector.ActionSelector;
import com.auto.assist.accessibility.selector.NodeSelector;
import com.auto.assist.accessibility.util.ApiUtil;
import com.auto.assist.accessibility.util.Config;
import com.auto.assist.accessibility.util.LogUtil;

import java.util.HashMap;
import java.util.List;

/**
 * 上层api
 */
public class UiApi
{
    private static final int WAIT_UI_APPEAR_MSEC = 500;  //控件默认的超时时间,毫秒

    private static final int CHECK_UI_SLEEP_GAP_MSEC = 200;  //控件默认的超时时间,毫秒


    /**
     * 通过text获取节点,直到超时
     *
     */
    public static AccessibilityNodeInfo findNodeByTextWithTimeOut(long maxMills, String text) {
        AccessibilityNodeInfo mNode = null;

        long beginUtcMsc = System.currentTimeMillis(); //记录当前开始时间
        long curUtcMsc; //当前时间

        if (maxMills == 0 || maxMills < 0)
        {
            maxMills = WAIT_UI_APPEAR_MSEC;
        }

        while (true)
        {
            mNode = AcessibilityApi.findViewByText(text);
            if (isExists(mNode))
            {

                break;
            } else
            {
                curUtcMsc = System.currentTimeMillis();

                if ((curUtcMsc - beginUtcMsc) < maxMills)
                {
                    ApiUtil.sleepTime(CHECK_UI_SLEEP_GAP_MSEC);

                } else
                {
                    break;
                }
            }
        }
        return mNode;
    }


    /**
     * 通过text获取节点,直到超时
     */
    public static AccessibilityNodeInfo findNodeByIdWithTimeOut(long maxMills, String id) {
        AccessibilityNodeInfo mNode;

        long beginUtcMsc = System.currentTimeMillis(); //记录当前开始时间
        long curUtcMsc; //当前时间

        if (maxMills == 0 || maxMills < 0)
        {
            maxMills = WAIT_UI_APPEAR_MSEC;
        }

        while (true)
        {
            mNode = AcessibilityApi.findViewByID(id);
            if (isExists(mNode))
            {
                break;
            } else
            {
                curUtcMsc = System.currentTimeMillis();

                if ((curUtcMsc - beginUtcMsc) < maxMills)
                {
                    ApiUtil.sleepTime(CHECK_UI_SLEEP_GAP_MSEC);
                } else
                {
                    break;
                }
            }
        }
        return mNode;
    }

    /**
     * 根据描述查找控件,含超时时间
     */
    public static AccessibilityNodeInfo findNodeByDesWithTimeOut(long maxMills, String des) {
        AccessibilityNodeInfo mNode;

        long beginUtcMsc = System.currentTimeMillis(); //记录当前开始时间
        long curUtcMsc; //当前时间

        if (maxMills == 0 || maxMills < 0)
        {
            maxMills = WAIT_UI_APPEAR_MSEC;
        }

        while (true)
        {
            mNode = AcessibilityApi.findViewByDes(des);
            if (isExists(mNode))
            {
                break;
            } else
            {
                curUtcMsc = System.currentTimeMillis();

                if ((curUtcMsc - beginUtcMsc) < maxMills)
                {
                    ApiUtil.sleepTime(CHECK_UI_SLEEP_GAP_MSEC);
                } else
                {
                    break;
                }
            }
        }
        return mNode;
    }


    /**
     * 根据类名查找控件,含超时时间

     */
    public static AccessibilityNodeInfo findNodeByClsWithTimeOut(long maxMills, String cls) {
        AccessibilityNodeInfo mNode = null;

        long beginUtcMsc = System.currentTimeMillis(); //记录当前开始时间
        long curUtcMsc; //当前时间

        if (maxMills == 0 || maxMills < 0)
        {
            maxMills = WAIT_UI_APPEAR_MSEC;
        }

        while (true)
        {
            List<AccessibilityNodeInfo> lists = AcessibilityApi.findViewByCls(cls);

            if (lists != null && lists.size() != 0)
            {
                mNode = lists.get(0);
                break;
            } else
            {
                curUtcMsc = System.currentTimeMillis();

                if ((curUtcMsc - beginUtcMsc) < maxMills)
                {
                    ApiUtil.sleepTime(CHECK_UI_SLEEP_GAP_MSEC);
                } else
                {
                    break;
                }
            }
        }
        return mNode;
    }

    /**
     * 按条件查找节点,并返回text中的字符串
     *
     */
    public static String getTextByNode(long maxMustMills, HashMap<String, String> map) {
        HashMap<String, String[]> map2 = new HashMap<>();

        for (String item : map.keySet())
        {
            String vlue = map.get(item);
            map2.put(item, new String[]{vlue});

        }
        AccessibilityNodeInfo nodeInfo = findOptionNodeWithTimeOut(maxMustMills, map2);


        if (nodeInfo != null && nodeInfo.getText() != null)
        {

            return nodeInfo.getText().toString();
        }
        return "";
    }

    /**
     * 通过条件精确查找,获取节点上的描述信息
     *
     * @param maxMustMills 超时时间
     * @param map 查找条件
     */
    public static String getDescByNode(long maxMustMills, HashMap<String, String> map) {

        HashMap<String, String[]> map2 = new HashMap<>();

        for (String item : map.keySet())
        {
            String vlue = map.get(item);
            map2.put(item, new String[]{vlue});

        }
        AccessibilityNodeInfo nodeInfo = findOptionNodeWithTimeOut(maxMustMills, map2);

        if (nodeInfo != null && nodeInfo.getContentDescription() != null)
        {

            return nodeInfo.getContentDescription().toString();
        }
        return "";
    }


    /**
     * 点击text节点,直到超时
     *
     * @param maxMills
     * @param text
     * @return
     */
    public static boolean clickNodeByTextWithTimeOut(long maxMills, String text) {

        boolean isClick;

        AccessibilityNodeInfo node = findNodeByTextWithTimeOut(maxMills, text);

        if (node == null) return false;
        long beginUtcMsc = System.currentTimeMillis(); //记录当前开始时间
        long curUtcMsc; //当前时间
        if (maxMills == 0 || maxMills < 0)
        {
            maxMills = WAIT_UI_APPEAR_MSEC;
        }

        while (true)
        {
            isClick = AcessibilityApi.performViewClick(node);
            if (isClick)
            {
                break;
            } else
            {
                curUtcMsc = System.currentTimeMillis();

                if ((curUtcMsc - beginUtcMsc) < maxMills)
                {
                    ApiUtil.sleepTime(500);
                    LogUtil.D("尝试失败,再次尝试点击");
                } else
                {
                    break;
                }
            }
        }

        return isClick;
    }

    /**
     * 点击id节点直到超时
     *
     * @param maxMills 超时时间
     * @param id       id查找
     * @return
     */
    public static boolean clickNodeByIdWithTimeOut(long maxMills, String id) {

        boolean isClick = false;

        AccessibilityNodeInfo node = findNodeByIdWithTimeOut(maxMills, id);

        if (node == null) return false;
        long beginUtcMsc = System.currentTimeMillis(); //记录当前开始时间
        long curUtcMsc; //当前时间
        if (maxMills == 0 || maxMills < 0)
        {
            maxMills = WAIT_UI_APPEAR_MSEC;
        }

        while (true)
        {
            isClick = AcessibilityApi.performViewClick(node);
            if (isClick)
            {
                break;
            } else
            {
                curUtcMsc = System.currentTimeMillis();

                if ((curUtcMsc - beginUtcMsc) < maxMills)
                {
                    ApiUtil.sleepTime(500);
                    LogUtil.D("尝试失败,再次尝试点击");
                } else
                {
                    break;
                }
            }
        }
        return isClick;
    }

    /**
     * 通过des查找并点击控件,含超时
     *
     * @return
     */
    public static boolean clickNodeByDesWithTimeOut(long maxMills, String id) {

        boolean isClick;

        AccessibilityNodeInfo node = findNodeByDesWithTimeOut(maxMills, id);

        if (node == null) return false;
        long beginUtcMsc = System.currentTimeMillis(); //记录当前开始时间
        long curUtcMsc; //当前时间
        if (maxMills == 0 || maxMills < 0)
        {
            maxMills = WAIT_UI_APPEAR_MSEC;
        }

        while (true)
        {
            isClick = AcessibilityApi.performViewClick(node);
            if (isClick)
            {
                break;
            } else
            {
                curUtcMsc = System.currentTimeMillis();

                if ((curUtcMsc - beginUtcMsc) < maxMills)
                {
                    ApiUtil.sleepTime(500);
                    LogUtil.D("尝试失败,再次尝试点击");
                } else
                {
                    break;
                }
            }
        }
        return isClick;
    }

    /**
     * 点击某个节点,直到超时
     *
     * @param maxMills 超时时间
     */
    public static boolean clickNodeWithTimeOut(long maxMills, AccessibilityNodeInfo node) {
        boolean isClick = false;

        if (node == null) return false;
        long beginUtcMsc = System.currentTimeMillis(); //记录当前开始时间
        long curUtcMsc; //当前时间
        if (maxMills == 0 || maxMills < 0)
        {
            maxMills = WAIT_UI_APPEAR_MSEC;
        }

        while (true)
        {
            isClick = AcessibilityApi.performViewClick(node);
            if (isClick)
            {
                break;
            } else
            {
                curUtcMsc = System.currentTimeMillis();

                if ((curUtcMsc - beginUtcMsc) < maxMills)
                {
                    ApiUtil.sleepTime(500);
                    LogUtil.D("尝试失败,再次尝试点击");
                } else
                {
                    break;
                }
            }
        }
        return isClick;
    }


    /**
     * 模糊查找,只要找到一个就返回
     *
     * @param maxMustMills 超时时间
     * @param map          查找的条件
     */
    public static AccessibilityNodeInfo findOptionNodeWithTimeOut(long maxMustMills, HashMap<String, String[]> map) {

        AccessibilityNodeInfo nodeInfo;

        if (map != null)
        {

            for (String item : map.keySet())
            {

                String[] vlue = map.get(item);
                if (vlue.length == 0) continue;

                switch (item)
                {
                    case NodeSelector.DES_KEY:
                        for (String vl : vlue)
                        {
                            nodeInfo = findNodeByDesWithTimeOut(maxMustMills, vl);
                            if (!isExists(nodeInfo))
                            {
                                LogUtil.I("模糊查找 DES节点[" + vl + "]查找失败");

                            } else
                            {
                                LogUtil.I("模糊查找 DES节点[" + vl + "]查找成功");
                                return nodeInfo;
                            }
                        }
                        break;
                    case NodeSelector.TEXT_KEY:
                        for (String vl : vlue)
                        {
                            nodeInfo = findNodeByTextWithTimeOut(maxMustMills, vl);
                            if (!isExists(nodeInfo))
                            {
                                LogUtil.I("模糊查找 字符串节点[" + vl + "]查找失败");

                            } else
                            {
                                LogUtil.I("模糊查找 字符串节点[" + vl + "]查找成功");
                                return nodeInfo;
                            }
                        }

                        break;
                    case NodeSelector.ID_KEY:
                        for (String vl : vlue)
                        {
                            nodeInfo = findNodeByIdWithTimeOut(maxMustMills, vl);
                            if (!isExists(nodeInfo))
                            {
                                LogUtil.I("模糊查找 ID节点[" + vl + "]查找失败");

                            } else
                            {
                                LogUtil.I("模糊查找 ID节点[" + vl + "]查找成功");
                                return nodeInfo;
                            }
                        }
                        break;
                }

            }
        }


        return null;

    }


    /**
     * 判断是否是当前页面
     */
    public static boolean isMyNeedPage(NodeSelector nodeSelecor) {
        boolean isPage;

        if (nodeSelecor == null) return false;

        long maxMustMills = nodeSelecor.getMaxMustMills();
        long maxOptionMills = nodeSelecor.getMaxOptionMills();

        //---must 只有有一个失败,就失败
        boolean mustTag = true;
        HashMap<String, String[]> must = nodeSelecor.getMust();
        if (must != null)
        {
            for (String item : must.keySet())
            {

                String[] vlue = must.get(item);
                if (vlue.length == 0) continue;


                switch (item)
                {
                    case NodeSelector.DES_KEY:
                        for (String vl : vlue)
                        {
                            mustTag = isExists(findNodeByDesWithTimeOut(maxMustMills, vl));
                            if (!mustTag)
                            {
                                LogUtil.I("must DES节点[" + vl + "]查找失败");

                                return false;
                            } else
                            {
                                LogUtil.I("must DES节点[" + vl + "]查找成功");

                            }
                        }
                        break;
                    case NodeSelector.TEXT_KEY:
                        for (String vl : vlue)
                        {
                            mustTag = isExists(findNodeByTextWithTimeOut(maxMustMills, vl));
                            if (!mustTag)
                            {
                                LogUtil.I("must 字符串节点[" + vl + "]查找失败");
                                return false;
                            } else
                            {
                                LogUtil.I("must 字符串节点[" + vl + "]查找成功");

                            }
                        }

                        break;
                    case NodeSelector.ID_KEY:
                        for (String vl : vlue)
                        {
                            mustTag = isExists(findNodeByIdWithTimeOut(maxMustMills, vl));
                            if (!mustTag)
                            {
                                LogUtil.I("must ID节点[" + vl + "]查找失败");

                                return false;
                            } else
                            {
                                LogUtil.I("must ID节点[" + vl + "]查找成功");

                            }
                        }
                        break;
                }


            }
        }

        //---option,只要有一个成功,就成功

        boolean optTag = true;
        HashMap<String, String[]> option = nodeSelecor.getOption();
        if (option != null)
        {
            for (String item : option.keySet())
            {

                String[] vlue = option.get(item);
                if (vlue.length == 0) continue;

                switch (item)
                {
                    case NodeSelector.DES_KEY:
                        for (String vl : vlue)
                        {
                            mustTag = isExists(findNodeByDesWithTimeOut(maxMustMills, vl));
                            if (mustTag)
                            {
                                LogUtil.I("option DES节点[" + vl + "]查找成功");

                                return true;
                            } else
                            {
                                LogUtil.I("option DES节点[" + vl + "]查找失败");

                            }
                        }
                        break;
                    case NodeSelector.TEXT_KEY:
                        for (String vl : vlue)
                        {
                            optTag = isExists(findNodeByTextWithTimeOut(maxOptionMills, vl));

                            if (optTag)
                            {
                                LogUtil.I("option 字符串节点[" + vl + "]查找成功");

                                return true;
                            } else
                            {
                                LogUtil.I("option 字符串节点[" + vl + "]查找失败");

                            }
                        }
                        break;
                    case NodeSelector.ID_KEY:
                        for (String vl : vlue)
                        {
                            optTag = isExists(findNodeByIdWithTimeOut(maxOptionMills, vl));
                            if (optTag)
                            {
                                LogUtil.I("option ID节点[" + vl + "]查找成功");

                                return true;
                            } else
                            {
                                LogUtil.I("option ID节点[" + vl + "]查找失败");

                            }
                        }
                        break;
                }


            }

        }

        isPage = (mustTag && optTag);

        return isPage;
    }


    /**
     * 前往预期页面,可以串联多个页面路径
     *
     * @param lists 条件
     */
    public static boolean jumpToNeedPage(List<ActionSelector> lists) {
        boolean isJump = false;

        if (lists == null || lists.size() == 0) return false;


        for (ActionSelector item : lists)
        {
            long maxClickMills = item.getMaxWClickMSec();

            NodeSelector page = item.getPage();

            HashMap<String, String> clickNode = item.getClick();

            if (page != null && clickNode != null && maxClickMills != 0)
            {

                if (isMyNeedPage(page))
                {

                    for (String key : clickNode.keySet())
                    {

                        switch (key)
                        {
                            case NodeSelector.TEXT_KEY:

                                isJump = clickNodeByTextWithTimeOut(maxClickMills, clickNode.get(key));

                                break;
                            case NodeSelector.ID_KEY:

                                isJump = clickNodeByIdWithTimeOut(maxClickMills, clickNode.get(key));

                                break;
                            case NodeSelector.DES_KEY:

                                isJump = clickNodeByDesWithTimeOut(maxClickMills, clickNode.get(key));

                                break;
                        }
                        if (!isJump)
                        {
                            LogUtil.E("未找到节点:" + clickNode.get(key));

                            return false;
                        }
                    }
                } else
                {
                    LogUtil.E("不在预期页面:" + page.toString());
                    return false;
                }
            }


        }

        return isJump;

    }


    /**
     * 通过text查找输入控件,然后输入
     *
     * @param maxMils  最大超时时间
     * @param text     text节点查找
     * @param inputStr 输入的内容
     * @return 是否输入成功
     */
    public static boolean findNodeByTextAndInput(long maxMils, String text, String inputStr) {
        AccessibilityNodeInfo node = findNodeByTextWithTimeOut(maxMils, text);

        if (node != null)
        {
            return AcessibilityApi.inputTextByNode(node, inputStr);

        }

        return false;
    }

    /**
     * 通过text查找输入控件,然后输入
     *
     * @param maxMils  最大超时时间
     * @param id       id节点查找
     * @param inputStr 输入的内容
     * @return 是否输入成功
     */
    public static boolean findNodeByIdAndInput(long maxMils, String id, String inputStr) {
        AccessibilityNodeInfo node = findNodeByIdWithTimeOut(maxMils, id);

        if (node != null)
        {
            return AcessibilityApi.inputTextByNode(node, inputStr);

        }

        return false;
    }

    /**
     * 回退到桌面
     */
    public static boolean backToDesk() {
        int i = 10;
        while (i > 0)
        {

            AcessibilityApi.performAction(AcessibilityApi.ActionType.BACK);
            String pke = AcessibilityApi.getEventPkg();
            LogUtil.I("正在执行回退_当前包名:" + pke);

            if ("".equals(pke))
            {
                LogUtil.D("辅助服务异常");
                return false;
            }


            for (String str : Config.DESKTOP_PKG)
            {
                if (str.equals(pke))
                {
                    LogUtil.I("已到桌面");

                    return true;
                }
            }
            ApiUtil.sleepTime(1000);
            i--;
        }

        return false;

    }

    /**
     * 回home
     */
    public static void backHome() {
        AcessibilityApi.performAction(AcessibilityApi.ActionType.HOME);

    }

    /**
     * 回back
     */
    public static void back() {
        AcessibilityApi.performAction(AcessibilityApi.ActionType.BACK);
    }

    private static boolean isExists(AccessibilityNodeInfo nodeInfo) {
        return nodeInfo != null;

    }

  
}
