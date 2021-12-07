package my.mummyapp.bestmom.ui.fragments;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import my.mummyapp.bestmom.R;
import my.mummyapp.bestmom.ui.ViewModelData;
import my.mummyapp.bestmom.ui.models.DataModel;

import java.util.List;

public class ProgressFragment  extends Fragment {
    private ImageView button_forwards;
    private ImageView button_backwards;
    private RelativeLayout relativeLayout;
    int i = 0;
    int m =0;
    int DayNumber=1;
    private ViewModelData viewModelData;
    private TextView mDate_textView, mProgressTextView;
    private RadioButton m_taskDoneCheck, m_task_notDone_Check;
    private int theday;
    int[] myimageList = new int[]{R.drawable.light_blue_bg, R.drawable.light_peach_bg, R.drawable.light_purple, R.drawable.light_skin_bg, R.drawable.light_yellow_bg, R.drawable.lightpista};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task, container, false);

        mDate_textView = (TextView) v.findViewById(R.id.textDate);
        mDate_textView.setEnabled(false);
        mProgressTextView = (TextView) v.findViewById(R.id.mtext_progressTask);
        mProgressTextView.setEnabled(false);
        m_taskDoneCheck = (RadioButton) v.findViewById(R.id.task_donecheck);
        m_task_notDone_Check = (RadioButton) v.findViewById(R.id.task_not_done);
        button_backwards = (ImageView)v.findViewById(R.id.btn_backward);
        button_forwards =(ImageView)v.findViewById(R.id.btn_forward);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        relativeLayout = (RelativeLayout) getActivity().findViewById(R.id.Progress_relative_layout);
        viewModelData = ViewModelProviders.of(getActivity()).get(ViewModelData.class);
        TextPaint paint  =mProgressTextView.getPaint();
        float width= mProgressTextView.getPaint().measureText(mProgressTextView.getText().toString());
        Shader shader = new LinearGradient(0,0,20,mProgressTextView.getLineHeight(),
                new int[]{
                        Color.parseColor("#AD1457"),
                        Color.parseColor("#FF3700B3"),

                },null, Shader.TileMode.REPEAT);
        mProgressTextView.getPaint().setShader(shader);
        m_taskDoneCheck.setChecked(false);
        mDate_textView.setText("1");
        m_task_notDone_Check.setChecked(false);
        viewModelData.getAllProgressData().observe(getViewLifecycleOwner(), new Observer<List<DataModel>>() {
            @Override
            public void onChanged(List<DataModel> dataModels) {
                Log.d("sizeDataModel", "DataModelSize "+dataModels.size());
                if (dataModels.get(DayNumber-1).getIs_task_done() == 1 && dataModels.get(DayNumber-1).getIs_task_not_done() == 0) {
                    m_taskDoneCheck.setChecked(true);
                }
                if (dataModels.get(DayNumber-1).getIs_task_done() == 0 && dataModels.get(DayNumber-1).getIs_task_not_done() == 1) {
                    m_task_notDone_Check.setChecked(true);
                }

                if (dataModels.get(DayNumber-1).getIs_task_done() == 0 && dataModels.get(DayNumber-1).getIs_task_not_done() == 0) {
                    m_task_notDone_Check.setChecked(false);
                    m_taskDoneCheck.setChecked(false);
                }
                mProgressTextView.setText(String.valueOf(dataModels.get(DayNumber-1).getProgress_Name()));
                mDate_textView.setText(String.valueOf(dataModels.get(DayNumber-1).getMdayNumber()));
               // Toast.makeText(getActivity(), "mday is"+h, Toast.LENGTH_SHORT).show();


            }
                });

        button_backwards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("buttonBackwardsABC", "buttonBackwards isss selected: "+m);
                if (!m_taskDoneCheck.isChecked() && !m_task_notDone_Check.isChecked()) {
                    Toast t = Toast.makeText(getActivity(), "يجب اختيار مهمه اليوم", Toast.LENGTH_SHORT);
                    LinearLayout toastLayout = (LinearLayout) t.getView();
                    TextView toastTv = (TextView) toastLayout.getChildAt(0);
                    toastTv.setTextSize(25);
                    t.show();

                }else{
                    DayNumber = Integer.parseInt(String.valueOf(mDate_textView.getText()));
                    theday= Integer.parseInt(String.valueOf(mDate_textView.getText()));

                    if(DayNumber==1)//(int) dataModels.size() - 1
                    {
                        DayNumber= 30;
                    }else{
                        DayNumber--;
                    }
                    //mDate_textView.setText(String.valueOf(m));
                    // int h= DayNumber-1;
                    // Toast.makeText(getActivity(), "mday is"+h, Toast.LENGTH_SHORT).show();
                    relativeLayout.setBackground(getActivity().getDrawable(myimageList[i]));
                    if (i == (int) myimageList.length - 1) {
                        i = 0;
                    } else {
                        i++;
                    }

                    //mProgressTextView.setText(String.valueOf(dataModels.get(j).getProgress_Name()));

                    if (m_taskDoneCheck.isChecked() && !m_task_notDone_Check.isChecked()){
                        viewModelData.UpdateProgressData(1,0,theday);
                    }
                    if (!m_taskDoneCheck.isChecked() && m_task_notDone_Check.isChecked()){
                        viewModelData.UpdateProgressData(0,1,theday);
                    }
                }
            }
        });

    button_forwards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (m_taskDoneCheck.isChecked() == false && m_task_notDone_Check.isChecked() == false) {
                    Toast t = Toast.makeText(getActivity(), "يجب اختيار مهمه اليوم", Toast.LENGTH_SHORT);
                    LinearLayout toastLayout = (LinearLayout) t.getView();
                    TextView toastTv = (TextView) toastLayout.getChildAt(0);
                    toastTv.setTextSize(25);
                    t.show();

                } else {
                   DayNumber = Integer.parseInt(String.valueOf(mDate_textView.getText()));
                    theday= Integer.parseInt(String.valueOf(mDate_textView.getText()));
                    if (theday ==15){
                        Log.d("daynumberrrrrrr", "Day numberrrr "+theday);


                        new BestMomDialogue().show(getChildFragmentManager(),BestMomDialogue.TAG);
                    }
                    if (DayNumber == 30)//(int) dataModels.size() - 1
                    {
                        DayNumber = 1;
                    } else {
                        DayNumber++;
                    }
                   // mProgressTextView.setText(String.valueOf(dataModels.get(j).getProgress_Name()));
                    //mDate_textView.setText(String.valueOf(dataModels.get(j).getMdayNumber()));
                    relativeLayout.setBackground(getActivity().getDrawable(myimageList[i]));
                    if (i == (int) myimageList.length - 1) {
                        i = 0;
                    } else {
                        i++;
                    }
                    //Toast.makeText(getActivity(), "mday is"+DayNumber, Toast.LENGTH_SHORT).show();

                    if (m_taskDoneCheck.isChecked() && !m_task_notDone_Check.isChecked()){
                        viewModelData.UpdateProgressData(1,0,theday);
                    }
                    if (!m_taskDoneCheck.isChecked() && m_task_notDone_Check.isChecked()){
                        viewModelData.UpdateProgressData(0,1,theday);
                    }
                }
            }
        });
    }
        }



   