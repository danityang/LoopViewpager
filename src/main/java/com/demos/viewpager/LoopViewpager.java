package com.demos.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by yangdi on 2017/5/12.
 */

public class LoopViewpager extends ViewPager {

    private boolean isCanScroll = true;
    private boolean canLoop = true;

    public LoopViewpager(Context context) {
        super(context);
    }

    public LoopViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isCanScroll)
            return super.onInterceptTouchEvent(ev);
        else
            return false;
    }

    private float oldX = 0, newX = 0;
    private static final float sens = 5;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isCanScroll) {

                switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        oldX = ev.getX();
                        break;

                    case MotionEvent.ACTION_UP:
                        newX = ev.getX();
                        if (Math.abs(oldX - newX) < sens) {

                        }
                        oldX = 0;
                        newX = 0;
                        break;
                }

            return super.onTouchEvent(ev);
        } else
            return false;
    }
}
