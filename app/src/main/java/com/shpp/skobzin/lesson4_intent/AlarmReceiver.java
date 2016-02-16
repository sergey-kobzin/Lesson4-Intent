package com.shpp.skobzin.lesson4_intent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent onAlarmIntent = new Intent(context, AlarmActivity.class);
        onAlarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(onAlarmIntent);
    }
}
