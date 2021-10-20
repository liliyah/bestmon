package com.example.bestmom.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bestmom.R;
import com.example.bestmom.utils.NonSwipeViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.HashMap;

public class MainScreenActivity extends AppCompatActivity {
public HashMap<Integer,String> mTaskNotDone;
private  ViewPager2 viewPager2;
private TabLayout tabLayout;
private RelativeLayout relativeLayout;
private int i = 0;
private TextView name_view;
private  String[] titles = new String[]{"المهام","التطور"};
private ImageView ImageFacebook,ImageMessage,ImageWhatsapp,ImageBuyMeCoffe;
private final static String FACE_BOOK= "com.facebook.katana";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_screen);

        ImageFacebook = (ImageView) findViewById(R.id.facebook_share);
        ImageMessage = (ImageView)findViewById(R.id.sendemail_share);
        ImageWhatsapp = (ImageView)findViewById(R.id.whatsapp_share);
        ImageBuyMeCoffe = (ImageView)findViewById(R.id.buymecoffe_share);

        viewPager2 = findViewById(R.id.viewpager);
        relativeLayout = findViewById(R.id.Progress_relative_layout);
        tabLayout = findViewById(R.id.tabLayout);
        name_view= findViewById(R.id.text_name);
        Typeface typeface= Typeface.createFromAsset(getAssets(),"font-ar/arabicregualrraslan.ttf");
        name_view.setTypeface(typeface);

        viewPager2.setAdapter(new NonSwipeViewPager(this) );
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setUserInputEnabled(false);
        viewPager2.setPageTransformer(new MarginPageTransformer(1500));


           new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles[position]);
            }
        }).attach();
ImageFacebook.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (chekAppInstalled(FACE_BOOK)==false ){
            Toast.makeText(MainScreenActivity.this, "التطبيق غير موجود", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,FACE_BOOK);
          intent.setType("text/plain") ;
          startActivity(intent);
          return;
        }

    }
});

    }
    private boolean chekAppInstalled (String type ){
        PackageManager packageManager =  getPackageManager();
     try {
         PackageInfo info = packageManager.getPackageInfo(type,PackageManager.GET_META_DATA);
     }catch (PackageManager.NameNotFoundException e){
         return false;
     }
        return true;

    }
}