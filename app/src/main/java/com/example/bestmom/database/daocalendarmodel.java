package com.example.bestmom.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bestmom.ui.models.CalenderDataModel;
import com.example.bestmom.ui.models.DataModel;

import java.util.List;

@Dao
public interface daocalendarmodel {

        @Insert
        void insertCalendarData(CalenderDataModel calenderDataModel);

        @Update
        void updateCalendarData(CalenderDataModel calenderDataModel);

        @Query("DELETE FROM CALENDARDATA_TABLE")
        void deleteAllPCalendarData();

        @Query(("SELECT * FROM CALENDARDATA_TABLE"))
       LiveData<List<CalenderDataModel>>  selectAllCalendarData();


}
