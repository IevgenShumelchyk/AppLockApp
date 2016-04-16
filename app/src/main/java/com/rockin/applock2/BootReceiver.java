package com.rockin.applock2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;


public class BootReceiver extends BroadcastReceiver{

  SharedPreferences sp;
  ArrayList<ApplicationInfo> selectedappslist;
  List<ApplicationInfo> listappinfo;
  HelperSelectedApps hsa;

  PackageManager pm;

  @Override
  public void onReceive(Context context, Intent intent) {
    try
    {
      sp=context.getSharedPreferences("com.rockin.applock2",Context.MODE_PRIVATE);

      String formfilled=sp.getString("formfilled", null);
      selectedappslist=new ArrayList<ApplicationInfo>();
      hsa=new HelperSelectedApps(context);

      pm = context.getPackageManager();


      if(formfilled!=null)
      {
        getPreSelectedAppsList();

        new ServiceAppLock();
        context.startService(new Intent(context, ServiceAppLock.class));
      }
    }catch(Exception e){}

  }

  public void getPreSelectedAppsList() throws Exception
  {
    listappinfo = pm.getInstalledApplications(PackageManager.GET_META_DATA);
    Cursor c = hsa.display();

    int i = c.getColumnIndex("PackageName");

    for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
      String pname = c.getString(i);

      for (ApplicationInfo ai : listappinfo) {
        if (pname.equals(ai.packageName)) {
          selectedappslist.add(ai);
        }
      }
    }
  }

}

/* from decompile
public class BootReceiver
  extends BroadcastReceiver
{
  HelperSelectedApps hsa;
  List<ApplicationInfo> listappinfo;
  PackageManager pm;
  ArrayList<ApplicationInfo> selectedappslist;
  SharedPreferences sp;
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    try
    {
      this.sp = paramContext.getSharedPreferences("com.rockin.applock2", 0);
      paramIntent = this.sp.getString("formfilled", null);
      this.selectedappslist = new ArrayList();
      this.hsa = new HelperSelectedApps(paramContext);
      this.pm = paramContext.getPackageManager();
      if (paramIntent != null)
      {
        new ServiceAppLock();
        paramContext.startService(new Intent(paramContext, ServiceAppLock.class));
      }
      return;
    }
    catch (Exception paramContext) {}
  }
}
*/

/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\BootReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */