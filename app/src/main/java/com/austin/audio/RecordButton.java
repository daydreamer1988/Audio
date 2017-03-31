package com.austin.audio;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Austin on 2017/3/31.
 */

public class RecordButton extends Button {

    private static final int RECORD_NORMAL = 0;
    private static final int RECORD_FINISH = 1;
    private static final int RECORD_WANT_TO_CANCEL = 2;
    private static final float DISTANCE_TO_CANCEL_Y = Resources.getSystem().getDisplayMetrics().density * 50;

    private int curState = RECORD_NORMAL;

    private boolean isRecording = false;
    private DialogManager dialogManager;

    public RecordButton(Context context) {
        this(context, null);
    }

    public RecordButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        dialogManager = new DialogManager(getContext());

        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialogManager.showRecordDialog();
                isRecording = true;
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                changeState(RECORD_FINISH);
                break;

            case MotionEvent.ACTION_MOVE:
                    if (wantToCancel(x, y)) {
                        changeState(RECORD_WANT_TO_CANCEL);
                        dialogManager.wantToCancel();
                    } else {
                        changeState(RECORD_FINISH);
                        dialogManager.showRecordDialog();
                    }
                break;

            case MotionEvent.ACTION_UP:
                changeState(RECORD_NORMAL);
                if(isRecording) {
                    dialogManager.dismissDialog();
                }else{
                    dialogManager.tooShort();
                }

                break;
        }


        return super.onTouchEvent(event);
    }


    private boolean wantToCancel(float x, float y) {
        if (x < 0 || x > getWidth()) {
            return true;
        }
        if (y < -DISTANCE_TO_CANCEL_Y || y > DISTANCE_TO_CANCEL_Y) {
            return true;
        }
        return false;
    }

    private void changeState(int state) {
        if (curState == state) {
            return;
        }
        curState = state;

        switch (state) {
            case RECORD_NORMAL:
                setText(R.string.press_to_recode);
                break;

            case RECORD_FINISH:
                setText(R.string.finish_recode);

                break;

            case RECORD_WANT_TO_CANCEL:
                setText(R.string.want_to_cancel);

                break;
        }
    }


}
