package my.mummyapp.bestmom.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import my.mummyapp.bestmom.BuildConfig;
import my.mummyapp.bestmom.R;
import my.mummyapp.bestmom.utils.NonSwipeViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainScreenActivity extends AppCompatActivity  {
public HashMap<Integer,String> mTaskNotDone;
private  ViewPager2 viewPager2;
private TabLayout tabLayout;
private RelativeLayout relativeLayout;
private int i = 0;
private TextView name_view;

private BillingClient billingClient;
private String sku="coffe_developer";
private SkuDetails mskuDetails;

private  String[] titles = new String[]{"المهام","التطور"};
private List skulist = new ArrayList();
private ImageView ImageFacebook,ImageMessage,ImageWhatsapp,ImageBuyMeCoffe,SettingsImage,refreshImage;
private final static String FACE_BOOK= "com.facebook.katana";
private  final static  String WHATS_APP="com.whatsapp";
private  final static  String  GOOGLE_MAIL="com.google.android.gm";
Activity activity = MainScreenActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        ImageFacebook = (ImageView) findViewById(R.id.facebook_share);
        ImageMessage = (ImageView)findViewById(R.id.sendemail_share);
        ImageWhatsapp = (ImageView)findViewById(R.id.whatsapp_share);
        ImageBuyMeCoffe = (ImageView)findViewById(R.id.buymecoffe_share);
        SettingsImage = (ImageView)findViewById(R.id.settings);
        refreshImage=(ImageView)findViewById(R.id.referesh_image);

        billingClient = BillingClient.newBuilder
                (this).enablePendingPurchases().setListener(new PurchasesUpdatedListener() {
            @Override
            public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
                int responseCode = billingResult.getResponseCode();
                if(responseCode == BillingClient.BillingResponseCode.OK && list !=null){
                    for (Purchase p : list){
                        handlePurshases(p);

                    }

                }else if (billingResult.getResponseCode()== BillingClient.BillingResponseCode.USER_CANCELED){
                    //handle error
                    Log.d("bilingresult", String.valueOf(billingResult.getResponseCode()));
                }else{
                    //handle error

                    Log.d("bilibngresult", String.valueOf(billingResult.getResponseCode()));
                }
            }
        }).build();
        startconnection();

        viewPager2 = findViewById(R.id.viewpager);
        relativeLayout = findViewById(R.id.Progress_relative_layout);
        tabLayout = findViewById(R.id.tabLayout);
        name_view= findViewById(R.id.text_name);

        Typeface typeface= Typeface.createFromAsset(getAssets(),"font-ar/arabicregualrraslan.ttf");
        name_view.setTypeface(typeface);
        viewPager2.setAdapter(new NonSwipeViewPager(this) );
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setUserInputEnabled(false);

       // viewPager2.setPageTransformer(new MarginPageTransformer(1500));
        //viewPager2.setPageTransformer( new ZoomOutPageTransformer());

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles[position]);
            }
        }).attach();

        SettingsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent = new Intent(MainScreenActivity.this,SettingActivity.class);
             startActivity(intent);
            }
        });
        ImageMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (chekAppInstalled(FACE_BOOK) == false) {
                    Toast.makeText(MainScreenActivity.this, "التطبيق غير موجود", Toast.LENGTH_SHORT).show();
                } else {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    String myemail = "sara.m.ragab@hotmail.com";
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{myemail});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "sent from user");
                    emailIntent.putExtra(Intent.EXTRA_CC, "sender name");
                    emailIntent.setType("text/html");
                    emailIntent.setPackage(GOOGLE_MAIL);
                    startActivity(emailIntent);
                    return;
                }
            }
        });
         ImageFacebook.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
        if (chekAppInstalled(FACE_BOOK)==false ){
            Toast.makeText(MainScreenActivity.this, "التطبيق غير موجود", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,FACE_BOOK + BuildConfig.APPLICATION_ID);
          intent.setType("text/plain") ;
          intent.setPackage(FACE_BOOK);
          startActivity(intent);
          return;
        }
    }
});
        ImageWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chekAppInstalled(WHATS_APP)==false ){
                    Toast.makeText(MainScreenActivity.this, "التطبيق غير موجود", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT,WHATS_APP + BuildConfig.APPLICATION_ID);
                    intent.setType("text/plain") ;
                    intent.setPackage(WHATS_APP);
                    startActivity(intent);
                    return;
                }
            }
        });
        refreshImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 ViewModelData viewModelData;
                viewModelData = ViewModelProviders.of(MainScreenActivity.this).get(ViewModelData.class);
                viewModelData.deleteAllProgress();
                Toast.makeText(MainScreenActivity.this, "لقد تم اعادة التطبيق من البدايه", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void getProductsDetails(){
        Activity activity = this;
        List<String> skuList = new ArrayList<>();
        skuList.add(sku);
        SkuDetailsParams params = SkuDetailsParams.newBuilder().setSkusList(skuList).
                setType(BillingClient.SkuType.INAPP).build();

        billingClient.querySkuDetailsAsync(params, new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<SkuDetails> list) {
                if (billingResult.getResponseCode()==BillingClient.BillingResponseCode.OK&& list != null){
                    //textview ... button
                    SkuDetails iteminfo = list.get(0);
                    String title = iteminfo.getTitle();
                    String price = iteminfo.getPrice();
                    String itemid = iteminfo.getSku();
                    Log.d("title and price", title+price +itemid);
                    //int responsecode = billingClient.launchBillingFlow(activity,billingFlowParams).getResponseCode();
                    //Log.d("responsecode", "onSkuDetailsResponsecode: "+responsecode);

                    ImageBuyMeCoffe.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("buttoncliked", "imagecoffeispressed");
                            //BillingFlowParams billingFlowParams= BillingFlowParams.newBuilder().setSkuDetails(iteminfo).build();

                       billingClient.launchBillingFlow(activity, BillingFlowParams.newBuilder().setSkuDetails(iteminfo).build());

                        }
                    });

                  //int responsecode =  billingClient.launchBillingFlow(MainScreenActivity.this,billingFlowParams).getResponseCode();
              }
            }
        });
    }

    private void startconnection(){

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
                startconnection();
                //Toast.makeText(MainScreenActivity.this, "there was an error", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {

                if ( billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    //bilingclientis ready you can query purchases here
                    getProductsDetails();
                }
                else{
                    Log.d("errorResponseBilling",String.valueOf(billingResult.getResponseCode()));
                }
            }
        });
   }

    private void handlePurshases(Purchase p) {
        if (p.getSkus().equals(sku)&& p.getPurchaseState() ==Purchase.PurchaseState.PURCHASED){
            Log.d("skupurshased", "item is purshased");
            /*
            if(!p.isAcknowledged()){
                AcknowledgePurchaseParams acknowledgePurchaseParams= AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(p.getPurchaseToken()).build();
                billingClient.acknowledgePurchase(acknowledgePurchaseParams, new AcknowledgePurchaseResponseListener() {
                    @Override
                    public void onAcknowledgePurchaseResponse(@NonNull BillingResult billingResult) {
                        if (billingResult.getResponseCode()==BillingClient.BillingResponseCode.OK){
                            Toast.makeText(MainScreenActivity.this, "تمت العمليه بنجاح", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            */

            ConsumeParams consumeParams = ConsumeParams.newBuilder().setPurchaseToken(p.getPurchaseToken()).build();
            ConsumeResponseListener listener = new ConsumeResponseListener() {
                @Override
                public void onConsumeResponse(@NonNull BillingResult billingResult, @NonNull String s) {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){

                        Toast.makeText(MainScreenActivity.this, "تمت العمليه بنجاح", Toast.LENGTH_SHORT).show();
                        //handleresult
                    }
                }
            };
            billingClient.consumeAsync(consumeParams,listener);
           // Toast.makeText(MainScreenActivity.this, "شكرا لك :) اتمنى لك يوما سعيدا مع طفلك", Toast.LENGTH_SHORT).show();
        }else {
            Log.d("handlepurshases", "handlePurshases:error " );
        }
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