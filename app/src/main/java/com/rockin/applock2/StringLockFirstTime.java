package com.rockin.applock2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import java.io.IOException;

public class StringLockFirstTime
  extends FragmentActivity
  implements View.OnClickListener
{
  CommonClass cc;
  private SlidingMenu drawer;
  SharedPreferences.Editor e;
  EditText password;
  ProgressDialog pd;
  SharedPreferences sp;
  String spass = null;
  private Button submit;
  
  public boolean checkOnlyAlphabets(String paramString)
  {
    return paramString.matches("[a-zA-Z]+");
  }
  
  public void getStringValues()
    throws Exception
  {
    this.spass = this.password.getText().toString();
  }
  
  public void onClick(View paramView)
  {
    try
    {
      switch (paramView.getId())
      {
      case 2130968601: 
        this.drawer.toggle();
        return;
      }
    }
    catch (Exception paramView)
    {
      paramView.printStackTrace();
      return;
    }
    getStringValues();
    if (this.spass.length() == 0)
    {
      paramView = Toast.makeText(this, "Please enter a string.", 0);
      paramView.setGravity(16, 0, 0);
      paramView.show();
      return;
    }
    if (checkOnlyAlphabets(this.spass))
    {
      this.pd.show();
      this.e.putString("formfilled", "true");
      this.e.putString("locktype", "string");
      this.e.putString("launched", "true");
      this.e.commit();
    }
    try
    {
      this.cc.printToast();
      this.cc.writePassword(this.spass);
      paramView = new Intent("installedappsapplock");
      paramView.setFlags(67108864);
      startActivity(paramView);
      return;
      paramView = Toast.makeText(this, "Enter only alphabets", 0);
      paramView.setGravity(16, 0, 0);
      paramView.show();
      this.password.setText("");
      return;
    }
    catch (IOException paramView)
    {
      for (;;) {}
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2130903056);
    setRequestedOrientation(1);
    try
    {
      this.pd = new ProgressDialog(this);
      this.pd.setProgressStyle(0);
      this.pd.setMessage("Loading..");
      this.pd.setCancelable(false);
      this.sp = getSharedPreferences("com.rockin.applock2", 0);
      this.e = this.sp.edit();
      this.cc = new CommonClass(this);
      this.password = ((EditText)findViewById(2130968609));
      this.submit = ((Button)findViewById(2130968620));
      this.submit.setOnClickListener(this);
      ((TextView)findViewById(2130968582)).setText("String");
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


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\StringLockFirstTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */