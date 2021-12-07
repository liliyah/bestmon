package my.mummyapp.bestmom.ui.fragments;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import my.mummyapp.bestmom.BuildConfig;
import my.mummyapp.bestmom.R;

public class BestMomDialogue  extends DialogFragment {
    private final static String FACE_BOOK= "com.facebook.katana";
    private  final static  String WHATS_APP="com.whatsapp";


    public BestMomDialogue() {
    }

public static  String TAG ="DialogueFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.dialogue_view,container);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ImageView Canceldialogue= (ImageView) view.findViewById(R.id.Buttons_cancel);
        ImageView whatsappDialogue= (ImageView) view.findViewById(R.id.whatsapp_image);
        ImageView Facebookdialogue= (ImageView) view.findViewById(R.id.facebook_image);

        Canceldialogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             getDialog().dismiss();
            }
        });

        whatsappDialogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chekAppInstalled(WHATS_APP)==false ){
                    Toast.makeText(getActivity(), "التطبيق غير موجود", Toast.LENGTH_SHORT).show();
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
        Facebookdialogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chekAppInstalled(FACE_BOOK)==false ){
                    Toast.makeText(getActivity(), "التطبيق غير موجود", Toast.LENGTH_SHORT).show();
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



    }
    private boolean chekAppInstalled (String type ){
        PackageManager packageManager = getContext().getPackageManager();
        try {
            PackageInfo info = packageManager.getPackageInfo(type,PackageManager.GET_META_DATA);
        }catch (PackageManager.NameNotFoundException e){
            return false;
        }
        return true;
    }
}
