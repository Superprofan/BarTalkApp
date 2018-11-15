package com.superprofan.bartalk.util;

import android.app.Activity;
import android.os.Build;
import android.view.View;

/**
 * A utility class that helps with showing and hiding system UI such as the
 * status bar and navigation/system bar.
 */

public abstract class SystemUiHider {

    public static final int FLAG_LAYOUT_IN_SCREEN_OLDER_DEVICES = 0x1;
    public static final int FLAG_FULLSCREEN = 0x2;
    public static final int FLAG_HIDE_NAVIGATION = FLAG_FULLSCREEN | 0x4;

    protected Activity mActivity;
    protected View mAnchorView;
    protected int mFlags;

    protected  OnVisibilityChangeListener mOnVisibilityChangeListener = sDummyListener;

    public SystemUiHider(Activity activity, View anchorView, int flags) {
        mActivity = activity;
        mAnchorView = anchorView;
        mFlags = flags;
    }

    /**
 * Creates and returns an instance of {@link SystemUiHider} that is
 * appropriate for this device. The object will be either a
 * {@link SystemUiHiderBase} or {@link SystemUiHiderHoneyComb} depending on
 * the device.
 */
public static SystemUiHider getInstance(Activity activity, View anchorView, int flags) {
    if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB){
        return new SystemUiHiderHoneyComb(activity, anchorView, flags);
    }else{
        return new SystemUiHiderBase(activity,anchorView,flags);
    }
}
    /**
     * Sets up the system UI hider. Should be called from
     * {@link Activity#onCreate}.
     */
    public abstract void setup();

    /**
     * Returns whether or not the system UI is visible.
     */
    public abstract boolean isVisible();

    /**
     * Hide the system UI.
     */
    public abstract void hide();

    /**
     * Show the system UI.
     */
    public abstract void show();

    /**
     * Toggle the visibility of the system UI.
     */
    public void toggle() {
        if (isVisible()) {
            hide();
        } else {
            show();
        }
    }

    /**
     * Registers a callback, to be triggered when the system UI visibility
     * changes.
     */
    public void setOnVisibilityChangeListener(OnVisibilityChangeListener listener) {
        if (listener == null) {
            listener = sDummyListener;
        }

        mOnVisibilityChangeListener = listener;
    }
    /**
     * A dummy no-op callback for use when there is no other listener set.
     */
    private static OnVisibilityChangeListener sDummyListener = new OnVisibilityChangeListener() {
        @Override
        public void onVisibilityChange(boolean visible) {

        }
    };


    public interface OnVisibilityChangeListener{
        public void onVisibilityChange(boolean visible);
    }
}