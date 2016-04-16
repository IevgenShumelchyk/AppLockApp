package com.rockin.applock2;

import android.graphics.drawable.Drawable;

public class App
{
  private Drawable icon;
  private String name = "";
  private String packageName = "";
  
  public Drawable getIcon()
  {
    return this.icon;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPackageName()
  {
    return this.packageName;
  }
  
  public void setIcon(Drawable paramDrawable)
  {
    this.icon = paramDrawable;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setPackageName(String paramString)
  {
    this.packageName = paramString;
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\App.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */