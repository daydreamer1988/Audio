package com.austin.audio;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Austin on 2017/3/31.
 */

public class DialogManager {

    private Context mContext;
    private final Dialog dialog;

    private ImageView mLeft;
    private ImageView mLevel;
    private TextView mText;

    public DialogManager(Context context) {
        mContext = context;
        dialog = new Dialog(context, R.style.RecordDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.record_dialog, null);
        dialog.setContentView(view);

        mLeft = (ImageView) view.findViewById(R.id.left);
        mLevel = (ImageView) view.findViewById(R.id.level);
        mText = (TextView) view.findViewById(R.id.text);
    }


    public void showRecordDialog(){
        mLevel.setVisibility(View.VISIBLE);
        mLeft.setImageResource(R.drawable.recorder);
        mText.setText("向上滑动，取消录音");
        dialog.show();
    }

    public void wantToCancel(){
        mLevel.setVisibility(View.GONE);
        mLeft.setImageResource(R.drawable.cancel);
        mText.setText("松开手指，取消录音");


    }

    public void tooShort(){
        mLevel.setVisibility(View.VISIBLE);
        mLevel.setImageResource(R.drawable.voice_to_short);
        mText.setText("录音时间太短");


    }

    public void dismissDialog(){
        if (dialog != null) {
            dialog.dismiss();
        }
    }



}
