package com.shpp.skobzin.lesson4_intent;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends Activity {

    private AlarmManager alarmManager;
    private Calendar alarmTime;
    private PendingIntent pendingIntent;
    private boolean isAlarmTimeSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent onSetAlarmIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, onSetAlarmIntent, 0);

        alarmTime = Calendar.getInstance();
        isAlarmTimeSet = false;

        final TextView currentAlarm = (TextView) findViewById(R.id.currentAlarmText);

        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                alarmTime.set(Calendar.HOUR_OF_DAY, hour);
                alarmTime.set(Calendar.MINUTE, minute);
                alarmTime.set(Calendar.SECOND, 0);
                isAlarmTimeSet = true;
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime.getTimeInMillis(), pendingIntent);
                currentAlarm.setTextSize(120);
                currentAlarm.setText(((hour < 10) ? "0" + hour : hour) + ":" + ((minute < 10) ? "0" + minute : minute));
            }
        }, alarmTime.get(Calendar.HOUR_OF_DAY), alarmTime.get(Calendar.MINUTE), DateFormat.is24HourFormat(this));

        Button setAlarmButton = (Button) findViewById(R.id.setAlarmButton);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAlarmTimeSet) {
                    alarmTime = Calendar.getInstance();
                }
                timePickerDialog.updateTime(alarmTime.get(Calendar.HOUR_OF_DAY), alarmTime.get(Calendar.MINUTE));
                timePickerDialog.show();
            }
        });

        Button cancelAlarmButton = (Button) findViewById(R.id.cancelAlarmButton);
        cancelAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmManager.cancel(pendingIntent);
                isAlarmTimeSet = false;
                currentAlarm.setTextSize(40);
                currentAlarm.setText(R.string.tvNotSetAlarm);
            }
        });
    }
}
