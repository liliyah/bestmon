package my.mummyapp.bestmom.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import my.mummyapp.bestmom.ui.models.DataModel;

import java.util.List;

public class Repository {

    private daodatamodel daodatamodel;
    private LiveData<List<DataModel>> ProgressData;
    LiveData<Integer> count;

    public Repository(Application application) {
        DataModelDatabase database = DataModelDatabase.getInstance(application);
        daodatamodel = database.daoProgressData();
        count= database.daoProgressData().countdays();
        ProgressData = daodatamodel.selectAllProgress();
    }
    public void insetProgress(DataModel progress) {
        new insertProgressData(daodatamodel).execute(progress);
    }

     public void deleteallprogress() {
        new DeleteAllProgressData(daodatamodel).execute();
    }

    public static class DeleteAllProgressData extends AsyncTask<Void, Void, Void> {
        private daodatamodel daodatamodel;

        private DeleteAllProgressData(daodatamodel daoData) {
            this.daodatamodel = daoData;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            daodatamodel.deleteAllProgress();
            return null;
        }
    }

   public LiveData<List<DataModel>> selectallProgressData() {

        return ProgressData;
    }
    public static class insertProgressData extends AsyncTask<DataModel, Void, Void> {
        private daodatamodel daodatamodel;

        private insertProgressData(daodatamodel daoData) {
            this.daodatamodel = daoData;
        }

        @Override
        protected Void doInBackground(DataModel... DataModels) {

            daodatamodel.insertProgress(DataModels[0]);

            return null;
        }
    }
           public void updateProgress(int is_task_done, int is_task_not_done, int task_id) {
            MyTaskParams myTaskParams = new MyTaskParams(is_task_done,is_task_not_done,task_id);
            new UpdateProgressData(daodatamodel).execute(myTaskParams);
        }

        private static class MyTaskParams {
        int taskDone,taskNotDone,taskid;
        MyTaskParams(int isTaskDone,int istasknotDone,int taskid){
            this.taskDone= isTaskDone;
            this.taskNotDone= istasknotDone;
            this.taskid =taskid;
        }
        }

        public static class UpdateProgressData extends AsyncTask<MyTaskParams, Void, Void> {
            private daodatamodel daodatamodel;


            private UpdateProgressData(daodatamodel daoData) {
                this.daodatamodel = daoData;
            }
            @Override
            protected Void doInBackground(MyTaskParams... params) {
               int istaskdone =params[0].taskDone;
               int istasknotdone = params[0].taskNotDone;
               int id = params[0].taskid;
                daodatamodel.updateProgress(istaskdone, istasknotdone, id);
                return null;

            }
        }
    }




