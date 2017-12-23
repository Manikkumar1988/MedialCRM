package challenge.rt.medibus.mani.data.source.local.SalesRepRepository;

import android.arch.lifecycle.LiveData;
import challenge.rt.medibus.mani.data.SalesRepDetail;
import challenge.rt.medibus.mani.data.source.SalesRepDataSource;
import challenge.rt.medibus.mani.data.source.local.UserDatabase;
import java.util.List;

/**
 * Created by mani on 20/12/17.
 */

public class SalesRepLocalDataSource implements SalesRepDataSource {

  private static SalesRepLocalDataSource INSTANCE;

  UserDatabase userDatabase;

  public SalesRepLocalDataSource(UserDatabase userDatabase) {
    this.userDatabase = userDatabase;
  }

  @Override public void addSalesRep(SalesRepDetail salesRepDetail) {
    userDatabase.salesRepDao().addSalesRep(salesRepDetail);
  }

  @Override public void deleteSalesRep(SalesRepDetail salesRepDetail) {
    userDatabase.salesRepDao().deleteSalesRep(salesRepDetail);
  }

  @Override public LiveData<List<SalesRepDetail>> getSalesRepList() {
    return userDatabase.salesRepDao().getSalesRepList();
  }

  public static SalesRepLocalDataSource getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new SalesRepLocalDataSource();
    }
    return INSTANCE;
  }

  // Prevent direct instantiation.
  private SalesRepLocalDataSource() {}
}
