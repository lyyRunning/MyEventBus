package com.function.luo.myeventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by luo on 2019/6/20.
 */

public class TwoActivity extends BaseActivity {
    private Button button1;
    private Button button2;
    private TextView tv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        tv1 = findViewById(R.id.tv1);


        init();
    }

    private void init() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusUtil.sendEvent(new Event(EventCode.B,"中国必将能够崛起！"));

               // finish();
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdActivity.launch(TwoActivity.this);
            }
        });
    }


    public static void launch(Context mContext) {
        Intent intent = new Intent(mContext,TwoActivity.class);
        mContext.startActivity(intent);
    }


    /**
     * 接受事件
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
}
