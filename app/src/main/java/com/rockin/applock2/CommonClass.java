package com.rockin.applock2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import java.io.File;
import java.io.IOException;

public class CommonClass
{
  Activity act;
  SharedPreferences.Editor e;
  SharedPreferences sp;
  
  public CommonClass(Activity paramActivity)
  {
    this.act = paramActivity;
  }
  
  public boolean checkPassword(String paramString)
  {
    boolean bool2 = false;
    this.sp = this.act.getSharedPreferences("com.rockin.applock2", 0);
    String str = this.sp.getString("stringpassword", null);
    boolean bool1 = bool2;
    if (str != null)
    {
      bool1 = bool2;
      if (str.equals(paramString)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public void deleteFiles()
  {
    this.sp = this.act.getSharedPreferences("com.rockin.applock2", 0);
    this.e = this.sp.edit();
    if (this.sp.getString("change", null) != null)
    {
      new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gestures").delete();
      this.e.putString("password", "0");
      this.e.putString("stringpassword", null);
      this.e.putString("counter", null);
      this.e.putString("numericpassword", null);
      this.e.putString("change", null);
      this.e.putString("launched", null);
      this.e.commit();
    }
  }
  
  public int existPassword()
  {
    this.sp = this.act.getSharedPreferences("com.rockin.applock2", 0);
    if (this.sp.getString("counter", null) == null) {
      return 0;
    }
    return 1;
  }
  
  public void printToast()
    throws IOException
  {
    this.sp = this.act.getSharedPreferences("com.rockin.applock2", 0);
    this.e = this.sp.edit();
    this.e.putString("counter", "1");
    this.e.commit();
  }
  
  public void writePassword(String paramString)
  {
    this.sp = this.act.getSharedPreferences("com.rockin.applock2", 0);
    this.e = this.sp.edit();
    this.e.putString("stringpassword", paramString);
    this.e.commit();
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\CommonClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */