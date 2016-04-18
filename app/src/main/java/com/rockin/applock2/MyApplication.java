package com.rockin.applock2;

import android.app.Application;
import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

@ReportsCrashes(formKey="", formUri="https://apps.sam-platform.com/53fb2c818957dfa8038b4576/53fba7338957dfa7038b4582/acra")
public class MyApplication
  extends Application
{
  public void onCreate()
  {
    ACRA.init(this);
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\MyApplication.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */