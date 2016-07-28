package com.wokee.progressbgview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBgView progressBgView = (ProgressBgView) findViewById(R.id.progressview);
            progressBgView.setBackgroundAsTile(R.drawable.bg_detail_live_white);
            progressBgView.setDuration(1000);
            progressBgView  .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progressBgView.isRunning()){
                    progressBgView.stopAnimation();
                }else{
                    progressBgView.startAnimation();
                }
            }
        });
    }
}
