package com.demos.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by yangdi on 2017/5/11.
 */

public class Adapter extends PagerAdapter{

    private List<ImageView> viewList;
    private final int MULTIPLE_COUNT = 10;
    private IPositionListener iPositionListener;

    private ViewPager viewPager;

    String TAG = "PagerAdapter";

    public Adapter(List<ImageView> viewList) {
        this.viewList = viewList;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }



    public int getRealCount() {
        return viewList == null ? 0 : viewList.size();
    }

    public int toRealPosition(int position) {
        int realCount = getRealCount();
        if (realCount == 0)
            return 0;
        int realPosition = position % realCount;
        return realPosition;
    }

    @Override
    public int getCount() {
        return getRealCount()*MULTIPLE_COUNT;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {// 实例化
        int realPosition = toRealPosition(position);
        iPositionListener.selectedPosition(realPosition);
		container.removeView(viewList.get(realPosition));
        container.addView(viewList.get(realPosition));
        return viewList.get(realPosition);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {// 销毁
        container.removeView((View) object);
    }


    public void setOnItemClickListener(IPositionListener iPositionListener) {
        this.iPositionListener = iPositionListener;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        // 获取viewpager实际位置
        int position = viewPager.getCurrentItem();
        if (position == 0) {
            position = viewList.size();
        } else if (position == getCount() - 1) {
            position = 0;
        }
        try {
            viewPager.setCurrentItem(position, false);
        }catch (IllegalStateException e){}
    }
}
