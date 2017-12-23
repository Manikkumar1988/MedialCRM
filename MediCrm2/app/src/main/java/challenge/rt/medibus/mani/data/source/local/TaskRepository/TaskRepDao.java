package challenge.rt.medibus.mani.data.source.local.TaskRepository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import challenge.rt.medibus.mani.data.SalesRepDetail;
import challenge.rt.medibus.mani.data.TaskDetail;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by mani on 20/12/17.
 */

@Dao
public interface TaskRepDao {
  @Query("SELECT * FROM " + TaskDetail.TABLE_NAME) LiveData<List<TaskDetail>> getTaskList();

  @Insert(onConflict = OnConflictStrategy.REPLACE) void addTaskDetail(TaskDetail taskDetail);
}
