package com.example.user.myapplication.lockscreen;

import android.widget.SeekBar;

/**
 * Created by Junho on 2016-03-09.
 *
 * Seekbar와 각 Fragment와의 Communication을 하기 위함 인터페이스
 * activity to fragment communication
 */
public interface LockscreenToSeekbar {
    void onStopSeekbar(SeekBar seekBar);
}
