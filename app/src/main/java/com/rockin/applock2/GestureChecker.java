package com.rockin.applock2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;

public class GestureChecker
  extends Activity
  implements GestureOverlayView.OnGesturePerformedListener, View.OnClickListener
{
  SharedPreferences.Editor e;
  GestureOverlayView gesture;
  GestureLibrary library;
  ProgressDialog pd;
  SharedPreferences sp;
  
  public void onBackPressed()
  {
    super.onBackPressed();
    try
    {
      Bundle localBundle = getIntent().getExtras();
      String str2 = null;
      String str1 = null;
      if (localBundle != null)
      {
        str2 = localBundle.getString("index");
        str1 = localBundle.getString("fromservice");
      }
      if ((str2 != null) && (str1.equals("true")))
      {
        int i = Integer.parseInt(str2);
        ServiceAppLock.selectappstrack.set(i, "0");
      }
      if (this.sp.getString("change", null) != null)
      {
        this.e.putString("change", null);
        this.e.commit();
      }
      finish();
      return;
    }
    catch (Exception localException) {}
  }
  
  public void onClick(View paramView)
  {
    try
    {
      paramView.getId();
      return;
    }
    catch (Exception e) {}
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2130903048);
    setRequestedOrientation(1);
    try
    {
      this.gesture = ((GestureOverlayView)findViewById(2130968603));
      this.sp = getSharedPreferences("com.rockin.applock2", 0);
      this.e = this.sp.edit();
      this.gesture.addOnGesturePerformedListener(this);
      this.library = GestureLibraries.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gestures"));
      this.library.load();
      this.pd = new ProgressDialog(this);
      this.pd.setProgressStyle(0);
      this.pd.setMessage("Loading..");
      this.pd.setCancelable(false);
      ((TextView)findViewById(2130968582)).setText("Gesture");
      ((Button)findViewById(2130968601)).setVisibility(4);
      return;
    }
    catch (Exception e) {}
  }
  
  public void onGesturePerformed(GestureOverlayView paramGestureOverlayView, Gesture paramGesture)
  {
    Intent paramIntent;
    Toast paramToast;
    try
    {
      if (((Prediction)this.library.recognize(paramGesture).get(0)).score > 2.0D)
      {
        if (getIntent().getExtras() != null)
        {
          finish();
          return;
        }
        this.pd.show();
        paramIntent = new Intent("installedappsapplock");
        paramIntent.setFlags(67108864);
        startActivity(paramIntent);
        finish();
        return;
      }
      paramToast = Toast.makeText(this, "Failed", 0);
      paramToast.setGravity(16, 0, 0);
      paramToast.show();
      return;
    }
    catch (Exception e) {}
  }
  
  protected void onPause()
  {
    super.onPause();
    finish();
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\GestureChecker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */