package com.zz.ndkffmpeg;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class VideoView extends SurfaceView {

    public VideoView(Context context) {
        super(context);
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {

    }

    public void player(String input) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                render(input, VideoView.this.getHolder().getSurface());
            }
        }).start();
    }

    public native void render(String input, Object surfaceView);
}
