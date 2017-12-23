package challenge.rt.medibus.mani.ui.taskscreate;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import challenge.rt.medibus.mani.Injection;
import challenge.rt.medibus.mani.data.SalesRepDetail;
import challenge.rt.medibus.mani.data.TaskDetail;
import challenge.rt.medibus.mani.data.source.TaskDataSource;
import challenge.rt.medibus.mani.data.source.remote.myApi.MyApi;
import challenge.rt.medibus.mani.ui.salescreate.SalesCreateViewModel;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.tasks.Task;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import java.io.IOException;

/**
 * Created by mani on 20/12/17.
 */

public class TaskCreateViewModel extends AndroidViewModel {

  public TaskCreateViewModel(Application application,TaskDataSource taskDataSource) {
    super(application);
    this.taskDataSource = taskDataSource;
  }

  private TaskDetail taskDetail;

  private TaskDataSource taskDataSource;

  public void setTaskDetail(TaskDetail taskDetail) {
    this.taskDetail = taskDetail;
  }


  public void setName(String name) {
    taskDetail.taskName = name;
  }

  public void setTaskDescription(String taskDescription) {
    taskDetail.taskDescription = taskDescription;
  }

  public void setAssigned(String assigned) {
    taskDetail.salesRep = assigned;
  }

  public void setStatus(int status) {
    taskDetail.taskStatus = status;
  }

  public void setStartDate(long startDate) {
    taskDetail.startDate = startDate;
  }

  public void setEndDate(long endDate) {
    taskDetail.endDate = endDate;
  }

  public void setPharmacyName(String pharmacyName) {
    taskDetail.pharmacyName = pharmacyName;
  }


  public String getName() {
    return taskDetail.taskName;
  }

  public String getTaskDescription() {
    return taskDetail.taskDescription;
  }

  public long getStartDate() {
    return taskDetail.startDate;
  }

  public long getEndDate() {
    return taskDetail.endDate;
  }

  public String getAssigned() {
    return taskDetail.salesRep;
  }

  public String getPharmacyName() {
    return taskDetail.pharmacyName;
  }

  public int getStatus() {
    return taskDetail.taskStatus;
  }


  public void save() {
    new Thread(){
      @Override public void run() {
        super.run();

        Injection.proviceTasksDetailRemoteDataSource().addSalesRep(taskDetail);
        taskDataSource.addSalesRep(taskDetail);

      }
    }.start();
  }
}
