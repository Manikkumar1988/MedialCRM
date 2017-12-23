package challenge.rt.medibus.mani.ui.home;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import challenge.rt.medibus.mani.data.SalesRepDetail;
import challenge.rt.medibus.mani.data.TaskDetail;
import challenge.rt.medibus.mani.data.source.SalesRepDataSource;
import challenge.rt.medibus.mani.data.source.TaskDataSource;
import java.util.List;

/**
 * Created by mani on 20/12/17.
 */

public class HomeViewModel extends AndroidViewModel {

  private final SalesRepDataSource mProductsDatasource;

  private final TaskDataSource mTaskDataSource;

  public HomeViewModel(Application application, SalesRepDataSource salesRepDataSource,TaskDataSource taskDataSource) {
    super(application);

    mProductsDatasource = salesRepDataSource;

    mTaskDataSource = taskDataSource;
  }

  public LiveData<List<SalesRepDetail>> getSalesRepList() {
    return mProductsDatasource.getSalesRepList();
  }

  public LiveData<List<TaskDetail>> getTaskList() {
    return mTaskDataSource.getTaskDetail();
  }
}
