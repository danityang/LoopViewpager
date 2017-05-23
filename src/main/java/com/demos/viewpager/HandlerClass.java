package com.demos.viewpager;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.SoftReference;

/**
 * Created by yangdi on 2017/5/22.
 */

public class HandlerClass extends Handler {

    SoftReference softReference;

    public HandlerClass(Object o) {
        this.softReference = new SoftReference(o);
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case 0x11:
                MainActivity ma = (MainActivity) softReference.get();
                ma.startTurning();
                sendEmptyMessageDelayed(0x11, 3000);
                break;
        }
    }
}
