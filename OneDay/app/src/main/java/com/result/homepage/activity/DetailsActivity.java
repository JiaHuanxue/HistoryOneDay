package com.result.homepage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.result.homepage.R;
import com.result.homepage.bean.DetailsBean;
import com.result.homepage.db.OneDayDao;
import com.result.homepage.gson.Tools;
import com.result.homepage.okhttp.OkHttp;

import java.io.IOException;

import okhttp3.Request;

public class DetailsActivity extends AppCompatActivity {

    private Toolbar toolbar_xiangqing;
    private String e_id;
    private String url="http://v.juhe.cn/todayOnhistory/queryDetail.php?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&e_id=";
    private TextView textView_xiangqing;
    private String title;
    private String date;
    private boolean isCheck = false;
    private String content;
    private ImageView imageView_ima;
    private FloatingActionButton floatingActionButton_shoucang;
    private String image_url;
    private OneDayDao dao = new OneDayDao(DetailsActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        floatingActionButton_shoucang = (FloatingActionButton) findViewById(R.id.xin);
        textView_xiangqing = (TextView) findViewById(R.id.textView_xiangqing);
        toolbar_xiangqing = (Toolbar)findViewById(R.id.toolbar_xiangqing);
        imageView_ima = (ImageView) findViewById(R.id.backdrop);
        Intent intent = getIntent();
        e_id = intent.getStringExtra("e_id");
        date = intent.getStringExtra("date");
        boolean isChecks = intent.getBooleanExtra("isChecks",false);
        String hometitle = intent.getStringExtra("hometitle");
        title = intent.getStringExtra("title");
        if (isChecks||dao.same(hometitle)){
            floatingActionButton_shoucang.setImageResource(R.mipmap.ic_like);
            isCheck = true;
        }
        floatingActionButton_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCheck) {
                    floatingActionButton_shoucang.setImageResource(R.mipmap.ic_like);
                    if(date==null||title==null){
                        Toast.makeText(DetailsActivity.this,"这条信息已经删除,不能收藏！",Toast.LENGTH_SHORT).show();
                    }else {
                        dao.add(date, title, image_url,e_id);
                    }
                    isCheck = true;
                } else {
                    floatingActionButton_shoucang.setImageResource(R.mipmap.ic_unlike);
                    dao.delete(title);

                    isCheck = false;
                }
            }
        });
        url = url+e_id;
        initData();
        setSupportActionBar(toolbar_xiangqing);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    public void initData(){
        Log.i("*******",url);
        OkHttp.getAsync(url, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Log.i("*******","失败");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                DetailsBean detaB = Tools.parseJsonWithGson(result, DetailsBean.class);
                content = detaB.getResult().get(0).getContent();
                title = detaB.getResult().get(0).getTitle();
                textView_xiangqing.setText(content);
                image_url = detaB.getResult().get(0).getPicUrl().get(0).getUrl();
                CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
                collapsingToolbarLayout.setTitle(title);
                Glide.with(DetailsActivity.this)
                        .load(detaB.getResult().get(0).getPicUrl().get(0).getUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL) //缓冲全尺寸
                        .centerCrop() //设置居中
                        .placeholder(R.mipmap.afs)
                        .into(imageView_ima);

            }
        });
    }
}
