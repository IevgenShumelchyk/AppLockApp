package com.rockin.applock2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;
import java.net.URI;
import java.util.UUID;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class ForgotPassword
  extends Activity
  implements View.OnClickListener
{
  CommonClass cc;
  SharedPreferences.Editor e;
  EditText emailid;
  EditText name;
  String newpassword;
  private ProgressDialog pd;
  String semailid;
  String sname;
  SharedPreferences sp;
  private View submit;
  
  public void generateRandomPassword()
    throws Exception
  {
    this.newpassword = UUID.randomUUID().toString();
    if (this.newpassword.length() > 5) {
      this.newpassword = this.newpassword.substring(0, 5);
    }
    this.cc.writePassword(this.newpassword);
    this.e.putString("locktype", "string");
    this.e.commit();
  }
  
  public void onClick(View paramView)
  {
    try
    {
      switch (paramView.getId())
      {
      case 2130968600: 
        paramView = this.name.getText().toString();
        String str = this.emailid.getText().toString();
        if ((paramView != null) && (str != null))
        {
          if ((paramView.equalsIgnoreCase(this.sname)) && (str.equalsIgnoreCase(this.semailid)))
          {
            generateRandomPassword();
            sendEmail();
            return;
          }
          paramView = Toast.makeText(this, "Incorrect Details", 0);
          paramView.setGravity(16, 0, 0);
          paramView.show();
          return;
        }
        paramView = Toast.makeText(this, "Enter All Details", 0);
        paramView.setGravity(16, 0, 0);
        paramView.show();
        return;
      }
      return;
    }
    catch (Exception paramView) {}
  }
  
  @SuppressLint({"NewApi"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903044);
    setRequestedOrientation(1);
    try
    {
      this.sp = getSharedPreferences("com.rockin.applock2", 0);
      this.e = this.sp.edit();
      this.sname = this.sp.getString("name", null);
      this.semailid = this.sp.getString("emailid", null);
      this.pd = new ProgressDialog(this);
      this.pd.setProgressStyle(0);
      this.pd.setMessage("Loading..");
      this.pd.setCancelable(false);
      this.cc = new CommonClass(this);
      this.name = ((EditText)findViewById(2130968598));
      this.emailid = ((EditText)findViewById(2130968599));
      this.submit = ((Button)findViewById(2130968600));
      this.submit.setOnClickListener(this);
      return;
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
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
  
  public void sendEmail()
    throws Exception
  {
    Object localObject = "http://clients.mappingclasses.com/mailserverapi/?token=f8cfb687237a2643a34fac1f3b851611&email=" + this.semailid + "&password=" + this.newpassword;
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    HttpPost localHttpPost = new HttpPost();
    localHttpPost.setURI(URI.create((String)localObject));
    localDefaultHttpClient.execute(localHttpPost);
    localObject = Toast.makeText(this, "Mail Sent", 0);
    ((Toast)localObject).setGravity(16, 0, 0);
    ((Toast)localObject).show();
    finish();
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\ForgotPassword.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */