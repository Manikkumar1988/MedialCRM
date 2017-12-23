package challenge.rt.medibus.mani.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.io.Serializable;

import static challenge.rt.medibus.mani.data.TaskDetail.TABLE_NAME;

/**
 * Created by mani on 20/12/17.
 */

@Entity(tableName = TABLE_NAME)
public class TaskDetail implements Serializable{
  public static final String TABLE_NAME = "task_table";
  @PrimaryKey
  public String taskName;
  public String taskDescription;
  public long startDate, endDate;
  public int taskStatus; //Save as enum
  public String salesRep;
  public String pharmacyName;

}
