package com.rockin.applock2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetPassword
  extends Activity
  implements View.OnClickListener
{
  CommonClass cc;
  SharedPreferences.Editor e;
  EditText emailid;
  EditText name;
  ProgressDialog pd;
  String sconfirm = null;
  String semailid = null;
  String sname = null;
  SharedPreferences sp;
  String spass = null;
  Button submit;
  
  public boolean checkAllStrings()
    throws Exception
  {
    if ((this.sname.trim().length() == 0) && ((this.semailid.trim().length() == 0) || (!this.semailid.contains("@"))))
    {
      localToast = Toast.makeText(this, "Enter correct details", 0);
      localToast.setGravity(16, 0, 0);
      localToast.show();
      return false;
    }
    if (this.sname.trim().length() == 0)
    {
      localToast = Toast.makeText(this, "Enter your name", 0);
      localToast.setGravity(16, 0, 0);
      localToast.show();
      return false;
    }
    if ((this.sname.trim().length() != 0) && (this.semailid.trim().length() != 0) && (this.spass.trim().length() != 0) && (this.sconfirm.trim().length() != 0))
    {
      if (this.semailid.contains("@")) {
        return true;
      }
      localToast = Toast.makeText(this, "Enter correct email", 0);
      localToast.setGravity(16, 0, 0);
      localToast.show();
      return false;
    }
    Toast localToast = Toast.makeText(this, "Enter correct email", 0);
    localToast.setGravity(16, 0, 0);
    localToast.show();
    return false;
  }
  
  public void getStringValues()
    throws Exception
  {
    this.sname = this.name.getText().toString();
    this.semailid = this.emailid.getText().toString();
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    Intent localIntent = new Intent("lockselectorapplock");
    localIntent.setFlags(67108864);
    startActivity(localIntent);
    finish();
  }
  
  /* Error */
  public void onClick(android.view.View paramView)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 124	android/view/View:getId	()I
    //   4: tableswitch	default:+142->146, 2130968606:+20->24
    //   24: aload_0
    //   25: invokevirtual 126	com/rockin/applock2/SetPassword:getStringValues	()V
    //   28: aload_0
    //   29: invokevirtual 128	com/rockin/applock2/SetPassword:checkAllStrings	()Z
    //   32: ifeq +114 -> 146
    //   35: aload_0
    //   36: getfield 130	com/rockin/applock2/SetPassword:cc	Lcom/rockin/applock2/CommonClass;
    //   39: invokevirtual 135	com/rockin/applock2/CommonClass:existPassword	()I
    //   42: ifne +104 -> 146
    //   45: aload_0
    //   46: getfield 137	com/rockin/applock2/SetPassword:pd	Landroid/app/ProgressDialog;
    //   49: invokevirtual 140	android/app/ProgressDialog:show	()V
    //   52: aload_0
    //   53: getfield 142	com/rockin/applock2/SetPassword:e	Landroid/content/SharedPreferences$Editor;
    //   56: ldc -112
    //   58: ldc -110
    //   60: invokeinterface 152 3 0
    //   65: pop
    //   66: aload_0
    //   67: getfield 142	com/rockin/applock2/SetPassword:e	Landroid/content/SharedPreferences$Editor;
    //   70: ldc -103
    //   72: aload_0
    //   73: getfield 30	com/rockin/applock2/SetPassword:sname	Ljava/lang/String;
    //   76: invokeinterface 152 3 0
    //   81: pop
    //   82: aload_0
    //   83: getfield 142	com/rockin/applock2/SetPassword:e	Landroid/content/SharedPreferences$Editor;
    //   86: ldc -102
    //   88: aload_0
    //   89: getfield 32	com/rockin/applock2/SetPassword:semailid	Ljava/lang/String;
    //   92: invokeinterface 152 3 0
    //   97: pop
    //   98: aload_0
    //   99: getfield 142	com/rockin/applock2/SetPassword:e	Landroid/content/SharedPreferences$Editor;
    //   102: invokeinterface 157 1 0
    //   107: pop
    //   108: aload_0
    //   109: getfield 130	com/rockin/applock2/SetPassword:cc	Lcom/rockin/applock2/CommonClass;
    //   112: invokevirtual 160	com/rockin/applock2/CommonClass:printToast	()V
    //   115: aload_0
    //   116: ldc -94
    //   118: iconst_0
    //   119: invokestatic 65	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   122: astore_1
    //   123: aload_1
    //   124: bipush 16
    //   126: iconst_0
    //   127: iconst_0
    //   128: invokevirtual 69	android/widget/Toast:setGravity	(III)V
    //   131: aload_1
    //   132: invokevirtual 72	android/widget/Toast:show	()V
    //   135: aload_0
    //   136: invokevirtual 115	com/rockin/applock2/SetPassword:finish	()V
    //   139: return
    //   140: astore_1
    //   141: return
    //   142: astore_1
    //   143: goto -28 -> 115
    //   146: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	147	0	this	SetPassword
    //   0	147	1	paramView	android.view.View
    // Exception table:
    //   from	to	target	type
    //   0	24	140	java/lang/Exception
    //   24	108	140	java/lang/Exception
    //   108	115	140	java/lang/Exception
    //   115	139	140	java/lang/Exception
    //   108	115	142	java/io/IOException
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2130903044);
    setRequestedOrientation(1);
    try
    {
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
      this.cc = new CommonClass(this);
      this.sp = getSharedPreferences("com.rockin.applock2", 0);
      this.e = this.sp.edit();
      this.sname = this.sp.getString("name", null);
      this.semailid = this.sp.getString("emailid", null);
      this.name = ((EditText)findViewById(2130968598));
      this.emailid = ((EditText)findViewById(2130968599));
      this.submit = ((Button)findViewById(2130968606));
      this.submit.setOnClickListener(this);
      return;
    }
    catch (Exception paramBundle) {}
  }
  
  protected void onPause()
  {
    super.onPause();
    finish();
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\SetPassword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */