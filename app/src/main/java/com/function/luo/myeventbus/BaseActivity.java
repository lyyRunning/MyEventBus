package com.function.luo.myeventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by luo on 2019/7/17.
 */

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //默认不注册，子类重写
        if (isRegisteredEventBus()) {
            //注册
            EventBusUtil.register(this);
        }

    }


    /**
     * 可以在子类中重写onStickyEventBusCome（）
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    /**
     * 可以在子类中重写onStickyEventBusCome（）
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event) {

    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(Event event) {

    }


    /**
     * 是否注册事件分发
     *
     * 可以在子类中重写isRegisterEventBus（）
     * @return true 注册；false 不注册，默认不注册
     */
    protected boolean isRegisteredEventBus() {
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //默认不注册，子类重写
        if (isRegisteredEventBus()) {

            //反注册
            EventBusUtil.unregister(this);


        }
    }


}
