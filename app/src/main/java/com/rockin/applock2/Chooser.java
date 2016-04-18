package com.rockin.applock2;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
//import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import content.only.skeleton.util.Globals;
import group.pals.android.lib.ui.lockpattern.LockPatternActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.acra.util.ToastSender;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class Chooser
  extends FragmentActivity
  implements View.OnClickListener
{
  private static final int REQ_ENTER_PATTERN = 2;
  final int REQ_CREATE_PATTERN = 1;
  CommonClass cc;
  RelativeLayout changelist;
  private int count = 0;
  private SlidingMenu drawer;
  private NoDefaultSpinner dropdown;
  SharedPreferences.Editor e;
  RelativeLayout forgotpassword;
  protected String formfilled;
  RelativeLayout instructions;
  String locktype;
  private Button menu;
  protected int selected;
  SharedPreferences sp;
  
  public void changeGestureLock()
    throws Exception
  {
    this.e.putString("change", null);
    this.e.commit();
    startActivity(new Intent(this, GestureLockFirstTime.class));
  }
  
  public void changeNumericLock()
  {
    this.e.putString("change", null);
    this.e.commit();
    startActivity(new Intent(this, NumericLockFirstTime.class));
  }
  
  public void changePatternLock()
    throws Exception
  {
    this.e.putString("change", null);
    this.e.commit();
    startActivity(new Intent(this, PatternLock.class));
  }
  
  public void changePatternLock1()
    throws Exception
  {
    Object localObject = this.sp.getString("password", "0");
    Intent localIntent = new Intent(LockPatternActivity.ACTION_COMPARE_PATTERN, null, this, LockPatternActivity.class);
    localObject = ((String)localObject).toCharArray();
    localIntent.putExtra(LockPatternActivity.EXTRA_PATTERN, (char[])localObject);
    startActivityForResult(localIntent, 2);
  }
  
  public void changeStringLock()
    throws Exception
  {
    this.e.putString("change", null);
    this.e.commit();
    startActivity(new Intent(this, StringLockFirstTime.class));
  }
  
  public String checkVersion()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new URL(Globals.CHECK_VERSION_URL).openStream()));
      for (;;)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
        {
          localBufferedReader.close();
          return localStringBuilder.toString();
        }
        localStringBuilder.append(str);
      }
      return "";
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default: 
      return;
    }
    switch (paramInt2)
    {
    default: 
      return;
    case -1: 
      this.e.putString("change", "true");
      this.e.commit();

      Toast paramToast;
      paramToast = Toast.makeText(this, "Success. Please change your lock now.", Toast.LENGTH_SHORT);
      paramToast.setGravity(16, 0, 0);
      paramToast.show();
      //paramIntent = Toast.makeText(this, "Success. Please change your lock now.", 0);
      //paramIntent.setGravity(16, 0, 0);
      //paramIntent.show();

      if (Utils.selected == 1) {
        startActivity(new Intent(this, StringLockFirstTime.class));
      }
      for (;;)
      {
        finish();
        return;
        if (Utils.selected == 3) {
          startActivity(new Intent(this, GestureLockFirstTime.class));
        } else if (Utils.selected == 4) {
          startActivity(new Intent(this, NumericLockFirstTime.class));
        } else if (Utils.selected == 2) {
          startActivity(new Intent(this, PatternLock.class));
        }
      }
    }
    finish();
  }
  
  public void onClick(View paramView)
  {
    Toast paramToast;
    Intent paramIntent;
    String paramString = "";
    try
    {
      switch (paramView.getId())
      {
      case 2130968601: 
        this.drawer.toggle();
        return;
      case 2130968596: 
        //paramView = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
        NetworkInfo paramNetInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
        if (this.dropdown.getSelectedItemPosition() == 0)
        {
          Toast.makeText(this, "Please set a lock first.", 1).show();
          return;
        }
        if ((paramNetInfo != null) && (paramNetInfo.isConnected()))
        {
          //paramView = new Intent("forgotpasswordapplock");
          //paramView.setFlags(67108864);
          //startActivity(paramView);
          paramIntent = new Intent("forgotpasswordapplock");
          paramIntent.setFlags(67108864);
          startActivity(paramIntent);
          return;
        }
        //paramView = Toast.makeText(this, "No Internet Connection", 0);
        //paramView.setGravity(16, 0, 0);
        //paramView.show();
        paramToast = Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT);
        paramToast.setGravity(16, 0, 0);
        paramToast.show();
        return;
      case 2130968594: 
        this.formfilled = this.sp.getString("formfilled", null);
        if (this.formfilled == null)
        {
          Toast.makeText(this, "Please set backup email first.", Toast.LENGTH_LONG).show();
          this.drawer.toggle();
          return;
        }
        if (this.dropdown.getSelectedItemPosition() == 0)
        {
          Toast.makeText(this, "Please set a lock first.", Toast.LENGTH_LONG).show();
          return;
        }
        this.e.putString("change", null);
        this.e.commit();
        //paramView = null;
        if (this.locktype.equals("string")) {
          //paramView = "stringlockapplock";
          paramString = "stringlockapplock";
        }
        for (;;)
        {
          //paramView = new Intent(paramView);
          //paramView.setFlags(67108864);
          //startActivity(paramView);
          paramIntent = new Intent("stringlockapplock");
          paramIntent.setFlags(67108864);
          startActivity(paramIntent);

          finish();
          return;
          if (this.locktype.equals("pattern")) {
            paramString = "patternlockapplock";
          } else if (this.locktype.equals("gesture")) {
            paramString = "gesturecheckerapplock";
          } else if (this.locktype.equals("numeric")) {
            paramString = "numericlockapplock";
          }
        }
      case 2130968597: 
        //paramView = new Intent("instructionsapplock");
        //paramView.setFlags(67108864);
        //startActivity(paramView);
        paramIntent = new Intent("instructionsapplock");
        paramIntent.setFlags(67108864);
        startActivity(paramIntent);

        finish();
        return;
      }
      return;
    }
   // catch (Exception paramView) {}
    catch (Exception e) {}
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2130903043);
    setRequestedOrientation(1);
    this.count = 0;
    Log.e("here", "here");
    for (;;)
    {
      try
      {
        this.sp = getSharedPreferences("com.rockin.applock2", 0);
        this.e = this.sp.edit();
        this.locktype = this.sp.getString("locktype", "0");
        this.cc = new CommonClass(this);
        this.dropdown = ((NoDefaultSpinner)findViewById(2130968593));
        this.dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
          public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            paramAnonymousAdapterView = Chooser.this;
            paramAnonymousAdapterView.count += 1;
            Log.e("pos", paramAnonymousInt + ";;" + Chooser.this.count);
            Chooser.this.selected = paramAnonymousInt;
            if (Chooser.this.count >= 2)
            {
              if (paramAnonymousInt != 3) {
                break label227;
              }
              Utils.selected = 1;
              Log.e("locktype", Chooser.this.sp.getString("locktype", "") + ";;" + Chooser.this.sp.getString("change", ""));
              Chooser.this.formfilled = Chooser.this.sp.getString("formfilled", null);
              if (paramAnonymousInt != 0) {
                break label263;
              }
              paramAnonymousAdapterView = new Intent(Chooser.this, ServiceAppLock.class);
              Chooser.this.stopService(paramAnonymousAdapterView);
              Chooser.this.e.putString("locktype", "");
              Chooser.this.e.commit();
              Chooser.this.cc.deleteFiles();
            }
            for (;;)
            {
              return;
              label227:
              if (paramAnonymousInt == 1)
              {
                Utils.selected = 3;
                break;
              }
              if (paramAnonymousInt == 4)
              {
                Utils.selected = 4;
                break;
              }
              if (paramAnonymousInt != 2) {
                break;
              }
              Utils.selected = 2;
              break;
              label263:
              if (Chooser.this.formfilled == null)
              {
                Toast.makeText(Chooser.this, "Please set backup email first.", 1).show();
                Chooser.this.dropdown.setSelection(0);
                Chooser.this.drawer.toggle();
                return;
              }
              if ((Chooser.this.sp.getString("locktype", "") == null) || (Chooser.this.sp.getString("locktype", "").length() == 0) || (Chooser.this.sp.getString("change", "").equals("true"))) {
                break label590;
              }
              Chooser.this.e.putString("change", "");
              Chooser.this.e.commit();
              paramAnonymousAdapterView = null;
              if (Chooser.this.locktype.equals("string"))
              {
                Chooser.this.finish();
                paramAnonymousAdapterView = new Intent(Chooser.this, StringLockChange.class);
              }
              while (!Chooser.this.locktype.equals("pattern"))
              {
                Chooser.this.finish();
                paramAnonymousAdapterView.setFlags(67108864);
                Chooser.this.startActivity(paramAnonymousAdapterView);
                return;
                if (Chooser.this.locktype.equals("pattern"))
                {
                  try
                  {
                    Chooser.this.changePatternLock1();
                  }
                  catch (Exception paramAnonymousView)
                  {
                    paramAnonymousView.printStackTrace();
                  }
                }
                else if (Chooser.this.locktype.equals("gesture"))
                {
                  Chooser.this.finish();
                  paramAnonymousAdapterView = new Intent(Chooser.this, GestureLockChange.class);
                }
                else if (Chooser.this.locktype.equals("numeric"))
                {
                  Chooser.this.finish();
                  paramAnonymousAdapterView = new Intent(Chooser.this, NumericLockChange.class);
                }
              }
            }
            label590:
            if (paramAnonymousInt == 1) {}
            for (;;)
            {
              try
              {
                Chooser.this.changeGestureLock();
                Chooser.this.finish();
                return;
              }
              catch (Exception paramAnonymousAdapterView)
              {
                paramAnonymousAdapterView.printStackTrace();
                return;
              }
              if (paramAnonymousInt == 2) {
                Chooser.this.changePatternLock();
              } else if (paramAnonymousInt == 3) {
                Chooser.this.changeStringLock();
              } else if (paramAnonymousInt == 4) {
                Chooser.this.changeNumericLock();
              }
            }
          }
          
          public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
        });
        paramBundle = new ArrayAdapter(this, 17367049, new String[] { "No Lock", "Gesture", "Pattern", "Password", "Numeric" });
        this.dropdown.setAdapter(paramBundle);
        if (this.sp.getString("locktype", "").equals("string"))
        {
          this.dropdown.setSelection(3);
          this.forgotpassword = ((RelativeLayout)findViewById(2130968596));
          this.changelist = ((RelativeLayout)findViewById(2130968594));
          this.instructions = ((RelativeLayout)findViewById(2130968597));
          this.forgotpassword.setOnClickListener(this);
          this.changelist.setOnClickListener(this);
          this.instructions.setOnClickListener(this);
          this.menu = ((Button)findViewById(2130968601));
          this.menu.setOnClickListener(this);
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
          if (!Globals.isNetworkAvailable(this)) {
            break;
          }
          new CheckVersionTask().execute(new Void[0]);
          return;
        }
        if (this.sp.getString("locktype", "").equals("numeric"))
        {
          this.dropdown.setSelection(4);
          continue;
        }
        if (!this.sp.getString("locktype", "").equals("gesture")) {
          break label516;
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
        return;
      }
      this.dropdown.setSelection(1);
      continue;
      label516:
      if (this.sp.getString("locktype", "").equals("pattern")) {
        this.dropdown.setSelection(2);
      } else {
        this.dropdown.setSelection(0);
      }
    }

  }
  
  protected android.app.Dialog onCreateDialog(final int paramInt)
  {
    /*
    switch (paramInt)
    {
    default: 
      return null;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    Object localObject2 = "Update Application";
    Object localObject3 = "An updated version of this App is available. Please update your application.";
    localObject7 = "Update";
    str2 = "Cancel";
    localObject4 = localObject3;
    localObject5 = localObject7;
    localObject6 = localObject2;
    try
    {
      content.only.skeleton.util.Dialog localDialog = Globals.getDialogData(this, "dialog3");
      Object localObject1 = localObject2;
      localObject4 = localObject3;
      localObject5 = localObject7;
      localObject6 = localObject2;
      if (localDialog.getTitle().length() != 0)
      {
        localObject4 = localObject3;
        localObject5 = localObject7;
        localObject6 = localObject2;
        localObject1 = localDialog.getTitle();
      }
      localObject2 = localObject3;
      localObject4 = localObject3;
      localObject5 = localObject7;
      localObject6 = localObject1;
      if (localDialog.getMessage().length() != 0)
      {
        localObject4 = localObject3;
        localObject5 = localObject7;
        localObject6 = localObject1;
        localObject2 = localDialog.getMessage();
      }
      localObject3 = localObject7;
      localObject4 = localObject2;
      localObject5 = localObject7;
      localObject6 = localObject1;
      if (localDialog.getPositiveAnswer().length() != 0)
      {
        localObject4 = localObject2;
        localObject5 = localObject7;
        localObject6 = localObject1;
        localObject3 = localDialog.getPositiveAnswer();
      }
      localObject9 = localObject2;
      str1 = str2;
      localObject7 = localObject3;
      localObject8 = localObject1;
      localObject4 = localObject2;
      localObject5 = localObject3;
      localObject6 = localObject1;
      if (localDialog.getNegativeAnswer().length() != 0)
      {
        localObject4 = localObject2;
        localObject5 = localObject3;
        localObject6 = localObject1;
        str1 = localDialog.getNegativeAnswer();
        localObject8 = localObject1;
        localObject7 = localObject3;
        localObject9 = localObject2;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        Object localObject9 = localObject4;
        String str1 = str2;
        localObject7 = localObject5;
        Object localObject8 = localObject6;
      }
    }
    localBuilder.setTitle((CharSequence)localObject8);
    localBuilder.setMessage((CharSequence)localObject9);
    localBuilder.setPositiveButton((CharSequence)localObject7, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        new Chooser.UpdateTask(Chooser.this, null).execute(new Void[0]);
      }
    }).setNegativeButton(str1, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Chooser.this.removeDialog(paramInt);
      }
    });
    return localBuilder.create();
    */
  }
  
  public void onResume()
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
  
  int update()
  {
    try
    {
      Object localObject1 = new BasicHttpParams();
      HttpConnectionParams.setConnectionTimeout((HttpParams)localObject1, 12000);
      HttpConnectionParams.setSoTimeout((HttpParams)localObject1, 12000);
      ((HttpParams)localObject1).setParameter("http.useragent", "Android");
      Object localObject2 = new DefaultHttpClient((HttpParams)localObject1).execute(new HttpGet(Globals.APK_URL.replaceAll("xcvxcv", Globals.getCountry(this))));
      localObject1 = new File(Environment.getExternalStorageDirectory() + "/download/");
      ((File)localObject1).mkdirs();
      localObject1 = new FileOutputStream(new File((File)localObject1, "AppLock.apk"));
      localObject2 = ((HttpResponse)localObject2).getEntity().getContent();
      byte[] arrayOfByte = new byte['ࠈ'];
      for (;;)
      {
        int i = ((InputStream)localObject2).read(arrayOfByte);
        if (i == -1)
        {
          ((FileOutputStream)localObject1).close();
          ((InputStream)localObject2).close();
          return 0;
        }
        ((FileOutputStream)localObject1).write(arrayOfByte, 0, i);
      }
      return -1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private class CheckVersionTask
    extends AsyncTask<Void, Void, Void>
  {
    private String text = "";
    
    private CheckVersionTask() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      this.text = Chooser.this.checkVersion();
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      if ((this.text != null) && (this.text.toString() != null) && (this.text.toString().length() != 0) && (Globals.getVersion(Chooser.this.getApplicationContext()) < Integer.parseInt(this.text.toString()))) {
        Chooser.this.showDialog(0);
      }
    }
    
    protected void onPreExecute() {}
  }
  
  private class UpdateTask
    extends AsyncTask<Void, Void, Void>
  {
    public ProgressDialog progressDialog;
    private int what = -1;
    
    private UpdateTask() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      this.what = Chooser.this.update();
      return null;
    }
    
    protected void onPostExecute() // Void paramVoid
    {
      Intent paramVoid;
      if (!Chooser.this.isFinishing()) {
        this.progressDialog.dismiss();
      }
      if (this.what == 0)
      {
        Chooser.this.finish();
        paramVoid = new Intent("android.intent.action.VIEW");
        paramVoid.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/" + "AppLock.apk")), "application/vnd.android.package-archive");
        Chooser.this.startActivity(paramVoid);
      }
    }
    
    protected void onPreExecute()
    {
      Object localObject = "Loading...";
      try
      {
        String str = Globals.getDialogData(Chooser.this, "dialog1").getMessage();
        localObject = str;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
      this.progressDialog = ProgressDialog.show(Chooser.this, "", (CharSequence)localObject);
    }
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\Chooser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */