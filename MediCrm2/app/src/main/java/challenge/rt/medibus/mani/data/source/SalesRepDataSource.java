package challenge.rt.medibus.mani.data.source;

import android.arch.lifecycle.LiveData;
import challenge.rt.medibus.mani.data.SalesRepDetail;
import java.util.List;

/**
 * Created by mani on 20/12/17.
 */

public interface SalesRepDataSource {
  void addSalesRep(SalesRepDetail salesRepDetail);
  void deleteSalesRep(SalesRepDetail salesRepDetail);
  LiveData<List<SalesRepDetail>> getSalesRepList();
}
