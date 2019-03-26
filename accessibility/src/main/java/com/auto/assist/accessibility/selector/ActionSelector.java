package com.auto.assist.accessibility.selector;

import com.google.gson.Gson;

import java.util.HashMap;

public class ActionSelector
{

    private NodeSelector page;

    private long maxWClickMSec = 0; //最大点击超时

    private HashMap<String ,String> click;  //点击的内容


    public NodeSelector getPage() {
        return page;
    }

    public void setPage(NodeSelector page) {
        this.page = page;
    }

    public long getMaxWClickMSec() {
        return maxWClickMSec;
    }

    public void setMaxWClickMSec(long maxWClickMSec) {
        this.maxWClickMSec = maxWClickMSec;
    }

    public HashMap<String, String> getClick() {
        return click;
    }

    public void setClick(HashMap<String, String> click) {
        this.click = click;
    }

    @Override
    public String toString() {
        return "ActionSelector{" +
                "page=" + page.toString() +
                ", maxWClickMSec=" + maxWClickMSec +
                ", click=" + click +
                '}';
    }

    public static ActionSelector fromJson(String jsonStr) {
        return new Gson().fromJson(jsonStr, ActionSelector.class);
    }
}
