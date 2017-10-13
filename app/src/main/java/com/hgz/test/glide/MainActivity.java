package com.hgz.test.glide;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;

public class MainActivity extends AppCompatActivity {

    private ImageView myimg;
    private ViewTarget<Myview, Bitmap> view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // myimg = (ImageView) findViewById(R.id.myimg);
        addimg();
        RequestOptions options = new RequestOptions();
        //重设图片大小(和centerCrop不能一起用)
        //options.override(300,300);
        //圆形图片
        //options.circleCrop();
        //设置圆角图片（有BUG慎用）
        //options.transform(new RoundedCorners(30));
        options.transform(new MyTransation(this,30));
        //加载图片编码格式
        //options.format(DecodeFormat.PREFER_ARGB_8888);
        //居中拉伸（当图片是长方形的时候图片会显示不完全）
        //options.centerCrop();
        //取长宽最小值，居中显示图片
        //options.centerInside();
        //取长宽最小值设置图片
        //options.fitCenter();
        //禁止动画
        //options.dontAnimate();
        //设置全部缓存
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
        //设置图片格式
        options.encodeFormat(Bitmap.CompressFormat.PNG);
        //加载Url错误时图片
        options.error(R.mipmap.ic_launcher);
        //当你url为空时执行
        options.fallback(R.mipmap.ic_launcher_round);
        //设置是否跳过内存缓存
        options.skipMemoryCache(false);
        //设置优先级
        options.priority(Priority.NORMAL);
        //设置预加载的图片
        options.placeholder(R.mipmap.ic_launcher);
        /*Glide.with(getApplicationContext())
                //指定返回类型
                //.asGif()
                //.asBitmap()
                .load("http://img5.imgtn.bdimg.com/it/u=2993396273,3023277058&fm=27&gp=0.jpg")
                //缩略图
                .thumbnail(0.1f)
                //加载动画（当图片加载完毕以后执行）
                .transition(new GenericTransitionOptions<Drawable>().transition(R.anim.transition))
                .apply(options)*/
                //.into(myimg);
                //.into();
       /* Glide.with(this)
                //.load("file://"+ Environment.getExternalStorageDirectory()+"/icon.png")
                //.load("file:///android_asset/icon.png")
                //.load("android.resource://com.baidu.test.myglidedemo3/raw/"+R.raw.icon)
                .into(myimg);*/
    }
   /* private SimpleTarget<Drawable> sim=new SimpleTarget<Drawable>() {
        @Override
        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
            myimg.setImageDrawable(resource);
        }
    };*/
    public void addimg(){
        Myview myfalm = (Myview) findViewById(R.id.myfalm);
        view2 = new ViewTarget<Myview, Bitmap>(myfalm) {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                this.view.loadimg(resource);
            }
        };
        RequestOptions options=new RequestOptions();
        options.transform(new MyTransation(this,30));
        Glide.with(this)
                .asBitmap()
                //.apply(options)
                .load("http://img5.imgtn.bdimg.com/it/u=2993396273,3023277058&fm=27&gp=0.jpg")
                .into(view2);
    }

}
