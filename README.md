##1、效果：

-----

![](http://zhongsir.qiniudn.com/2014-10-15%2Ftest.gif)

##2、使用：
---
```
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
```

##3、太困了。明天再补。不好意思。晚安。