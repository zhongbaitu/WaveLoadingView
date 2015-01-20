##效果：

---
![](http://zhongsir.qiniudn.com/2014-10-15%2Ftest.gif)

##使用：

-----------

在Activity onCreate()中写

```java
        mWaveLoadingView = new WaveLoadingView(this);
        setContentView(mWaveLoadingView);
        mWaveLoadingView.setWaveColor(0xff0099CC)
                        .setTextColor(0xffFFFFFF)
                        .setTextSize(40)
                        .setTextColor(0xff000000)
                        .setAmplitude(20)
                        .setPalstance(0.5f)
                        .setRefreshTime(4);
        mWaveLoadingView.setOnFinishedListener(new WaveLoadingView.OnFinishedListener() {
            @Override
            public void onFinished() {
                System.out.println("完成");
            }
        });
```

##License

-----------

```
Copyright 2014 ZhongBaiTu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
