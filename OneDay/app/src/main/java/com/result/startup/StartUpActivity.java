package com.result.startup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.result.homepage.R;
import com.result.homepage.activity.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class StartUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        //timer中有一个线程,这个线程不断执行task
        Timer timer = new Timer();
        //timertask实现runnable接口,TimerTask类就代表一个在指定时间内执行的task
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(StartUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        //设置这个task在延迟三秒之后自动执行
        timer.schedule(task, 1000 * 3);


    }
}
