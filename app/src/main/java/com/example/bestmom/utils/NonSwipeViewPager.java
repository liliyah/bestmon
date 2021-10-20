package com.example.bestmom.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bestmom.ui.fragments.CalenderFragment;
import com.example.bestmom.ui.fragments.ProgressFragment;

import java.util.ArrayList;

public class NonSwipeViewPager   extends FragmentStateAdapter {
    private ArrayList<Fragment> arrayList = new ArrayList<>();

    public NonSwipeViewPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        arrayList.add(new ProgressFragment());
        arrayList.add(new CalenderFragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return  new ProgressFragment();
            case  1:
                return  new CalenderFragment();
            default:
                return  new ProgressFragment();
        }
    }




    /*
    <androidx.cardview.widget.CardView
    android:layout_width="300dp"
    android:layout_marginTop="170dp"
    app:cardCornerRadius="20dp"
    android:layout_marginLeft="50dp"
    android:layout_height="400dp">
     */

    @Override
    public int getItemCount() {
        return 2;
    }

}
