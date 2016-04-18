package com.rockin.applock2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StringLock
  extends Activity
  implements OnClickListener
{
  boolean b;
  CommonClass cc;
  SharedPreferences.Editor e;
  EditText password;
  ProgressDialog pd;
  SharedPreferences sp;
  private Button submit;
  
  public void check()
    throws Exception
  {
    String str = this.password.getText().toString();
    this.b = this.cc.checkPassword(str);
  }
  
  public boolean checkOnlyAlphabets(String paramString)
  {
    return paramString.matches("[a-zA-Z]+");
  }
  
  /* Error */
  public void onClick(android.view.View paramView)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 71	android/view/View:getId	()I
    //   4: tableswitch	default:+281->285, 2130968620:+20->24
    //   24: aload_0
    //   25: invokevirtual 75	com/rockin/applock2/StringLock:getIntent	()Landroid/content/Intent;
    //   28: invokevirtual 81	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   31: astore_1
    //   32: aload_0
    //   33: getfield 44	com/rockin/applock2/StringLock:cc	Lcom/rockin/applock2/CommonClass;
    //   36: invokevirtual 84	com/rockin/applock2/CommonClass:deleteFiles	()V
    //   39: aload_0
    //   40: getfield 44	com/rockin/applock2/StringLock:cc	Lcom/rockin/applock2/CommonClass;
    //   43: invokevirtual 87	com/rockin/applock2/CommonClass:existPassword	()I
    //   46: ifne +151 -> 197
    //   49: aload_1
    //   50: ifnonnull +147 -> 197
    //   53: aload_0
    //   54: getfield 30	com/rockin/applock2/StringLock:password	Landroid/widget/EditText;
    //   57: invokevirtual 36	android/widget/EditText:getText	()Landroid/text/Editable;
    //   60: invokeinterface 42 1 0
    //   65: astore_1
    //   66: aload_0
    //   67: aload_1
    //   68: invokevirtual 89	com/rockin/applock2/StringLock:checkOnlyAlphabets	(Ljava/lang/String;)Z
    //   71: ifeq +96 -> 167
    //   74: aload_0
    //   75: getfield 91	com/rockin/applock2/StringLock:pd	Landroid/app/ProgressDialog;
    //   78: invokevirtual 96	android/app/ProgressDialog:show	()V
    //   81: aload_0
    //   82: getfield 98	com/rockin/applock2/StringLock:e	Landroid/content/SharedPreferences$Editor;
    //   85: ldc 100
    //   87: ldc 102
    //   89: invokeinterface 108 3 0
    //   94: pop
    //   95: aload_0
    //   96: getfield 98	com/rockin/applock2/StringLock:e	Landroid/content/SharedPreferences$Editor;
    //   99: ldc 110
    //   101: ldc 112
    //   103: invokeinterface 108 3 0
    //   108: pop
    //   109: aload_0
    //   110: getfield 98	com/rockin/applock2/StringLock:e	Landroid/content/SharedPreferences$Editor;
    //   113: invokeinterface 116 1 0
    //   118: pop
    //   119: aload_0
    //   120: getfield 44	com/rockin/applock2/StringLock:cc	Lcom/rockin/applock2/CommonClass;
    //   123: invokevirtual 119	com/rockin/applock2/CommonClass:printToast	()V
    //   126: aload_0
    //   127: getfield 44	com/rockin/applock2/StringLock:cc	Lcom/rockin/applock2/CommonClass;
    //   130: aload_1
    //   131: invokevirtual 123	com/rockin/applock2/CommonClass:writePassword	(Ljava/lang/String;)V
    //   134: new 77	android/content/Intent
    //   137: dup
    //   138: ldc 125
    //   140: invokespecial 127	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   143: astore_1
    //   144: aload_1
    //   145: ldc -128
    //   147: invokevirtual 132	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   150: pop
    //   151: aload_0
    //   152: aload_1
    //   153: invokevirtual 136	com/rockin/applock2/StringLock:startActivity	(Landroid/content/Intent;)V
    //   156: aload_0
    //   157: invokevirtual 139	com/rockin/applock2/StringLock:finish	()V
    //   160: return
    //   161: astore_1
    //   162: aload_1
    //   163: invokevirtual 142	java/lang/Exception:printStackTrace	()V
    //   166: return
    //   167: aload_0
    //   168: ldc -112
    //   170: iconst_0
    //   171: invokestatic 150	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   174: astore_1
    //   175: aload_1
    //   176: bipush 16
    //   178: iconst_0
    //   179: iconst_0
    //   180: invokevirtual 154	android/widget/Toast:setGravity	(III)V
    //   183: aload_1
    //   184: invokevirtual 155	android/widget/Toast:show	()V
    //   187: aload_0
    //   188: getfield 30	com/rockin/applock2/StringLock:password	Landroid/widget/EditText;
    //   191: ldc -99
    //   193: invokevirtual 161	android/widget/EditText:setText	(Ljava/lang/CharSequence;)V
    //   196: return
    //   197: aload_0
    //   198: invokevirtual 163	com/rockin/applock2/StringLock:check	()V
    //   201: aload_0
    //   202: getfield 52	com/rockin/applock2/StringLock:b	Z
    //   205: ifeq +46 -> 251
    //   208: aload_1
    //   209: ifnonnull +37 -> 246
    //   212: aload_0
    //   213: getfield 91	com/rockin/applock2/StringLock:pd	Landroid/app/ProgressDialog;
    //   216: invokevirtual 96	android/app/ProgressDialog:show	()V
    //   219: new 77	android/content/Intent
    //   222: dup
    //   223: ldc 125
    //   225: invokespecial 127	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   228: astore_1
    //   229: aload_1
    //   230: ldc -128
    //   232: invokevirtual 132	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   235: pop
    //   236: aload_0
    //   237: aload_1
    //   238: invokevirtual 136	com/rockin/applock2/StringLock:startActivity	(Landroid/content/Intent;)V
    //   241: aload_0
    //   242: invokevirtual 139	com/rockin/applock2/StringLock:finish	()V
    //   245: return
    //   246: aload_0
    //   247: invokevirtual 139	com/rockin/applock2/StringLock:finish	()V
    //   250: return
    //   251: aload_0
    //   252: ldc -91
    //   254: iconst_0
    //   255: invokestatic 150	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   258: astore_1
    //   259: aload_1
    //   260: bipush 16
    //   262: iconst_0
    //   263: iconst_0
    //   264: invokevirtual 154	android/widget/Toast:setGravity	(III)V
    //   267: aload_1
    //   268: invokevirtual 155	android/widget/Toast:show	()V
    //   271: aload_0
    //   272: getfield 30	com/rockin/applock2/StringLock:password	Landroid/widget/EditText;
    //   275: ldc -99
    //   277: invokevirtual 161	android/widget/EditText:setText	(Ljava/lang/CharSequence;)V
    //   280: return
    //   281: astore_1
    //   282: goto -148 -> 134
    //   285: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	286	0	this	StringLock
    //   0	286	1	paramView	android.view.View
    // Exception table:
    //   from	to	target	type
    //   0	24	161	java/lang/Exception
    //   24	49	161	java/lang/Exception
    //   53	119	161	java/lang/Exception
    //   119	134	161	java/lang/Exception
    //   134	160	161	java/lang/Exception
    //   167	196	161	java/lang/Exception
    //   197	208	161	java/lang/Exception
    //   212	245	161	java/lang/Exception
    //   246	250	161	java/lang/Exception
    //   251	280	161	java/lang/Exception
    //   119	134	281	java/io/IOException
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
      this.sp = getSharedPreferences("com.rockin.applock2", 0);
      this.e = this.sp.edit();
      this.pd = new ProgressDialog(this);
      this.pd.setProgressStyle(0);
      this.pd.setMessage("Loading..");
      this.pd.setCancelable(false);
      this.password = ((EditText)findViewById(2130968609));
      this.submit = ((Button)findViewById(2130968620));
      this.cc = new CommonClass(this);
      this.submit.setOnClickListener(this);
      ((TextView)findViewById(2130968582)).setText("String");
      ((Button)findViewById(2130968601)).setVisibility(4);
      return;
    }
    catch (Exception e) {}
  }
  
  protected void onPause()
  {
    super.onPause();
    finish();
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\StringLock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */