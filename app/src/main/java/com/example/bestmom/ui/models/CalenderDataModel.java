package com.example.bestmom.ui.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="calendardata_table")
public class CalenderDataModel
{
    @PrimaryKey(autoGenerate = true)
   public    int id ;
   private int date_count;
    private int days_bg;
    private boolean is_task_done;
     private   boolean is_task_not_done;

    public int getDate_count() {
        return date_count;
    }

    public int getDays_bg() {
        return days_bg;
    }

    public boolean isIs_task_done() {
        return is_task_done;
    }

    public boolean isIs_task_not_done() {
        return is_task_not_done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CalenderDataModel(int date_count, int days_bg, boolean is_task_done, boolean is_task_not_done) {
        this.date_count = date_count;
        this.days_bg = days_bg;
        this.is_task_done = is_task_done;
        this.is_task_not_done = is_task_not_done;
    }
}
