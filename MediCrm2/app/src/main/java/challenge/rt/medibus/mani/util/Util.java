package challenge.rt.medibus.mani.util;

import android.util.Log;

public class Util {

  public static void Logger(String text) {
    Logger("DEV123", text);
  }

  public static void Logger(String tag, String text) {
    Log.d(tag, text);
  }

  public static void printStacktrace(Exception e) {
        /*if(e instanceof IOException) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    OttoBus.getInstance().post(new ErrorEvent("Network Error", "Please check your network connectivity"));
                }
            });
        }*/
    Logger("ERROR Occurred - HANDLED exception");
    e.printStackTrace();
    Logger("***");
    //throw new RuntimeException();
  }
}
