package content.only.skeleton;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.view.Window;
import content.only.skeleton.util.Globals;
import content.only.skeleton.util.WebWeb;
import org.json.JSONArray;
import org.json.JSONObject;

public class Splash
  extends Activity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2130903055);
    if (Integer.valueOf(Build.VERSION.SDK).intValue() >= 9) {}
    try
    {
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
      Globals.setUserCountry(this);
      new AssetsDownloadTask(null).execute(new Void[0]);
      return;
    }
    catch (Exception paramBundle)
    {
      for (;;) {}
    }
  }
  
  public void proceed()
  {
    try
    {
      if ((new JSONObject(Globals.getMetaData(this)).getJSONObject(Globals.getCountry(this)).getJSONArray("lang").length() > 1) && (Globals.getLang(this).equals("na")))
      {
        Globals.setLang(this, "English");
        showDialogLang();
        return;
      }
      finish();
      startActivity(new Intent(this, Globals.LAUNCHER_CLASS));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      finish();
      startActivity(new Intent(this, Globals.LAUNCHER_CLASS));
    }
  }
  
  public void showDialogLang()
  {
    try
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setTitle("Select Language");
      JSONArray localJSONArray = new JSONObject(Globals.getMetaData(this)).getJSONObject(Globals.getCountry(this)).getJSONArray("lang");
      final CharSequence[] arrayOfCharSequence = new CharSequence[localJSONArray.length()];
      int i = 0;
      for (;;)
      {
        if (i >= localJSONArray.length())
        {
          localBuilder.setItems(arrayOfCharSequence, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              Globals.getLang(Splash.this);
              Globals.setLang(Splash.this, arrayOfCharSequence[paramAnonymousInt].toString());
              Splash.this.finish();
              paramAnonymousDialogInterface = new Intent(Splash.this, Globals.LAUNCHER_CLASS);
              Splash.this.startActivity(paramAnonymousDialogInterface);
            }
          });
          localBuilder.create();
          localBuilder.show();
          return;
        }
        arrayOfCharSequence[i] = localJSONArray.getString(i);
        i += 1;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private class AssetsDownloadTask
    extends AsyncTask<Void, Void, Void>
  {
    private String msg = "";
    private ProgressDialog progressDialog;
    
    private AssetsDownloadTask() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      Globals.startBackgroundService(Splash.this);
      if ((Globals.isNetworkAvailable(Splash.this)) && (WebWeb.isNewMetaDataAvailable(Splash.this))) {
        WebWeb.fetchAppMetaData(Splash.this);
      }
      for (;;)
      {
        return null;
        try
        {
          Thread.sleep(1200L);
        }
        catch (InterruptedException paramVarArgs)
        {
          paramVarArgs.printStackTrace();
        }
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      if ((this.progressDialog != null) && (this.progressDialog.isShowing()) && (!Splash.this.isFinishing())) {
        this.progressDialog.dismiss();
      }
      Globals.releaseWakeLock();
      Splash.this.proceed();
    }
    
    protected void onPreExecute()
    {
      Globals.keepTheLightsOn(Splash.this);
      if (Globals.getMetaData(Splash.this).length() == 0) {}
      for (this.msg = "Downloading App Resources...";; this.msg = "Loading...")
      {
        if ((!Splash.this.isFinishing()) && (Splash.this != null)) {
          this.progressDialog = ProgressDialog.show(Splash.this, "", this.msg);
        }
        return;
      }
    }
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\content\only\skeleton\Splash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */