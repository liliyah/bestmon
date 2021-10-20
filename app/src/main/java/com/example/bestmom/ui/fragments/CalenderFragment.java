package com.example.bestmom.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestmom.R;
import com.example.bestmom.adapter.CalenderAdapter;
import com.example.bestmom.ui.ViewModelData;
import com.example.bestmom.ui.models.DataModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalenderFragment  extends Fragment {
private RecyclerView recyclerView;
private CalenderAdapter madapter;
    private ViewModelData viewModelData;
private List<DataModel>  datamodels = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calender,container,false);



        recyclerView = (RecyclerView) v.findViewById(R.id.calendar_recycler);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelData = ViewModelProviders.of(getActivity()).get(ViewModelData.class);
viewModelData.getAllProgressData().observe(getViewLifecycleOwner(), new Observer<List<DataModel>>() {
    @Override
    public void onChanged(List<DataModel> dataModels) {
        madapter= new CalenderAdapter(dataModels,getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),5));
        recyclerView.setAdapter(madapter);
    }
});
    }
}

