package com.demo.waveviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity {
	
	private WaveLoadingView mWaveLoadingView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mWaveLoadingView = new WaveLoadingView(this);
		mWaveLoadingView.setWaveColor(0xff0099CC);
		mWaveLoadingView.setTextColor(0xffFFFFFF);
		mWaveLoadingView.setTextSize(80);
		mWaveLoadingView.setAmplitude(20);
		mWaveLoadingView.setPalstance(0.5f);
		mWaveLoadingView.setRefreshTime(4);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				mWaveLoadingView.updateProgress(0.8f);
				
			}
			
		}, 2000);
		
		setContentView(mWaveLoadingView);
	}

}
