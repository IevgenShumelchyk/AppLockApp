package com.rockin.applock2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class Utils
{
  protected static int selected = 1;
  
  public static int getScreenWidth(Context paramContext)
  {
    paramContext = ((Activity)paramContext).getWindowManager().getDefaultDisplay();
    Point localPoint = new Point();
    try
    {
      paramContext.getSize(localPoint);
      return localPoint.x;
    }
    catch (Exception localException)
    {
      return paramContext.getWidth();
    }
    catch (NoSuchMethodError localNoSuchMethodError) {}
    return paramContext.getWidth();
  }
  
  public static boolean isEmailValid(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString.indexOf("@") > 0)
    {
      bool1 = bool2;
      if (paramString.lastIndexOf('.') > paramString.indexOf("@") + 1) {
        bool1 = true;
      }
    }
    int i = paramString.indexOf("@");
    int j = paramString.indexOf(".");
    if ((i == -1) || (j == -1)) {
      bool1 = false;
    }
    if ((paramString.indexOf("#") != -1) || (paramString.indexOf("(") != -1) || (paramString.indexOf(")") != -1) || (paramString.indexOf("+") != -1) || (paramString.indexOf("*") != -1) || (paramString.indexOf("/") != -1) || (paramString.indexOf(":") != -1) || (paramString.indexOf(";") != -1) || (paramString.indexOf(",") != -1) || (paramString.indexOf("\"") != -1) || (paramString.indexOf("?") != -1) || (paramString.indexOf("!") != -1) || (paramString.indexOf("'") != -1) || (paramString.indexOf("$") != -1) || (paramString.indexOf(" ") != -1) || (paramString.indexOf("\n") != -1) || (paramString.indexOf("=") != -1) || (paramString.indexOf("%") != -1) || (paramString.indexOf("|") != -1) || (paramString.indexOf("{") != -1) || (paramString.indexOf("}") != -1) || (paramString.indexOf("[") != -1) || (paramString.indexOf("]") != -1) || (paramString.indexOf("&") != -1) || (paramString.indexOf("~") != -1) || (paramString.indexOf("^") != -1) || (paramString.indexOf("<") != -1) || (paramString.indexOf(">") != -1)) {
      bool1 = false;
    }
    paramString = paramString.concat("*");
    bool2 = bool1;
    if (paramString.indexOf("*") <= paramString.indexOf(".") + 2) {
      bool2 = false;
    }
    boolean bool3 = bool2;
    if (bool2)
    {
      bool1 = bool2;
      if (j != -1)
      {
        if (paramString.charAt(j) != paramString.charAt(paramString.length() - 1)) {
          break label445;
        }
        bool1 = false;
      }
    }
    for (;;)
    {
      bool3 = bool1;
      if (i != -1)
      {
        bool3 = bool1;
        if (paramString.substring(i + 1).indexOf("@") != -1) {
          bool3 = false;
        }
      }
      return bool3;
      label445:
      if (paramString.charAt(j) == paramString.charAt(j + 1))
      {
        bool1 = false;
      }
      else
      {
        bool1 = bool2;
        if (j == 0) {
          bool1 = false;
        }
      }
    }
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */