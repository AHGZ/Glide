package com.hgz.test.glide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;



public class MyTransation extends BitmapTransformation {
    //定义圆角
    private static float radio=0;
    private RectF rectF;
    private Canvas canvas;
    private Paint paint;

    /**
     * 默认圆角角度
     * @param context
     */
    public MyTransation(Context context){
        this(context,4);

    }

    /**
     * 根据传来的角度设置
     * @param context
     * @param dp
     */
    public MyTransation(Context context,int dp){
        super(context);
        //手机的参数（dpi）
        this.radio=Resources.getSystem().getDisplayMetrics().density*dp;

    }

    /**
     *
     * @param pool  池子用来装bitmap（为了复用bitmap）
     * @param toTransform
     * @param outWidth
     * @param outHeight
     * @return
     */
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return createRound(pool,toTransform);
    }

    /**
     * 创建圆角图片
     * @param pool
     * @param toTransform
     * @return
     */
    private Bitmap createRound(BitmapPool pool, Bitmap toTransform) {
        if (toTransform==null){
            return null;
        }
        //拿到图片池里的图片
        Bitmap bitmap = pool.get(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
        if (bitmap==null){
            //没有就创建
            bitmap=Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
        }
        //画bitmap
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setAntiAlias(true);
        //设置抖动
        paint.setDither(true);
        //设置作色器
        paint.setShader(new BitmapShader(toTransform, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        //实例化矩形
        rectF = new RectF(0f, 0f, toTransform.getWidth(), toTransform.getHeight());
        //画圆角矩形
        canvas.drawRoundRect(rectF,radio,radio,paint);
        return bitmap;
    }


    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }
}
