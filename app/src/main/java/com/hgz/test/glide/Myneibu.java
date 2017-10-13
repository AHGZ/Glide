package com.hgz.test.glide;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;


public class Myneibu extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        //设置磁盘缓存的内存
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,"huang",100*1024*1024));
        //设置磁盘缓存的外置内存
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,"zhengyu",100*1024*1024));
        //设置自己的缓存路径
        builder.setDiskCache(new DiskLruCacheFactory(Environment.getExternalStorageDirectory()+"/feng","kai",100*1024*1024));
        //拿到内存缓存测量器的管理者
        MemorySizeCalculator.Builder builder1=new MemorySizeCalculator.Builder(context);
        //得到内存缓存大小
        int cacheSize = builder1.build().getMemoryCacheSize();
        //得到Bitmap池的大小
        int poolSize = builder1.build().getBitmapPoolSize();
        //设置内存缓存的大小（不要自定义）
        builder.setMemoryCache(new LruResourceCache(cacheSize*2));
        builder.setBitmapPool(new LruBitmapPool(poolSize*2));
        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888));
        //双端队列
       /* BlockingQueue<Runnable> queue=new LinkedBlockingQueue<>();
        queue.add()*/
        //builder.setDiskCacheExecutor((GlideExecutor) new ThreadPoolExecutor(3,5,5, TimeUnit.SECONDS,null));
        //设置线程数量以及名字
        builder.setDiskCacheExecutor(GlideExecutor.newDiskCacheExecutor(3,"sss", GlideExecutor.UncaughtThrowableStrategy.DEFAULT));
    }
}
