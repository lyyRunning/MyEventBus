package com.function.luo.myeventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by luo on 2019/6/20.
 */

public class ThirdActivity extends BaseActivity {
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ButterKnife.bind(this);




    }




    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                EventBusUtil.sendEvent(new Event(EventCode.A));
                break;
            case R.id.button2:
                EventBusUtil.sendEvent(new Event(EventCode.B,"中国必将能够崛起！"));
                break;
            case R.id.button3:
                User user = new User();
                user.setName("孙海涛");
                user.setAge(29);
                Event<User> event = new Event<>(EventCode.C, user);
                EventBusUtil.sendEvent(event);
                break;
            case R.id.button4:
                News news = new News();
                news.setMessage("这是一个重大的好消息啊");
                Event<News> event2 = new Event<>(EventCode.D, news);
                EventBusUtil.sendEvent(event2);
                break;
            default:
        }
    }
    /**
     * 接受事件
     *
     * @param event
     */
    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case EventCode.A:
                Log.d("EventBus", "接收到A类型的Event");
                break;
            case EventCode.B:
                Log.d("EventBus", "接收到B类型的Event"+event.getData());
                break;
            case EventCode.C:
                User user = (User) event.getData();
                Log.d("EventBus", "接收到B类型的Event，携带User"+user);
                break;
            case EventCode.D:
                News news = (News) event.getData();
                Log.d("EventBus", "接收到D类型的Event，携带News"+news);
                break;
            default:
        }
    }


    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }


    public static void launch(Context mContext) {

        Intent intent = new Intent(mContext, ThirdActivity.class);
        mContext.startActivity(intent);
    }


}
