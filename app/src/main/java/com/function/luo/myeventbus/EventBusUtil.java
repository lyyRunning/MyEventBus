package com.function.luo.myeventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by luo on 2019/7/17.
 * <p>
 * 其实这个也没必要封装，本身就是一句代码
 *
 */

public class EventBusUtil {

    /**
     * 订阅事件
     *
     * @param subscriber
     */
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    /**
     * 取消订阅事件
     *
     * @param subscriber
     */
    public static void unregister(Object subscriber) {
        //先检查一下
        if (EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber);
        }

    }

    /**
     * 发送消息
     *
     * @param event
     */
    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发送粘性消息
     *
     * @param event
     */
    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }

}
