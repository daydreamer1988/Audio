package com.austin.audio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTv;
    private RecordButton mRecordButton;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecordButton = (RecordButton) findViewById(R.id.recordButton);

        mTv = (TextView) findViewById(R.id.tv);

        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showRecordLayout(flag);
                flag = !flag;
            }

            private void showRecordLayout(boolean flag) {
                mRecordButton.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, flag?R.anim.translate_to_bottom:R.anim.translate_from_bottom);
                animation.setDuration(200);
                animation.setFillAfter(true);
                mRecordButton.startAnimation(animation);
            }
        });


    }
}
