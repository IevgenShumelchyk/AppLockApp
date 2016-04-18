package com.rockin.applock2;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import java.io.File;

public class GestureLockFirstTime
  extends FragmentActivity
  implements View.OnClickListener
{
  private static final float LENGTH_THRESHOLD = 120.0F;
  private Button cancel;
  private SlidingMenu drawer;
  SharedPreferences.Editor e;
  private Gesture mGesture;
  GestureOverlayView overlay;
  ProgressDialog pd;
  SharedPreferences sp;
  Button submit;
  
  @SuppressLint({"NewApi"})
  public void onBackPressed()
  {
    super.onBackPressed();
    Intent localIntent = new Intent(this, Chooser.class);
    localIntent.setFlags(67108864);
    startActivity(localIntent);
    finish();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2130968602: 
    case 2130968603: 
    case 2130968604: 
    default: 
      return;
    case 2130968601: 
      this.drawer.toggle();
      return;
    case 2130968605: 
      startActivity(new Intent(this, GestureLockFirstTime.class));
      finish();
      return;
    }
    GestureLibrary paramGestureLibrary;
    Toast paramToast;
    Intent paramIntent;
    if ((this.mGesture != null) && (this.mGesture.getLength() > 130.0D))
    {
      this.pd.show();
      setResult(-1);
      this.e.putString("formfilled", "true");
      this.e.putString("locktype", "gesture");
      this.e.putString("launched", "true");
      this.e.commit();
      new File(Environment.getExternalStorageDirectory(), "gestures").delete();
      paramGestureLibrary = GestureLibraries.fromFile(new File(Environment.getExternalStorageDirectory(), "gestures"));
      paramGestureLibrary.addGesture("My gesture", this.mGesture);
      paramGestureLibrary.save();
      paramToast = Toast.makeText(this, "Gesture Recorded", 0);
      paramToast.setGravity(16, 0, 0);
      paramToast.show();
      paramIntent = new Intent("installedappsapplock");
      paramIntent.setFlags(67108864);
      startActivity(paramIntent);
      return;
    }
    paramToast = Toast.makeText(this, "Draw Gesture", 0);
    paramToast.setGravity(16, 0, 0);
    paramToast.show();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2130903047);
    setRequestedOrientation(1);
    this.sp = getSharedPreferences("com.rockin.applock2", 0);
    this.e = this.sp.edit();
    this.submit = ((Button)findViewById(2130968606));
    this.submit.setOnClickListener(this);
    this.cancel = ((Button)findViewById(2130968605));
    this.cancel.setOnClickListener(this);
    this.pd = new ProgressDialog(this);
    this.pd.setProgressStyle(0);
    this.pd.setMessage("Loading..");
    this.pd.setCancelable(false);
    this.overlay = ((GestureOverlayView)findViewById(2130968603));
    this.overlay.addOnGestureListener(new GesturesProcessor(null));
    ((TextView)findViewById(2130968582)).setText("Gesture");
    ((Button)findViewById(2130968601)).setOnClickListener(this);
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
  }
  
  protected void onPause()
  {
    super.onPause();
    finish();
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    this.mGesture = ((Gesture)paramBundle.getParcelable("gesture"));
    if ((this.mGesture != null) && (this.mGesture.getLength() > 130.0D))
    {
      this.overlay.post(new Runnable()
      {
        public void run()
        {
          GestureLockFirstTime.this.overlay.setGesture(GestureLockFirstTime.this.mGesture);
        }
      });
      this.submit.setEnabled(true);
      return;
    }
    this.submit.setEnabled(false);
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if ((this.mGesture != null) && (this.mGesture.getLength() > 130.0D)) {
      paramBundle.putParcelable("gesture", this.mGesture);
    }
  }
  
  public void onStart()
  {
    super.onStart();
    EasyTracker.getInstance(this).activityStart(this);
  }
  
  public void onStop()
  {
    super.onStop();
    EasyTracker.getInstance(this).activityStop(this);
  }
  
  private class GesturesProcessor
    implements GestureOverlayView.OnGestureListener
  {
    private GesturesProcessor() {}
    
    public void onGesture(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent) {}
    
    public void onGestureCancelled(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent) {}
    
    public void onGestureEnded(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent)
    {
      GestureLockFirstTime.this.mGesture = paramGestureOverlayView.getGesture();
      if (GestureLockFirstTime.this.mGesture.getLength() < 120.0F) {
        paramGestureOverlayView.clear(false);
      }
      GestureLockFirstTime.this.submit.setEnabled(true);
    }
    
    public void onGestureStarted(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent)
    {
      GestureLockFirstTime.this.submit.setEnabled(false);
      GestureLockFirstTime.this.mGesture = null;
    }
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\GestureLockFirstTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */