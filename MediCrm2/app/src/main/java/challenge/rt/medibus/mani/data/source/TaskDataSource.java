package challenge.rt.medibus.mani.data.source;

import android.arch.lifecycle.LiveData;
import challenge.rt.medibus.mani.data.SalesRepDetail;
import challenge.rt.medibus.mani.data.TaskDetail;
import java.util.List;

/**
 * Created by mani on 20/12/17.
 */

public interface TaskDataSource {
  void addSalesRep(TaskDetail taskDetail);
  LiveData<List<TaskDetail>> getTaskDetail();
}
