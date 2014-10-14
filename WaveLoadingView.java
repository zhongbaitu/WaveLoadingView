package com.demo.waveviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;

public class WaveLoadingView extends View {

	private Paint wavePaint;
	private Paint textPaint;
	private int textColor = 0xffFFFFFF;
	private int waveColor = 0xff0099CC;
	private int textSize = 50;
	private Path path;

	// 左右偏移 φ
	private int fai = 0;
	// 上下偏移
	private float k = -50;
	// 角速度
	private float w = 0.5f;
	// 振幅
	private int a = 20;

	private int height;
	private int width;
	private float targetHeight;
	private float textHeight;
	private int progress = 0;
	// 0% 时，空白的高度
	private float baseBlank;

	private OnFinishedListener listener;

	private int ms = 4;

	private boolean isRun = true;

	public WaveLoadingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public WaveLoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public WaveLoadingView(Context context) {
		super(context);
		init();
	}

	private void init() {
		wavePaint = new Paint();
		wavePaint.setAntiAlias(true);
		wavePaint.setColor(waveColor);

		textPaint = new Paint();
		textPaint.setAntiAlias(true);
		textPaint.setColor(textColor);
		textPaint.setTextSize(textSize);

		path = new Path();

		new MyThread().start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		setPath();
		canvas.drawPath(path, wavePaint);
		String str = progress + "%";
		float strWidth = textPaint.measureText(str);
		canvas.drawText(str, width / 2 - (strWidth / 2), textHeight, textPaint);
	}

	private void setPath(){
		int x = 0;
		int y = 0;
		path.reset();
		for (int i = 0; i < width; i++) {
			x = i;
			y = (int) (a * Math.sin((i * w + fai) * Math.PI / 180) + k);
			if (i == 0) {
				path.moveTo(x, y);
			}
			path.quadTo(x, y, x + 1, y);
		}
		path.lineTo(width, height);
		path.lineTo(0, height);
		path.close();
	}

	/**
	 * 
	 * @param p 0~1
	 */
	public void updateProgress(float p) {
		if(p >=0 && p <= 1){
			targetHeight = (float) (baseBlank * (1 - p));
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		initLayoutParams();
	}
	
	private void initLayoutParams(){
		height = this.getHeight();
		width = this.getWidth();
		baseBlank = (float) (height * 0.9);
		targetHeight = baseBlank;
		k = baseBlank;
		textHeight = baseBlank;
	}

	public void setTextColor(int color) {
		this.textColor = color;
		textPaint.setColor(textColor); 
	}

	public void setTextSize(int size){
		this.textSize = size;
		textPaint.setTextSize(textSize);
	}
	
	public void setWaveColor(int color) {
		this.waveColor = color;
		wavePaint.setColor(waveColor);
	}

	/**
	 * 
	 * @param amplitude
	 *            波浪振幅， 默认为 20
	 */
	public void setAmplitude(int a) {
		this.a = a;
	}

	/**
	 * 
	 * @param w
	 *            默认为0.5
	 */
	public void setPalstance(float w) {
		this.w = w;
	}

	/**
	 * 
	 * @param ms
	 *            默认为4毫秒
	 */
	public void setRefreshTime(int ms) {
		this.ms = ms;
	}

	public void setOnFinishedListener(OnFinishedListener l) {
		this.listener = l;
	}

	class MyThread extends Thread {

		@Override
		public void run() {
			while (isRun) {
				fai++;
				if (k > targetHeight) {
					k -= 0.5;
					progress = (int) ((baseBlank - k) / baseBlank * 100);
					if (textHeight > (height / 2)) {
						textHeight -= 0.5;
					}
				}
				if (progress >= 100 && listener != null) {
					listener.onFinished();
					isRun = false;
				}
				if (fai == 360) {
					fai = 0;
				}
				try {
					Thread.sleep(ms);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public interface OnFinishedListener {
		public void onFinished();
	}

}
