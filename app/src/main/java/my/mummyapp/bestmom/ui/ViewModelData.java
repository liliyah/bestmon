package my.mummyapp.bestmom.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import my.mummyapp.bestmom.database.Repository;
import my.mummyapp.bestmom.ui.models.DataModel;

import java.util.List;

public class ViewModelData extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<DataModel>> allProgressData;


    public ViewModelData(@NonNull Application application) {
        super(application);
        repository = new Repository(application);

    }

    public void deleteAllProgress() {
        repository.deleteallprogress();
    }

    public void UpdateProgressData(int istaskDone, int isTasknotDone, int idProgress) {
        repository.updateProgress(istaskDone, isTasknotDone, idProgress);
    }

       public LiveData<List<DataModel>> getAllProgressData() {
        allProgressData = repository.selectallProgressData();

        return allProgressData;
    }


}
