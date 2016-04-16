package com.rockin.applock2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;

public class SplashScreen
  extends Activity
{
  String form;
  SharedPreferences sp;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2130903055);
    setRequestedOrientation(1);
    this.sp = getSharedPreferences("com.rockin.applock2", 0);
    new Thread()
    {
      public void run()
      {
        super.run();
        try
        {
          Thread.sleep(1000L);
          Intent localIntent = new Intent("chooserapplock");
          localIntent.setFlags(67108864);
          SplashScreen.this.startActivity(localIntent);
          SplashScreen.this.finish();
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;) {}
        }
      }
    }.start();
  }
  
  protected void onPause()
  {
    super.onPause();
    finish();
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\SplashScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */