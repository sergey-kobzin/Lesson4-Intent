package com.shpp.skobzin.lesson4_intent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Broadcast Receiver: ", "onReceive");
        Intent onAlarmIntent = new Intent(context, AlarmActivity.class);
        onAlarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(onAlarmIntent);
    }
}
