package com.example.bestmom.ui;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bestmom.database.DataModelDatabase;
import com.example.bestmom.database.Repository;
import com.example.bestmom.ui.models.CalenderDataModel;
import com.example.bestmom.ui.models.DataModel;

import java.io.File;
import java.util.List;

public class ViewModelData extends AndroidViewModel {
    private Repository repository;
    private int i;
    private LiveData<List<CalenderDataModel>> allCalendarData;
     Boolean ifexists;
    private LiveData<List<DataModel>> allProgressData;
    private String[] listNames;
    private DataModelDatabase database;
    LiveData<Integer> count;

    public ViewModelData(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        database =DataModelDatabase.getInstance(getApplication());
        count= database.daoProgressData().countdays();
        allProgressData = database.daoProgressData().selectAllProgress();
        //ifexists = doesdatabaseexists(getApplication().getApplicationContext(),"Progress_database");
       // allCalendarData = repository.selectallCalendarData();

    }

    public void deleteAllProgress() {
        repository.deleteallprogress();
    }

    public void UpdateProgressData(int istaskDone, int isTasknotDone, int idProgress) {
        repository.updateProgress(istaskDone, isTasknotDone, idProgress);
    }

    public void deleteAllCalendars() {
        repository.deleteallcalendar();
    }

    public LiveData<List<DataModel>> getAllProgressData() {
        return allProgressData;
    }

    public LiveData<List<CalenderDataModel>> getAllCalendarData() {

        return allCalendarData;
    }
private static boolean doesdatabaseexists(Context context , String dbname ){


    File dbfile = context.getDatabasePath(dbname);
    return dbfile.exists();
}
    public String[] getProgressList() {

        for (i = 0; i == 30; i++) {
            listNames[i] = allProgressData.getValue().get(i).getProgress_Name();
        }
        return listNames;
    }

    /*

            <LinearLayout
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="30dp"
                android:layout_marginTop="40dp"
                android:orientation="vertical">

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="التطور"
                    android:id="@+id/text_tasks"
                    android:textSize="30sp"
                    android:textStyle="bold" />

                <ImageView
                    android:layout_width="15dp"
                    android:layout_height="15dp"
                    android:id="@+id/tasksDot"
                    android:layout_marginStart="30dp"
                    android:src="@drawable/blue_dot" />

            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginRight="100dp"
                android:layout_marginTop="40dp"
                android:orientation="vertical">

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="المهام"
                    android:paddingLeft="60dp"
                    android:id="@+id/text_progress"
                    android:textSize="30sp"
                    android:textStyle="bold" />

                <ImageView
                    android:layout_width="15dp"
                    android:layout_height="15dp"
                    android:layout_marginStart="85dp"
                    android:id="@+id/ProgressDot"
                    android:src="@drawable/blue_dot" />


            </LinearLayout>
     */



}
