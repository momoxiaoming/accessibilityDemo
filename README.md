# accessibilityDemo
安卓模拟点击服务工具包

### 一. 如何使用? 亦可参考demo


1. 接入module包,创建一个service继承module中的AutoCoreService
2. manifest文件中声明service

```java
 <service
            android:name="你创建的service包类名"
            android:permission="android.permission.BIND_ACCESSIBILITY_SERVICE">
            <intent-filter>
                <action android:name="android.accessibilityservice.AccessibilityService" />
            </intent-filter>

            <meta-data
                android:name="android.accessibilityservice"
                android:resource="@xml/accessibility_service_config" />
        </service>
```
3. 利用api创建自己的脚本逻辑



### 二. accessibility 主要API 

##### 1. UiApi.isMyNeedPage [判断是否在当前页面]

ps: 以判断是否在手机设置界面为例

```java
String pageStr = "{"
                + "'maxMustMills':5000,"
                +"'maxOptionMills':5000,"
                + "'must':{'text':['设置'],'id':[],'desc':[]},"
                +"'option':{'text':['更多'],'id':[],'desc':[]}"
                + "}";
        UiApi.isMyNeedPage(pageStr);
       
```

>参数说明	:
> maxMustMills 必要节点最大查找时间,毫秒
> maxOptionMills 可选节点最大查找时间,毫秒
> must 必要节点,
> option 可选节点
> text 节点文本内容,数组,可填多个
> id 节点的资源id,数组,可填多个
> desc 节点的描述内容,数组,可填多个
> 

##### 2. UiApi.jumpToNeedPage [前往某个界面]

ps: 例子 [设置->移动网络]

```java
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
		UiApi.jumpToNeedPage(new String[]{temp1,temp2});
       
```

>参数说明	:
> maxMustMills 必要节点最大查找时间,毫秒
> maxOptionMills 可选节点最大查找时间,毫秒
> click  需要点击的节点, 支持(text,id,desc)三种方式
> page  页面
> must 必要节点,
> option 可选节点
> text 节点文本内容,数组,可填多个
> id 节点的资源id,数组,可填多个
> desc 节点的描述内容,数组,可填多个




##### 3. UiApi类 其他接口


>1. backHome   回home页
2. back    返回
3. findNodeByTextWithTimeOut  通过text精确查找节点
4. findNodeByIdWithTimeOut  通过Id 精确查找节点点
5. findNodeByDesWithTimeOut 通过des 精确查找节点
6. findNodeByClsWithTimeOut 通过类名,模糊查找节点,有多个只会返回第一个
7. clickNodeByTextWithTimeOut 通过text查找节点并点击
8. clickNodeByIdWithTimeOut  通过id 查找节点并点击
9. clickNodeByDesWithTimeOut 通过des 查找节点并点击
10. findNodeByTextAndInput 通过 text查找节点并输入内容
11. findNodeByIdAndInput 通过id查找节点并输入内容
12. ......其他自己去看源码