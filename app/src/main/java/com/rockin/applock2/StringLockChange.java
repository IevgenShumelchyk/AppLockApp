package com.rockin.applock2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StringLockChange
  extends Activity
  implements View.OnClickListener
{
  boolean b;
  CommonClass cc;
  SharedPreferences.Editor e;
  EditText password;
  SharedPreferences sp;
  private Button submit;
  
  public void check()
    throws Exception
  {
    String str = this.password.getText().toString();
    this.b = this.cc.checkPassword(str);
  }
  
  public void onClick(View paramView)
  {
    for (;;)
    {
      try
      {
        switch (paramView.getId())
        {
        case 2130968620: 
          check();
          if (!this.b) {
            break label206;
          }
          paramView = Toast.makeText(this, "Success. Please change your lock now.", 0);
          paramView.setGravity(16, 0, 0);
          paramView.show();
          this.e.putString("change", "true");
          this.e.commit();
          this.e.putString("change", null);
          this.e.commit();
          if (Utils.selected == 1)
          {
            startActivity(new Intent(this, StringLockFirstTime.class));
            finish();
            return;
          }
          break;
        }
      }
      catch (Exception paramView)
      {
        paramView.printStackTrace();
        return;
      }
      if (Utils.selected == 3) {
        startActivity(new Intent(this, GestureLockFirstTime.class));
      } else if (Utils.selected == 4) {
        startActivity(new Intent(this, NumericLockFirstTime.class));
      } else if (Utils.selected == 2) {
        startActivity(new Intent(this, PatternLock.class));
      }
    }
    label206:
    paramView = Toast.makeText(this, "Incorrect Password", 0);
    paramView.setGravity(16, 0, 0);
    paramView.show();
    this.password.setText("");
    return;
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
      this.password = ((EditText)findViewById(2130968609));
      this.submit = ((Button)findViewById(2130968620));
      this.cc = new CommonClass(this);
      this.submit.setOnClickListener(this);
      ((TextView)findViewById(2130968582)).setText("Enter current password");
      ((Button)findViewById(2130968601)).setVisibility(4);
      return;
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
    }
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\StringLockChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */