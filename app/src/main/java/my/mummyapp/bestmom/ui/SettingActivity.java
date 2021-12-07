package my.mummyapp.bestmom.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import my.mummyapp.bestmom.R;
import my.mummyapp.bestmom.broadcastreceivers.AlarmReceiver;
import my.mummyapp.bestmom.ui.fragments.TimePickerFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Calendar;

public class SettingActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private static final String PREF_NAME = "switchPreferences";
    private static final String PREF_NAME_TIME = "timepreferences";
    private Switch aSwitch;
private TextView mTime_SelectedTime;
private AdView mAdview;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.BANNER);

        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdview = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);

        aSwitch =(Switch) findViewById(R.id.switch_setting);
        mTime_SelectedTime =(TextView) findViewById(R.id.text_timeSelected);



       SharedPreferences preferences = getSharedPreferences(PREF_NAME,0);
       Boolean state = preferences.getBoolean("switchKeys",false);
       if (state==true){

           SharedPreferences mpreferences = getSharedPreferences(PREF_NAME_TIME,0);
           String text = mpreferences.getString("textValue","");
           mTime_SelectedTime.setText(text);


       }else{
           mTime_SelectedTime.setText("");

       }
       aSwitch.setChecked(state);
     aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
             if (b){
                 DialogFragment timePicker = new TimePickerFragment();
                 timePicker.show(getSupportFragmentManager(),"time picker");
             }else{
                 cancelAlarm();
                 mTime_SelectedTime.setText("");

             }
             SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME,0);
             SharedPreferences.Editor editor = sharedPreferences.edit();
             editor.putBoolean("switchKeys",b);
             editor.commit();


         }
     });

    }
    public void updateTimeText(Calendar c){
        String time= DateFormat.getTimeFormat(this).format(c.getTime());
        mTime_SelectedTime.setText(time);
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME_TIME,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("textValue",time);
        editor.commit();

    }

    public void cancelAlarm(){
    AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(this, AlarmReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);

    alarmManager.cancel(pendingIntent);
    Toast.makeText(SettingActivity.this, "تم الغاء الاشعار", Toast.LENGTH_SHORT).show();


}
public void startAlarm(Calendar c){

    AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(this, AlarmReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);
    //
    // alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),24*60*60*1000,pendingIntent);
    Toast.makeText(SettingActivity.this, "تم ظبط الاشعار فالوقت المحدد", Toast.LENGTH_SHORT).show();

}
    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        Calendar C= Calendar.getInstance();
        C.set(Calendar.HOUR_OF_DAY,i);
        C.set(Calendar.MINUTE,i1);
        C.set(Calendar.SECOND,0);
updateTimeText(C);
startAlarm(C);

    }
}