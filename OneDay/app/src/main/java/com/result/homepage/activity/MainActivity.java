package com.result.homepage.activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.result.homepage.R;
import com.result.homepage.fragment.About;
import com.result.homepage.fragment.Collection;
import com.result.homepage.fragment.Girl;
import com.result.homepage.fragment.HomePage;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    Toolbar mToolBar;
    private Fragment fragment;
    private FrameLayout mFrameLayout;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private String title = "历史上的今天";

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrameLayout = (FrameLayout) findViewById(R.id.fragmentL);
        FragmentManager mFM=getSupportFragmentManager();
        FragmentTransaction mFT=mFM.beginTransaction();
        fragment= new HomePage();
        mFT.replace(R.id.fragmentL, fragment).commit();
        mToolBar = (Toolbar) findViewById(R.id.mToolBar); //ToolBar
        mToolBar.setTitle(title);
        mDrawer = (DrawerLayout) findViewById(R.id.myDrawer); //DrawerLayout
        navigationView = (NavigationView) findViewById(R.id.nav); //NavigationView导航栏
        mToolBar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(mToolBar);
        //设置左上角的图标响应
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolBar, 0, 0) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawer.setDrawerListener(mDrawerToggle); //设置侧滑监听
        //与fragment传值
        Bundle bundle = new Bundle();
        bundle.putString("title", "222");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                FragmentManager mFM=getSupportFragmentManager();
                FragmentTransaction mFT=mFM.beginTransaction();

                if (menuItem.getTitle().equals("历史上的今天")) {
                    navigationView.setCheckedItem(R.id.item1);
                    fragment= new HomePage();
                }else if(menuItem.getTitle().equals("妹纸")){
                    navigationView.setCheckedItem(R.id.item2);
                    fragment= new Girl();
                }else if(menuItem.getTitle().equals("收藏")){
                    navigationView.setCheckedItem(R.id.item3);
                    fragment= new Collection();

                }else if(menuItem.getTitle().equals("关于")){
                    navigationView.setCheckedItem(R.id.item4);
                    fragment= new About();
                }
                mFT.replace(R.id.fragmentL, fragment).commit();
                title = menuItem.getTitle().toString();
                mToolBar.setTitle(title);
                Bundle bundle = new Bundle();
                bundle.putString("title", menuItem.getTitle().toString());
                mDrawer.closeDrawer(Gravity.LEFT);
                title = menuItem.getTitle().toString();
                return false;
            }
        });


    }
    private long mExitTime = 0;

    /**
     *设置双击退出
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();

            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
