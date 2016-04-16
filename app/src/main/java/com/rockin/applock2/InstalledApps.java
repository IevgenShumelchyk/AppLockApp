package com.rockin.applock2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InstalledApps
  extends FragmentActivity
  implements View.OnClickListener
{
  static List<App> listappinfo;
  static ArrayList<App> selectedappslist;
  ArrayAdapter<String> aa;
  private Button clear;
  private SlidingMenu drawer;
  private EditText et;
  HelperSelectedApps hsa;
  LayoutInflater inflater;
  ListView installedappslist;
  private Button menu;
  Button ok;
  PackageManager pm;
  private Button search;
  
  public void getInstalledAppsList()
  {
    Iterator localIterator = this.pm.getInstalledApplications(128).iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      App localApp = new App();
      localApp.setName(localApplicationInfo.loadLabel(this.pm).toString());
      localApp.setIcon(localApplicationInfo.loadIcon(this.pm));
      localApp.setPackageName(localApplicationInfo.packageName);
      listappinfo.add(localApp);
    }
  }
  
  public void getPreSelectedAppsIndex()
  {
    Cursor localCursor = this.hsa.display();
    int i = localCursor.getColumnIndex("PackageName");
    localCursor.moveToFirst();
    if (localCursor.isAfterLast()) {
      return;
    }
    String str = localCursor.getString(i);
    Iterator localIterator = listappinfo.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        localCursor.moveToNext();
        break;
      }
      App localApp = (App)localIterator.next();
      if (str.equals(localApp.getPackageName())) {
        selectedappslist.add(localApp);
      }
    }
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    Intent localIntent = new Intent("chooserapplock");
    localIntent.setFlags(67108864);
    startActivity(localIntent);
    finish();
  }
  
  public void onClick(View paramView)
  {
    try
    {
      Object localObject;
      switch (paramView.getId())
      {
      case 2130968608: 
        if (this.search.getVisibility() == 0)
        {
          this.et.setVisibility(0);
          this.clear.setVisibility(0);
          this.search.setVisibility(8);
          return;
        }
        break;
      case 2130968610: 
        if (this.clear.getVisibility() == 0)
        {
          this.et.setVisibility(8);
          this.clear.setVisibility(8);
          this.search.setVisibility(0);
          paramView = new ArrayList();
          localObject = listappinfo.iterator();
          for (;;)
          {
            if (!((Iterator)localObject).hasNext())
            {
              paramView = new InstalledAppsCustomAdapter(this, paramView);
              this.installedappslist.setAdapter(paramView);
              return;
            }
            paramView.add((App)((Iterator)localObject).next());
          }
        }
        break;
      case 2130968601: 
        this.drawer.toggle();
        return;
      case 2130968615: 
        this.hsa.clearAll();
        paramView = selectedappslist.iterator();
        for (;;)
        {
          if (!paramView.hasNext())
          {
            new ServiceAppLock();
            paramView = new Intent(this, ServiceAppLock.class);
            stopService(paramView);
            startService(paramView);
            paramView = Toast.makeText(this, "Apps Locked", 0);
            paramView.setGravity(16, 0, 0);
            paramView.show();
            new Thread()
            {
              public void run()
              {
                super.run();
                try
                {
                  Thread.sleep(2000L);
                  InstalledApps.this.finish();
                  return;
                }
                catch (InterruptedException localInterruptedException)
                {
                  for (;;) {}
                }
              }
            }.start();
            return;
          }
          localObject = (App)paramView.next();
          this.hsa.insert(((App)localObject).getPackageName());
        }
      case 2130968630: 
        paramView = new Intent("chooserapplock");
        paramView.setFlags(67108864);
        startActivity(paramView);
        finish();
        return;
      }
      return;
    }
    catch (Exception paramView) {}
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2130903050);
    setRequestedOrientation(1);
    try
    {
      this.hsa = new HelperSelectedApps(this);
      this.inflater = ((LayoutInflater)getSystemService("layout_inflater"));
      this.installedappslist = ((ListView)findViewById(2130968613));
      this.ok = ((Button)findViewById(2130968615));
      this.installedappslist.setChoiceMode(2);
      this.pm = getPackageManager();
      listappinfo = new ArrayList();
      selectedappslist = new ArrayList();
      this.ok.setOnClickListener(this);
      this.et = ((EditText)findViewById(2130968609));
      this.et.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable) {}
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          paramAnonymousCharSequence = new ArrayList();
          paramAnonymousInt1 = 0;
          Iterator localIterator;
          if (paramAnonymousInt1 >= InstalledApps.listappinfo.size()) {
            if (InstalledApps.this.et.getText().toString().length() == 0)
            {
              paramAnonymousCharSequence.clear();
              localIterator = InstalledApps.listappinfo.iterator();
            }
          }
          for (;;)
          {
            if (!localIterator.hasNext())
            {
              paramAnonymousCharSequence = new InstalledAppsCustomAdapter(InstalledApps.this, paramAnonymousCharSequence);
              InstalledApps.this.installedappslist.setAdapter(paramAnonymousCharSequence);
              return;
              if (((App)InstalledApps.listappinfo.get(paramAnonymousInt1)).getName().toLowerCase().startsWith(InstalledApps.this.et.getText().toString().toLowerCase())) {
                paramAnonymousCharSequence.add((App)InstalledApps.listappinfo.get(paramAnonymousInt1));
              }
              paramAnonymousInt1 += 1;
              break;
            }
            paramAnonymousCharSequence.add((App)localIterator.next());
          }
        }
      });
      this.menu = ((Button)findViewById(2130968601));
      this.menu.setOnClickListener(this);
      this.clear = ((Button)findViewById(2130968610));
      this.clear.setOnClickListener(this);
      this.search = ((Button)findViewById(2130968608));
      this.search.setOnClickListener(this);
      this.drawer = new SlidingMenu(this);
      this.drawer.setTouchModeAbove(2);
      this.drawer.setFadeDegree(0.35F);
      this.drawer.attachToActivity(this, 1);
      this.drawer.setMenu(2130903046);
      int i = Utils.getScreenWidth(this) / 4;
      int j = Utils.getScreenWidth(this) / 2;
      this.drawer.setBehindWidth(i + j);
      this.drawer.setMode(2);
      getSupportFragmentManager().beginTransaction().replace(2130968602, new PasswordMenu(this.drawer)).commit();
      new AppLoadingTask(null).execute(new Void[0]);
      return;
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    finish();
  }
  
  protected void onResume()
  {
    super.onResume();
  }
  
  public void onStart()
  {
    super.onStart();
    EasyTracker.getInstance().activityStart(this);
  }
  
  public void onStop()
  {
    super.onStop();
    EasyTracker.getInstance().activityStop(this);
  }
  
  private class AppLoadingTask
    extends AsyncTask<Void, Void, Void>
  {
    private ProgressDialog progressDialog;
    
    private AppLoadingTask() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      InstalledApps.this.getInstalledAppsList();
      InstalledApps.this.getPreSelectedAppsIndex();
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      paramVoid = new InstalledAppsCustomAdapter(InstalledApps.this, InstalledApps.listappinfo);
      InstalledApps.this.installedappslist.setAdapter(paramVoid);
      if ((this.progressDialog != null) && (this.progressDialog.isShowing()) && (!InstalledApps.this.isFinishing())) {
        this.progressDialog.dismiss();
      }
    }
    
    protected void onPreExecute()
    {
      InstalledApps.listappinfo.clear();
      this.progressDialog = ProgressDialog.show(InstalledApps.this, "", "Loading...");
    }
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\InstalledApps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */