package com.auto.assist.accessibility.selector;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 *
 */
public class NodeSelector
{


    public static final String TEXT_KEY = "text";  //通过text

    public static final String ID_KEY = "id";  //通过id

    public static final String DES_KEY = "desc";  //通过描述


    private long maxMustMills = 0;  //必要节点超时时间,毫秒
    private long maxOptionMills = 0; //可选节点超时时间,毫秒


    private HashMap<String, String[]> must = null;  //必选节点,key为TEXT_KEY或ID_KEY中一种

    private HashMap<String, String[]> option = null;  //可选节点


    public long getMaxMustMills() {
        return maxMustMills;
    }

    public void setMaxMustMills(long maxMustMills) {
        this.maxMustMills = maxMustMills;
    }

    public long getMaxOptionMills() {
        return maxOptionMills;
    }

    public void setMaxOptionMills(long maxOptionMills) {
        this.maxOptionMills = maxOptionMills;
    }

    public HashMap<String, String[]> getMust() {
        return must;
    }

    public void setMust(HashMap<String, String[]> must) {
        this.must = must;
    }

    public HashMap<String, String[]> getOption() {
        return option;
    }

    public void setOption(HashMap<String, String[]> option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "NodeSelector{" +
                "maxMustMills=" + maxMustMills +
                ", maxOptionMills=" + maxOptionMills +
                ", must=" + hasMapStr(must) +
                ", option=" + hasMapStr(must) +
                '}';
    }

    public String hasMapStr(HashMap<String, String[]> map) {
        String rlt = "";
        if (map != null)
        {
            for (String key : map.keySet())
            {
                rlt+="--key:"+key+"_value: "+map.get(key)[0];

            }
        }
        return rlt;

    }

    public static NodeSelector fromJson(String jsonStr) {
        return new Gson().fromJson(jsonStr, NodeSelector.class);
    }
}
