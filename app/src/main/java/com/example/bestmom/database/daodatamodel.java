package com.example.bestmom.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bestmom.ui.models.CalenderDataModel;
import com.example.bestmom.ui.models.DataModel;

import java.util.List;

@Dao
public interface daodatamodel {

    @Insert
    void insertProgress (DataModel dataModel);

    @Query("Update progress SET is_task_done =:isTaskDone , is_task_not_done = :isTasknotDone  WHERE mdayNumber= :taskid")
    void updateProgress (int isTaskDone,int isTasknotDone ,int taskid );

    @Query("DELETE FROM progress")
    void  deleteAllProgress ();

    @Query("SELECT * FROM progress")
    LiveData<List<DataModel>> selectAllProgress();
    @Query("SELECT COUNT (mdayNumber) FROM progress")
       LiveData<Integer>  countdays();
}
