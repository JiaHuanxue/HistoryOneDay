package com.result.homepage.pic;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.result.homepage.R;

public class ImgLoder extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		initImageLoaderConfig();
		super.onCreate();
	}

	private void initImageLoaderConfig() {
		// TODO Auto-generated method stub
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisc(true)
				.showImageOnFail(R.mipmap.ic_launcher)
				.showImageOnFail(R.mipmap.ic_launcher)
				.considerExifParams(true)
				.cacheInMemory(true)
				// 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
				.resetViewBeforeLoading(true)
				.bitmapConfig(Bitmap.Config.RGB_565)     //设置图片的解码类型
				.showImageOnLoading(R.mipmap.ic_launcher).build();


		int max = (int) (Runtime.getRuntime().maxMemory() / 8);
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.memoryCache(new UsingFreqLimitedMemoryCache(max))
				.discCache(new UnlimitedDiskCache(getCacheDir()))
				.threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY-1)
				.defaultDisplayImageOptions(options)
				.build();
		ImageLoader.getInstance().init(configuration);

	}

}


