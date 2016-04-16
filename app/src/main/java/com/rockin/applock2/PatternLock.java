package com.rockin.applock2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import com.google.analytics.tracking.android.EasyTracker;
import group.pals.android.lib.ui.lockpattern.LockPatternActivity;
import java.util.ArrayList;

public class PatternLock
  extends Activity
{
  private static final int REQ_ENTER_PATTERN = 2;
  final int REQ_CREATE_PATTERN = 1;
  CommonClass cc;
  SharedPreferences.Editor e;
  SharedPreferences sp;
  String temp;
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default: 
      return;
    case 1: 
      if (paramInt2 == -1)
      {
        paramIntent = new String(paramIntent.getCharArrayExtra(LockPatternActivity.EXTRA_PATTERN));
        this.cc.deleteFiles();
        this.e.putString("locktype", "pattern");
        this.e.putString("launched", "true");
        this.e.putString("password", paramIntent);
        this.e.commit();
        if (getIntent().getExtras() == null)
        {
          paramIntent = new Intent("installedappsapplock");
          paramIntent.setFlags(67108864);
          startActivity(paramIntent);
          finish();
          return;
        }
        finish();
        return;
      }
      this.e.putString("formfilled", null);
      this.e.commit();
      return;
    }
    switch (paramInt2)
    {
    case 0: 
    default: 
      return;
    }
    if (getIntent().getExtras() == null)
    {
      paramIntent = new Intent("installedappsapplock");
      paramIntent.setFlags(67108864);
      startActivity(paramIntent);
      finish();
      return;
    }
    finish();
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    Bundle localBundle = getIntent().getExtras();
    String str = null;
    Object localObject = null;
    if (localBundle != null)
    {
      str = localBundle.getString("index");
      localObject = localBundle.getString("fromservice");
    }
    if ((str != null) && (((String)localObject).equals("true")))
    {
      int i = Integer.parseInt(str);
      ServiceAppLock.selectappstrack.set(i, "0");
    }
    if (this.sp.getString("change", null) != null)
    {
      this.e.putString("change", null);
      this.e.commit();
    }
    localObject = new Intent(this, Chooser.class);
    ((Intent)localObject).setFlags(67108864);
    startActivity((Intent)localObject);
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setRequestedOrientation(1);
    this.sp = getSharedPreferences("com.rockin.applock2", 0);
    this.e = this.sp.edit();
    this.cc = new CommonClass(this);
    Object localObject = this.sp.getString("password", "0");
    paramBundle = (Bundle)localObject;
    if (this.sp.getString("change", null) != null)
    {
      this.temp = ((String)localObject);
      paramBundle = "0";
    }
    if (paramBundle.equals("0"))
    {
      startActivityForResult(new Intent(LockPatternActivity.ACTION_CREATE_PATTERN, null, this, LockPatternActivity.class), 1);
      return;
    }
    localObject = new Intent(LockPatternActivity.ACTION_COMPARE_PATTERN, null, this, LockPatternActivity.class);
    paramBundle = paramBundle.toCharArray();
    ((Intent)localObject).putExtra(LockPatternActivity.EXTRA_PATTERN, paramBundle);
    startActivityForResult((Intent)localObject, 2);
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
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\PatternLock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */