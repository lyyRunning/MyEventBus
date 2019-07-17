package com.function.luo.myeventbus;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity {

    private Button button1;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        button1 = findViewById(R.id.button1);
        tv1 = findViewById(R.id.tv1);

        //点击事件
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TwoActivity.launch(MainActivity.this);
            }
        });
    }


    /**
     * 表示要注册事件
     *
     * @return
     */
    @Override
    protected boolean isRegisteredEventBus() {

        return true;
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
                Log.d("EventBus", "接收到B类型的Event" + event.getData());
                break;
            case EventCode.C:
                User user = (User) event.getData();
                Log.d("EventBus", "接收到B类型的Event，携带User" + user);
                break;
            case EventCode.D:
                News news = (News) event.getData();
                Log.d("EventBus", "接收到D类型的Event，携带News" + news);
                break;
            default:
        }
    }

}