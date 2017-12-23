package challenge.rt.medibus.mani.data.source.local.TaskRepository;

import android.arch.lifecycle.LiveData;
import challenge.rt.medibus.mani.data.TaskDetail;
import challenge.rt.medibus.mani.data.source.TaskDataSource;
import challenge.rt.medibus.mani.data.source.local.UserDatabase;
import java.util.List;

/**
 * Created by mani on 20/12/17.
 */

public class TaskLocalDataSource implements TaskDataSource{

  private static TaskLocalDataSource INSTANCE;

  UserDatabase userDatabase;

  public TaskLocalDataSource(UserDatabase userDatabase) {
    this.userDatabase = userDatabase;
  }
  
  @Override public void addSalesRep(TaskDetail taskDetail) {
    userDatabase.taskRepDao().addTaskDetail(taskDetail);
  }

  @Override public LiveData<List<TaskDetail>> getTaskDetail() {
    return userDatabase.taskRepDao().getTaskList();
  }

  public static TaskLocalDataSource getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new TaskLocalDataSource();
    }
    return INSTANCE;
  }

  // Prevent direct instantiation.
  private TaskLocalDataSource() {}
}
