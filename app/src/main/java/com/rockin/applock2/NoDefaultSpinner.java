package com.rockin.applock2;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Spinner;
import java.lang.reflect.Method;

public class NoDefaultSpinner
  extends Spinner
{
  private static Method s_pSelectionChangedMethod = null;
  private int lastSelected = 0;
  
  static
  {
    try
    {
      s_pSelectionChangedMethod = AdapterView.class.getDeclaredMethod("selectionChanged", new Class[0]);
      if (s_pSelectionChangedMethod != null) {
        s_pSelectionChangedMethod.setAccessible(true);
      }
      return;
    }
    catch (Exception localException)
    {
      Log.e("Custom spinner, reflection bug:", localException.getMessage());
      throw new RuntimeException(localException);
    }
  }
  
  public NoDefaultSpinner(Context paramContext)
  {
    super(paramContext);
  }
  
  public NoDefaultSpinner(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public NoDefaultSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    super.onClick(paramDialogInterface, paramInt);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.lastSelected == getSelectedItemPosition()) {
      testReflectionForSelectionChanged();
    }
    if (!paramBoolean) {
      this.lastSelected = getSelectedItemPosition();
    }
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void testReflectionForSelectionChanged()
  {
    try
    {
      s_pSelectionChangedMethod.invoke(this, new Class[0]);
      return;
    }
    catch (Exception localException)
    {
      Log.e("Custom spinner, reflection bug: ", localException.getMessage());
      localException.printStackTrace();
    }
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\NoDefaultSpinner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */