package com.rockin.applock2;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ServiceAppLock
  extends Service
{
  static ArrayList<String> selectappstrack;
  static ArrayList<App> selectedappslist;
  ActivityManager am;
  SharedPreferences.Editor e;
  int error = 0;
  HelperSelectedApps hsa;
  String locktype;
  PackageManager pm;
  ArrayList<String> runningappnames;
  List<ActivityManager.RunningTaskInfo> runningtasks;
  SharedPreferences sp;
  
  public void getRunningAppNames()
    throws Exception
  {
    Iterator localIterator = this.runningtasks.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      Object localObject = ((ActivityManager.RunningTaskInfo)localIterator.next()).topActivity.getPackageName();
      localObject = this.pm.getApplicationInfo((String)localObject, 0);
      this.runningappnames.add(((ApplicationInfo)localObject).loadLabel(this.pm).toString());
    }
  }
  
  public void getSelectedAppsList()
    throws Exception
  {
    selectedappslist = new ArrayList();
    Cursor localCursor = this.hsa.display();
    new ArrayList();
    PackageManager localPackageManager = getPackageManager();
    List localList = localPackageManager.getInstalledApplications(128);
    int i = localCursor.getColumnIndex("PackageName");
    localCursor.moveToFirst();
    if (localCursor.isAfterLast()) {
      return;
    }
    String str = localCursor.getString(i);
    Iterator localIterator = localList.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        localCursor.moveToNext();
        break;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (str.equals(localApplicationInfo.packageName))
      {
        App localApp = new App();
        localApp.setName(localApplicationInfo.loadLabel(localPackageManager).toString());
        localApp.setIcon(localApplicationInfo.loadIcon(localPackageManager));
        localApp.setPackageName(localApplicationInfo.packageName);
        selectedappslist.add(localApp);
      }
    }
  }
  
  public void isSelectedAppRunning()
    throws Exception
  {
    Iterator localIterator = selectedappslist.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      Object localObject = (App)localIterator.next();
      int i = selectedappslist.indexOf(localObject);
      if (this.runningappnames.contains(((App)localObject).getName().toString()))
      {
        if (((String)selectappstrack.get(i)).equals("0"))
        {
          selectappstrack.set(i, "1");
          localObject = null;
          if (this.locktype.equals("string")) {
            localObject = "stringlockapplock";
          }
          for (;;)
          {
            localObject = new Intent((String)localObject);
            ((Intent)localObject).setFlags(268435456);
            Bundle localBundle = new Bundle();
            localBundle.putString("fromservice", "true");
            localBundle.putString("index", String.valueOf(i));
            ((Intent)localObject).putExtras(localBundle);
            startActivity((Intent)localObject);
            break;
            if (this.locktype.equals("pattern")) {
              localObject = "patternlockapplock";
            } else if (this.locktype.equals("gesture")) {
              localObject = "gesturecheckerapplock";
            } else if (this.locktype.equals("numeric")) {
              localObject = "numericlockapplock";
            }
          }
        }
      }
      else if (!((ActivityManager.RunningTaskInfo)this.runningtasks.get(0)).topActivity.getPackageName().equals("com.rockin.applock2")) {
        selectappstrack.set(i, "0");
      }
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    startService(new Intent(this, ServiceAppLock.class));
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    try
    {
      this.sp = getSharedPreferences("com.rockin.applock2", 0);
      this.e = this.sp.edit();
      this.hsa = new HelperSelectedApps(this);
      this.am = ((ActivityManager)getSystemService("activity"));
      this.pm = getPackageManager();
      selectappstrack = new ArrayList();
      getSelectedAppsList();
      paramInt1 = 0;
      for (;;)
      {
        if (paramInt1 >= selectedappslist.size())
        {
          new Timer().scheduleAtFixedRate(new TimerTask()
          {
            public void run()
            {
              try
              {
                ServiceAppLock.this.locktype = ServiceAppLock.this.sp.getString("locktype", "0");
                ServiceAppLock.this.runningtasks = new ArrayList();
                ServiceAppLock.this.runningappnames = new ArrayList();
                ServiceAppLock.this.runningtasks = ServiceAppLock.this.am.getRunningTasks(Integer.MAX_VALUE);
                ServiceAppLock.this.getRunningAppNames();
                ServiceAppLock.this.isSelectedAppRunning();
                return;
              }
              catch (Exception localException)
              {
                ServiceAppLock.this.error = 1;
              }
            }
          }, 0L, 400L);
          return 1;
        }
        selectappstrack.add("0");
        paramInt1 += 1;
      }
      return 1;
    }
    catch (Exception paramIntent)
    {
      this.error = 1;
      if (this.error == 1)
      {
        this.error = 0;
        paramIntent = new Intent(this, ServiceAppLock.class);
        stopService(paramIntent);
        startService(paramIntent);
      }
    }
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\ServiceAppLock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */