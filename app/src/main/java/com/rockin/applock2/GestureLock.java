package com.rockin.applock2;

import android.annotation.TargetApi;
import android.app.Activity;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;
import java.io.File;

@TargetApi(4)
public class GestureLock
  extends Activity
  implements View.OnClickListener
{
  private static final float LENGTH_THRESHOLD = 120.0F;
  ImageButton backallpage;
  private Button cancel;
  CommonClass cc;
  SharedPreferences.Editor e;
  private Button mDoneButton;
  private Gesture mGesture;
  ProgressDialog pd;
  SharedPreferences sp;
  
  public void addGesture(View paramView)
  {
    if ((this.mGesture != null) && (this.mGesture.getLength() > 130.0D))
    {
      this.pd.show();
      this.cc.deleteFiles();
      this.e.putString("locktype", "gesture");
      this.e.putString("launched", "true");
      this.e.commit();
      setResult(-1);
      new File(Environment.getExternalStorageDirectory(), "gestures").delete();
      paramView = GestureLibraries.fromFile(new File(Environment.getExternalStorageDirectory(), "gestures"));
      paramView.addGesture("My gesture", this.mGesture);
      paramView.save();
      paramView = Toast.makeText(this, "Gesture Recorded", 0);
      paramView.setGravity(16, 0, 0);
      paramView.show();
      paramView = new Intent("installedappsapplock");
      paramView.setFlags(67108864);
      startActivity(paramView);
    }
    for (;;)
    {
      finish();
      return;
      setResult(0);
    }
  }
  
  public void cancelGesture(View paramView)
  {
    setResult(0);
    finish();
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    startActivity(new Intent(this, Chooser.class));
    finish();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    }
    startActivity(new Intent(this, Chooser.class));
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2130903047);
    setRequestedOrientation(1);
    this.mDoneButton = ((Button)findViewById(2130968606));
    this.cancel = ((Button)findViewById(2130968605));
    this.sp = getSharedPreferences("com.rockin.applock2", 0);
    this.e = this.sp.edit();
    this.cc = new CommonClass(this);
    ((GestureOverlayView)findViewById(2130968603)).addOnGestureListener(new GesturesProcessor(null));
    this.mDoneButton.setOnClickListener(this);
    this.cancel.setOnClickListener(this);
    this.pd = new ProgressDialog(this);
    this.pd.setProgressStyle(0);
    this.pd.setMessage("Loading..");
    this.pd.setCancelable(false);
    ((TextView)findViewById(2130968582)).setText("Gesture");
    ((Button)findViewById(2130968601)).setVisibility(4);
  }
  
  protected void onRestoreInstanceState(final Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    this.mGesture = ((Gesture)paramBundle.getParcelable("gesture"));
    if ((this.mGesture != null) && (this.mGesture.getLength() > 130.0D))
    {
      paramBundle = (GestureOverlayView)findViewById(2130968603);
      paramBundle.post(new Runnable()
      {
        public void run()
        {
          paramBundle.setGesture(GestureLock.this.mGesture);
        }
      });
      this.mDoneButton.setEnabled(true);
      return;
    }
    this.mDoneButton.setEnabled(false);
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
    EasyTracker.getInstance().activityStart(this);
  }
  
  public void onStop()
  {
    super.onStop();
    EasyTracker.getInstance().activityStop(this);
  }
  
  private class GesturesProcessor
    implements GestureOverlayView.OnGestureListener
  {
    private GesturesProcessor() {}
    
    public void onGesture(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent) {}
    
    public void onGestureCancelled(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent) {}
    
    public void onGestureEnded(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent)
    {
      GestureLock.this.mGesture = paramGestureOverlayView.getGesture();
      if (GestureLock.this.mGesture.getLength() < 120.0F) {
        paramGestureOverlayView.clear(false);
      }
      GestureLock.this.mDoneButton.setEnabled(true);
    }
    
    public void onGestureStarted(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent)
    {
      GestureLock.this.mDoneButton.setEnabled(false);
      GestureLock.this.mGesture = null;
    }
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\GestureLock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */