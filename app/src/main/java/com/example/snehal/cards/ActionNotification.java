package com.example.snehal.cards;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ActionNotification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg= intent.getStringExtra("toastmsg");
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
