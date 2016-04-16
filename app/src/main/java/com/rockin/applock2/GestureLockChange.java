package com.rockin.applock2;

import android.annotation.SuppressLint;
import android.app.Activity;
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

public class GestureLockChange
  extends Activity
  implements GestureOverlayView.OnGesturePerformedListener, View.OnClickListener
{
  CommonClass cc;
  SharedPreferences.Editor e;
  GestureOverlayView gesture;
  GestureLibrary library;
  SharedPreferences sp;
  
  @SuppressLint({"NewApi"})
  public void onBackPressed()
  {
    super.onBackPressed();
    Intent localIntent = new Intent("chooserapplock");
    localIntent.setFlags(67108864);
    startActivity(localIntent);
    finish();
  }
  
  public void onClick(View paramView) {}
  
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
      this.cc = new CommonClass(this);
      this.sp = getSharedPreferences("com.rockin.applock2", 0);
      this.e = this.sp.edit();
      this.gesture.addOnGesturePerformedListener(this);
      this.library = GestureLibraries.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gestures"));
      this.library.load();
      ((TextView)findViewById(2130968582)).setText("Enter current gesture");
      ((Button)findViewById(2130968601)).setVisibility(4);
      return;
    }
    catch (Exception paramBundle) {}
  }
  
  public void onGesturePerformed(GestureOverlayView paramGestureOverlayView, Gesture paramGesture)
  {
    try
    {
      if (((Prediction)this.library.recognize(paramGesture).get(0)).score > 2.0D)
      {
        paramGestureOverlayView = Toast.makeText(this, "Success. Please change your lock now.", 0);
        paramGestureOverlayView.setGravity(16, 0, 0);
        paramGestureOverlayView.show();
        this.e.putString("change", "true");
        this.e.commit();
        this.e.putString("change", null);
        this.e.commit();
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
      paramGestureOverlayView = Toast.makeText(this, "Failed", 0);
      paramGestureOverlayView.setGravity(16, 0, 0);
      paramGestureOverlayView.show();
      return;
    }
    catch (Exception paramGestureOverlayView) {}
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\GestureLockChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */