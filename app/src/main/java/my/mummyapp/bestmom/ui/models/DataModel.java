package my.mummyapp.bestmom.ui.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "progress")
public class DataModel {
    @PrimaryKey(autoGenerate = true)
       public int id;
       private int mdayNumber;
    private    int is_task_done;
    private int is_task_not_done;
    private  String Progress_Name;




    public DataModel(int mdayNumber, int is_task_done, int is_task_not_done, String Progress_Name) {
        this.mdayNumber = mdayNumber;
        this.is_task_done= is_task_done;
        this.is_task_not_done = is_task_not_done;
        this.Progress_Name=Progress_Name;


    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getMdayNumber() {
        return mdayNumber;
    }

    public int getIs_task_done() {
        return is_task_done;
    }

    public int getIs_task_not_done() {
        return is_task_not_done;
    }

    public String getProgress_Name() {
        return Progress_Name;
    }


    }



