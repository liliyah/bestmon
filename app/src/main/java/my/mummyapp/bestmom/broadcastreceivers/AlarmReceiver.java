package my.mummyapp.bestmom.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import my.mummyapp.bestmom.utils.NotificationHelper;

public class AlarmReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification("كيف تكونى ام افضل","هل قمتى بمهمتك اليوم مع طفلك");
        notificationHelper.getManager().notify(1,nb.build());
    }
}
