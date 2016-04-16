package content.only.skeleton;

import android.app.Service;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import content.only.skeleton.util.Globals;
import content.only.skeleton.util.WebWeb;

public class AppDataUpdateService
  extends Service
{
  public static long milliSecondsInAMinute = 60000L;
  private Handler handler;
  private long milliSecondsInAnHour = 3600000L;
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    this.handler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        super.handleMessage(paramAnonymousMessage);
        if (Integer.valueOf(Build.VERSION.SDK).intValue() >= 9) {}
        try
        {
          StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
          if (WebWeb.isNewMetaDataAvailable(AppDataUpdateService.this)) {
            WebWeb.fetchAppMetaData(AppDataUpdateService.this);
          }
          if (Globals.getDayCount(AppDataUpdateService.this) == 0) {
            Globals.showAdNotification(AppDataUpdateService.this);
          }
          Globals.setDayCount(AppDataUpdateService.this, Globals.getDayCount(AppDataUpdateService.this) + 1);
          if (Globals.getDayCount(AppDataUpdateService.this) == 2) {
            Globals.setDayCount(AppDataUpdateService.this, 0);
          }
          return;
        }
        catch (Exception paramAnonymousMessage)
        {
          for (;;) {}
        }
      }
    };
    new Thread(null, new ServiceWorker(), "BackgroundService").start();
  }
  
  class ServiceWorker
    implements Runnable
  {
    ServiceWorker() {}
    
    public void run()
    {
      for (;;)
      {
        if (1 == 0) {
          return;
        }
        long l = AppDataUpdateService.this.milliSecondsInAnHour;
        try
        {
          Thread.sleep(l * 24L);
          Message localMessage = new Message();
          AppDataUpdateService.this.handler.sendMessage(localMessage);
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;)
          {
            localInterruptedException.printStackTrace();
          }
        }
      }
    }
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\content\only\skeleton\AppDataUpdateService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */