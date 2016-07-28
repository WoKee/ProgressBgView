/**
 * 
 */
package com.wokee.progressbgview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.File;

/**
 *
 * com.wokee.progressbgview.java
 * 
 * Created by wang on 2016年7月28日上午9:45:41
 * 
 * Tips: android 发廊灯效果 || 自己实现更多的控制器
 */
public class ProgressBgView extends FrameLayout {
	private TranslateAnimation mProgressAnimation;
	private ImageView mProgressImage;
	private long duration =1000;//动画时长

	public ProgressBgView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mProgressImage = new ImageView(getContext());
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		mProgressImage.setLayoutParams(layoutParams);
		addView(mProgressImage);
	}

	/**
	 * 设置背景图片
	 * @param tileImageResId
	 */
	public void setBackgroundAsTile(int tileImageResId) {
		setBackgroundAsTile( BitmapFactory.decodeResource(getResources(), tileImageResId));
	}
	/**
	 * 设置背景图片
	 */
	public void setBackgroundAsTile(File file) throws NullPointerException{
		if (file == null || !file.isFile()) {
			throw new NullPointerException("file is null or isn`t file");
		}
		setBackgroundAsTile(BitmapFactory.decodeFile(file.getAbsolutePath()));
	}
	/**
	 * 设置背景图片
	 */
	public void setBackgroundAsTile(String filePath){
		setBackgroundAsTile(new File(filePath));
	}
	
	/**
	 * 设置背景图片
	 * @param bitmap
	 */
	public void setBackgroundAsTile(Bitmap bitmap) throws NullPointerException{
		if (bitmap == null) {
			throw new NullPointerException("bitmap is null");
		}
		BitmapDrawable tileRepeatedBitmap = new BitmapDrawable(getResources(), bitmap);
		tileRepeatedBitmap.setTileModeX(TileMode.REPEAT);
		initAnimation(bitmap.getWidth());
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN){
			mProgressImage.setBackgroundDrawable(tileRepeatedBitmap);
		}else{
			mProgressImage.setBackground(tileRepeatedBitmap);
		}
	}
	
	/**
	 * 根据文件宽度初始化动画
	 * @param tileImageWidth
	 */
	private void initAnimation(int tileImageWidth) {
		LayoutParams layoutParams = (LayoutParams) mProgressImage.getLayoutParams();
		layoutParams.setMargins(-tileImageWidth, 0, 0, 0);
		mProgressAnimation = new TranslateAnimation(0, tileImageWidth - 3, 0, 0);
		mProgressAnimation.setInterpolator(new LinearInterpolator());
		mProgressAnimation.setDuration(duration);
		mProgressAnimation.setRepeatCount(Animation.INFINITE);
	}

	/**
	 * 启动动画
	 */
	public void startAnimation() {
		if (mProgressImage.getAnimation()==null) {
			mProgressImage.startAnimation(mProgressAnimation);
		}
	}
	/**
	 * 停止动画
	 */
	public void stopAnimation(){
		if (mProgressImage.getAnimation()!=null) {
			mProgressImage.getAnimation().cancel();
			mProgressImage.clearAnimation();
		}
	}
	/**
	 * 判断动画是否正在进行
	 * @return
	 */
	public boolean isRunning(){
		return mProgressImage.getAnimation()!=null;
	}

	/**
	 * 设置动画时长
	 * @param duration the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration;
		mProgressAnimation.setDuration(duration);
	}
	
	
}
