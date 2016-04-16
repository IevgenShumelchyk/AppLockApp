package com.rockin.applock2;

import android.content.pm.ApplicationInfo;

public class InstalledAppsView
{
  private boolean checked = false;
  private ApplicationInfo info;
  
  public InstalledAppsView() {}
  
  public InstalledAppsView(ApplicationInfo paramApplicationInfo)
  {
    setInfo(paramApplicationInfo);
  }
  
  public ApplicationInfo getInfo()
  {
    return this.info;
  }
  
  public boolean isChecked()
  {
    return this.checked;
  }
  
  public void setChecked(boolean paramBoolean)
  {
    this.checked = paramBoolean;
  }
  
  public void setInfo(ApplicationInfo paramApplicationInfo)
  {
    this.info = paramApplicationInfo;
  }
  
  public void toggleChecked()
  {
    if (this.checked) {}
    for (boolean bool = false;; bool = true)
    {
      this.checked = bool;
      return;
    }
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\InstalledAppsView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */