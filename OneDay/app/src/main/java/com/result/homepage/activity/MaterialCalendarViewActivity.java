package com.result.homepage.activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.result.homepage.R;
import com.result.homepage.bean.FirstEvent;

import de.greenrobot.event.EventBus;

public class MaterialCalendarViewActivity extends AppCompatActivity {

    private Toolbar mMToolbar;
    private MaterialCalendarView mMaterialCalendarView;
    private int month;
    private int day;
    private int year;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_calendar_view);
        mMToolbar = (Toolbar) findViewById(R.id.mMToolBar);
        mMToolbar.setTitle("选择日期");
        setSupportActionBar(mMToolbar);
        mMToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button button_queding = (Button) findViewById(R.id.button_queding);
        mMaterialCalendarView = (MaterialCalendarView) findViewById(R.id.mcv);
        mMaterialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                    year = date.getYear();
                    month = date.getMonth();
                    day = date.getDay();
            }
        });
        button_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MaterialCalendarViewActivity.this,year+"年"+month+"月"+day+"日",Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(
                        new FirstEvent(""+year+"",""+month+1+"",""+day+""));
                finish();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
