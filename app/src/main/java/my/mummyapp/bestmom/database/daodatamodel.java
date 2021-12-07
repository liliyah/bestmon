package my.mummyapp.bestmom.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import my.mummyapp.bestmom.ui.models.DataModel;

import java.util.List;

@Dao
public interface daodatamodel {

    @Insert
    void insertProgress (DataModel dataModel);

    @Query("Update progress SET is_task_done =:isTaskDone , is_task_not_done = :isTasknotDone  WHERE mdayNumber= :taskid")
    void updateProgress (int isTaskDone,int isTasknotDone ,int taskid );

    @Query("Update progress SET is_task_done =0, is_task_not_done = 0")
    void  deleteAllProgress ();

    @Query("SELECT * FROM progress")
    LiveData<List<DataModel>> selectAllProgress();
    @Query("SELECT COUNT (mdayNumber) FROM progress")
       LiveData<Integer>  countdays();
}
