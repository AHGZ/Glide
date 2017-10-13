package com.hgz.test.glide;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public class MyCircleView extends Drawable {
    private float raido=0;
    private final Paint paint;

    public MyCircleView (Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        paint = new Paint();
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        //取长宽的最小值（为了把图片画满）
        int min = Math.min(width, height);
        raido=min/2;
    }
    @Override
    public void draw(@NonNull Canvas canvas) {
        //画圆
        canvas.drawCircle(raido,raido,raido,paint);
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        //设置透明度
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        //设置颜色过滤器
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        //保持和窗口一样的透明度
        return PixelFormat.TRANSLUCENT;
    }

    /**
     * 实际宽度
     * @return
     */
    @Override
    public int getIntrinsicWidth() {
        return (int) (raido*2);
    }

    /**
     * 实际高度
     * @return
     */
    @Override
    public int getIntrinsicHeight() {
        return (int) (raido*2);
    }
}
