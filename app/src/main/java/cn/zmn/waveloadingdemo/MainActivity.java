package cn.zmn.waveloadingdemo;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    private WaveLoadingView mWaveLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mWaveLoadingView.updateProgress(0.8f);

            }

        }, 2000);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
