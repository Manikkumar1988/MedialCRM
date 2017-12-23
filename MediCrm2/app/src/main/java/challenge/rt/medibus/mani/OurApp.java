package challenge.rt.medibus.mani;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by mani on 20/12/17.
 */

public class OurApp extends MultiDexApplication {
  @Override public void onCreate() {
    super.onCreate();
  }

  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
  }
}
