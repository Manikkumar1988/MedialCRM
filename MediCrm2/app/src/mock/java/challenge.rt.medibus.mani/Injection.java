package challenge.rt.medibus.mani;

import android.arch.persistence.room.Room;
import android.content.Context;
import challenge.rt.medibus.mani.data.source.SalesRepDataSource;
import challenge.rt.medibus.mani.data.source.TaskDataSource;
import challenge.rt.medibus.mani.data.source.local.SalesRepRepository.SalesRepLocalDataSource;
import challenge.rt.medibus.mani.data.source.local.TaskRepository.TaskLocalDataSource;
import challenge.rt.medibus.mani.data.source.local.UserDatabase;
import challenge.rt.medibus.mani.data.source.remote.SalesRepRemoteDataSource;
import challenge.rt.medibus.mani.data.source.remote.TaskDetailRemoteDataSource;
import challenge.rt.medibus.mani.data.source.remote.myApi.MyApi;
import challenge.rt.medibus.mani.data.source.remote.myApi.model.SalesRep;
import challenge.rt.medibus.mani.util.Util;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import java.io.IOException;
import java.util.List;

/**
 * Created by mani on 20/12/17.
 */

public class Injection {
  public static SalesRepDataSource provideSalesRepLocalDataSource(Context context) {
    return new SalesRepLocalDataSource(provideUserDatabase(context));
  }

  public static TaskDataSource provideTaskLocalDataSource(Context context) {
    return new TaskLocalDataSource(provideUserDatabase(context));
  }

  public static UserDatabase provideUserDatabase(Context context) {

    return Room.databaseBuilder(context, UserDatabase.class, "user_db").build();
  }

  public static SalesRepDataSource proviceSalesRepRemoteDataSource() {
    proviceMyApi();
    return SalesRepRemoteDataSource.getInstance();
  }

  public static TaskDataSource proviceTasksDetailRemoteDataSource() {
    proviceMyApi();
    return TaskDetailRemoteDataSource.getInstance();
  }

  static MyApi myAoiService = null;

  public static MyApi proviceMyApi() {
    if(myAoiService == null) {
      MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
          new AndroidJsonFactory(), null)
          .setRootUrl("https://nth-victory-615.appspot.com/_ah/api/")
          .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
            @Override
            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws
                IOException {
              abstractGoogleClientRequest.setDisableGZipContent(true);
            }
          });

      myAoiService = builder.build();
    }

    /*try {
      List<SalesRep> salesRepList = myAoiService.listSalesRep().execute().getItems();
      Util.Logger("returned from servver: "+salesRepList.size());
    } catch (IOException e) {
      throw new IllegalStateException("Exception when listing the notes " + e.getMessage());
    }*/
    return myAoiService;

  }
}
