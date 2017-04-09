package com.austin.audio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView mTv;
    private RecordButton mRecordButton;
    private boolean flag;
    private CheckBox radioButton;
    private AudioManager audioManager;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radioButton = (CheckBox) findViewById(R.id.radioButton);

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


        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);

                }else{
                    audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI);

                }
            }
        });

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);



    }


    public void play(View view) throws IOException {
        if(mediaPlayer==null)
        mediaPlayer = MediaPlayer.create(this, R.raw.lightning);

        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }

    public void up(View view) {
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);

    }

    public void down(View view) {
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);

    }

}
