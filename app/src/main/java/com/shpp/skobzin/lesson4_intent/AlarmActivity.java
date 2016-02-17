package com.shpp.skobzin.lesson4_intent;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class AlarmActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        TextView currentAlarm = (TextView) findViewById(R.id.textView);
        currentAlarm.setTextSize(120);
        currentAlarm.setText(((hour < 10) ? "0" + hour : hour) + ":" + ((minute < 10) ? "0" + minute : minute));

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.din_don);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        Button closeButton = (Button) findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                finish();
            }
        });
    }
}
