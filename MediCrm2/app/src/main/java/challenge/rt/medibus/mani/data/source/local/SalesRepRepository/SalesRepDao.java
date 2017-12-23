package challenge.rt.medibus.mani.data.source.local.SalesRepRepository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import challenge.rt.medibus.mani.data.SalesRepDetail;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by mani on 20/12/17.
 */

@Dao
public interface SalesRepDao {
  @Query("SELECT * FROM " + SalesRepDetail.TABLE_NAME) LiveData<List<SalesRepDetail>> getSalesRepList();

  @Insert(onConflict = OnConflictStrategy.REPLACE) void addSalesRep(SalesRepDetail salesRepDetail);

  @Delete void deleteSalesRep(SalesRepDetail cart);

  @Update(onConflict = REPLACE) void updateSalesRep(SalesRepDetail cart);
}
