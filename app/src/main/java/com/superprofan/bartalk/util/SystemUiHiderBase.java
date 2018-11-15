package com.superprofan.bartalk.util;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by SuperPuper on 13.11.2018.
 */

public class SystemUiHiderBase extends SystemUiHider {

    private boolean mVisible = true;
    protected SystemUiHiderBase(Activity activity, View ancorView, int flags){
        super(activity, ancorView,flags);
    }

    @Override
    public void setup() {
        if((mFlags&FLAG_LAYOUT_IN_SCREEN_OLDER_DEVICES)==0){
            mActivity.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                        | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                        | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }

    @Override
    public boolean isVisible() {
        return mVisible;
    }

    @Override
    public void hide() {
        if ((mFlags&FLAG_FULLSCREEN)!=0){
            mActivity.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        mOnVisibilityChangeListener.onVisibilityChange(false);
        mVisible = false;

    }

    @Override
    public void show() {
        if ((mFlags&FLAG_FULLSCREEN)!=0){
            mActivity.getWindow().setFlags(
                    0,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        mOnVisibilityChangeListener.onVisibilityChange(true);
        mVisible = true;
    }
}
