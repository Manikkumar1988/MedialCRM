package challenge.rt.medibus.mani.data.source.remote;

import android.arch.lifecycle.LiveData;
import challenge.rt.medibus.mani.Injection;
import challenge.rt.medibus.mani.data.SalesRepDetail;
import challenge.rt.medibus.mani.data.TaskDetail;
import challenge.rt.medibus.mani.data.source.SalesRepDataSource;
import challenge.rt.medibus.mani.data.source.TaskDataSource;
import challenge.rt.medibus.mani.data.source.remote.myApi.MyApi;
import challenge.rt.medibus.mani.data.source.remote.myApi.model.SalesRep;
import challenge.rt.medibus.mani.util.Util;
import java.io.IOException;
import java.util.List;

/**
 * Created by mani on 21/12/17.
 */

public class SalesRepRemoteDataSource implements SalesRepDataSource, TaskDataSource {

  private static SalesRepRemoteDataSource INSTANCE;

  MyApi myAoiService;

  @Override public void addSalesRep(SalesRepDetail salesRepDetail) {
    try {
      myAoiService.insertSalesRep(new SalesRep(salesRepDetail)).execute();
    } catch (IOException e) {
      Util.printStacktrace(e);
    }
  }

  @Override public void deleteSalesRep(SalesRepDetail salesRepDetail) {

  }

  @Override public LiveData<List<SalesRepDetail>> getSalesRepList() {
    try {
      List<SalesRep> salesRepList = myAoiService.listSalesRep().execute().getItems();
      Util.Logger("returned from servver: "+salesRepList.size());
    } catch (IOException e) {
      Util.printStacktrace(e);
      throw new IllegalStateException("Exception when listing the notes " + e.getMessage());
    }

    return null;
  }

  public static SalesRepRemoteDataSource getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new SalesRepRemoteDataSource();
      INSTANCE.myAoiService = Injection.proviceMyApi();
    }
    return INSTANCE;
  }

  // Prevent direct instantiation.
  private SalesRepRemoteDataSource() {}

  @Override public void addSalesRep(TaskDetail taskDetail) {
    try {
      myAoiService.insertTask(new challenge.rt.medibus.mani.data.source.remote.myApi.model.TaskDetail(taskDetail)).execute();
    } catch (IOException e) {
      Util.printStacktrace(e);
    }
  }

  @Override public LiveData<List<TaskDetail>> getTaskDetail() {
    try {
      List<challenge.rt.medibus.mani.data.source.remote.myApi.model.TaskDetail> salesRepList = myAoiService.listTasks().execute().getItems();
      Util.Logger("returned from servver: "+salesRepList.size());
    } catch (IOException e) {
      Util.printStacktrace(e);
      throw new IllegalStateException("Exception when listing the notes " + e.getMessage());
    }

    return null;
  }
}
