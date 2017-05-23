package com.demos.viewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ImageView img;
    private ViewPagerScroller scroller;
    private Adapter adapter;
    private volatile int index = -1;
    private List<ImageView> viewList = new ArrayList<ImageView>();
    HandlerClass mHandler;
    int imgs[] = new int[]{R.mipmap.sh_disney01, R.mipmap.sh_disney02,
            R.mipmap.sh_disney03, R.mipmap.sh_disney04,
            R.mipmap.sh_disney05, R.mipmap.sh_disney06};

    public static final String TAG = "MainactivityVieepager";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        initView();

    }

//    Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0x11:
//
//                    int page = viewPager.getCurrentItem() + 1;
//                    viewPager.setCurrentItem(page);
//
//                    mHandler.sendEmptyMessageDelayed(0x11, 3000);
//                    break;
//            }
//        }
//    };

    public void startTurning(){
        int page = viewPager.getCurrentItem() + 1;
        viewPager.setCurrentItem(page);
    }

    private void initView() {

        mHandler = new HandlerClass(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        for (int i = 0; i < imgs.length; i++) {
            img = new ImageView(this);
            img.setImageResource(imgs[i]);
            viewList.add(img);
        }
        initViewPagerScroll();
        adapter = new Adapter(viewList);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true,new PageTransformer());
        adapter.setViewPager(viewPager);

        mHandler.sendEmptyMessage(0x11);

        adapter.setOnItemClickListener(new IPositionListener() {
            @Override
            public void selectedPosition(int postion) {
//                Toast.makeText(MainActivity.this,"第"+(postion+1)+"张图片",Toast.LENGTH_LONG).show();
                Log.d("MainActivity","Listener - postion: "+postion);
                index = postion;
            }
        });
    }

    private void PicClick(int postion) {
        switch (postion) {
            // TODO

        }
    }


    private void initViewPagerScroll() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            scroller = new ViewPagerScroller(
                    viewPager.getContext());
            mScroller.set(viewPager, scroller);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }



}
