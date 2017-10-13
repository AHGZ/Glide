package com.hgz.test.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Myview extends FrameLayout{

    private ImageView mysecondimg;

    public Myview(@NonNull Context context) {
        super(context);
        initview(context);
    }

    private void initview(Context context) {
         inflate(context, R.layout.myviewitem, this);
        mysecondimg = (ImageView)findViewById(R.id.mysecondimg);
    }

    public Myview(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initview(context);
    }
    public void loadimg(Bitmap bitmap){

        mysecondimg.setImageDrawable(new MyCircleView(bitmap));
    }
}
