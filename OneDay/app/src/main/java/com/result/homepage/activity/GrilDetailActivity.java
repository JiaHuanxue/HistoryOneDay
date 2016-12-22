package com.result.homepage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.result.homepage.R;

import uk.co.senab.photoview.PhotoView;

public class GrilDetailActivity extends AppCompatActivity {

    private PhotoView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gril_detail);
        Intent intent = getIntent();
        imageView = (PhotoView) findViewById(R.id.imageView_coll_m);
        Glide.with(this)
                .load(intent.getStringExtra("url"))
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓冲全尺寸
                .centerCrop() //设置居中
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);

    }

}
