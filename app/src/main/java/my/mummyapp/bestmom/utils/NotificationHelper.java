package my.mummyapp.bestmom.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import my.mummyapp.bestmom.R;
import my.mummyapp.bestmom.ui.SettingActivity;

public class NotificationHelper extends ContextWrapper {
    private static final String channel_id = "CHANNEL_ID";
    private static final String channel_name = "channel1";
    private NotificationManager notificationManager;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationHelper(Context base) {
        super(base);
        createChannel();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel mychannel = new NotificationChannel(channel_id,channel_name, NotificationManager.IMPORTANCE_DEFAULT);
        mychannel.enableLights(true);
        mychannel.setLightColor(R.color.pink);
        mychannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(mychannel);


    }
    public NotificationManager getManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
       return notificationManager;
    }


    public NotificationCompat.Builder getChannelNotification(String title,String message){
        Intent notificationIntent = new Intent(getApplicationContext(), SettingActivity.class);
        PendingIntent intent =PendingIntent.getActivity(getApplicationContext(),0,notificationIntent,0);

        return  new NotificationCompat.Builder(getApplicationContext(),channel_id).
                setContentTitle(title).setContentText(message).setSmallIcon(R.mipmap.ic_mombaby).setContentIntent(intent).setAutoCancel(true);
    }

}
