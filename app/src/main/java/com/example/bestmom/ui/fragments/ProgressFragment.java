package com.example.bestmom.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.bestmom.R;
import com.example.bestmom.ui.ViewModelData;
import com.example.bestmom.ui.models.DataModel;

import java.util.List;

public class ProgressFragment  extends Fragment {
    private ImageView button_forwards;
    private ImageView button_backwards;
    private RelativeLayout relativeLayout;
    private int j = 0;
    int i = 0;
    private LiveData<List<DataModel>> progressData;
    private ViewModelData viewModelData;
    private TextView mDate_textView, mProgressTextView;
    private RadioButton m_taskDoneCheck, m_task_notDone_Check;
    String[] progress_Textes;
    int mday;
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
        viewModelData.getAllProgressData().observe(getViewLifecycleOwner(), new Observer<List<DataModel>>() {
            @Override
            public void onChanged(List<DataModel> dataModels) {
                mProgressTextView.setText(String.valueOf(dataModels.get(j).getProgress_Name()));
                mDate_textView.setText(String.valueOf(dataModels.get(j).getMdayNumber()));
                if(dataModels.get(j).getIs_task_done()==1 && dataModels.get(j).getIs_task_not_done()==0){
                    m_taskDoneCheck.setChecked(true);
                }
                if(dataModels.get(j).getIs_task_done()==0 && dataModels.get(j).getIs_task_not_done()==1){
                    m_task_notDone_Check.setChecked(true);
                }

                if(dataModels.get(j).getIs_task_done()==0 && dataModels.get(j).getIs_task_not_done()==0){
                    m_task_notDone_Check.setChecked(false);
                    m_taskDoneCheck.setChecked(false);
                }

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
                            int DayNumber = Integer.parseInt(String.valueOf(mDate_textView.getText()));

                            mProgressTextView.setText(String.valueOf(dataModels.get(j).getProgress_Name()));
                            mDate_textView.setText(String.valueOf(dataModels.get(j).getMdayNumber()));
                            relativeLayout.setBackground(getActivity().getDrawable(myimageList[i]));
                            if (i == (int) myimageList.length - 1) {
                                i = 0;
                            } else {
                                i++;
                            }

                            if (m_taskDoneCheck.isChecked() == true && m_task_notDone_Check.isChecked() == false){
                                viewModelData.UpdateProgressData(1,0,DayNumber);
                            }
                            if (m_taskDoneCheck.isChecked() == false && m_task_notDone_Check.isChecked() == true){
                                viewModelData.UpdateProgressData(0,1,DayNumber);
                            }
                            if (j == (int) dataModels.size() - 1) {
                                j = 0;
                            } else {
                                j++;
                            }
                        }
                    }
                });

                button_backwards.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(dataModels.get(j).getIs_task_done()==1 && dataModels.get(j).getIs_task_not_done()==0){
                            m_taskDoneCheck.setChecked(true);

                        }
                        if(dataModels.get(j).getIs_task_done()==0 && dataModels.get(j).getIs_task_not_done()==1){
                            m_task_notDone_Check.setChecked(true);
                        }

                            relativeLayout.setBackground(getActivity().getDrawable(myimageList[i]));
                            if (i == (int) myimageList.length - 1) {
                                i = 0;
                            } else {
                                i++;
                            }
                            j= Integer.parseInt(String.valueOf(mDate_textView.getText()));
                            if(j==0){
                                j= (int) dataModels.size() - 1;

                            }else{
                                j--;
                            }
                            //Toast.makeText(getActivity(), "mday is"+j, Toast.LENGTH_SHORT).show();
                            mDate_textView.setText(String.valueOf(j));
                            mProgressTextView.setText(String.valueOf(dataModels.get(j).getProgress_Name()));
                        if (m_taskDoneCheck.isChecked() == true && m_task_notDone_Check.isChecked() == false){
                            viewModelData.UpdateProgressData(1,0,j);
                        }
                        if (m_taskDoneCheck.isChecked() == false && m_task_notDone_Check.isChecked() == true){
                            viewModelData.UpdateProgressData(0,1,j);
                        }
                                                    }

                });
            }
        });
    }
}
   