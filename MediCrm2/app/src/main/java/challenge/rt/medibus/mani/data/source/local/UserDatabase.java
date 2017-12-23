package challenge.rt.medibus.mani.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import challenge.rt.medibus.mani.data.SalesRepDetail;
import challenge.rt.medibus.mani.data.TaskDetail;
import challenge.rt.medibus.mani.data.source.local.SalesRepRepository.SalesRepDao;
import challenge.rt.medibus.mani.data.source.local.TaskRepository.TaskRepDao;

@Database(entities = { SalesRepDetail.class, TaskDetail.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {
 
   public abstract SalesRepDao salesRepDao();

   public abstract TaskRepDao taskRepDao();
 
}