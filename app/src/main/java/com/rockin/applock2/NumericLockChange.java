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

public class NumericLockChange
  extends Activity
  implements View.OnClickListener
{
  Button backallpage;
  private Button button0;
  private View button1;
  private View button2;
  private View button3;
  private View button4;
  private View button5;
  private View button6;
  private Button button7;
  private View button8;
  private Button button9;
  private Button buttonclr;
  CommonClass cc;
  SharedPreferences.Editor e;
  EditText password;
  SharedPreferences sp;
  Button submit;
  
  public void onBackPressed()
  {
    super.onBackPressed();
    Intent localIntent = new Intent("chooserapplock");
    localIntent.setFlags(67108864);
    startActivity(localIntent);
    finish();
  }
  
  public void onClick(View paramView)
  {
    try
    {
      switch (paramView.getId())
      {
      case 2130968606: 
        paramView = this.password.getText().toString();
        if (this.sp.getString("numericpassword", null).equals(paramView))
        {
          this.e.putString("change", "true");
          this.e.commit();
          paramView = Toast.makeText(this, "Success. Please change your lock now.", 0);
          paramView.setGravity(16, 0, 0);
          paramView.show();
          this.e.putString("change", null);
          this.e.commit();
          if (Utils.selected == 1) {
            startActivity(new Intent(this, StringLockFirstTime.class));
          }
          for (;;)
          {
            finish();
            return;
            if (Utils.selected == 3) {
              startActivity(new Intent(this, GestureLockFirstTime.class));
            } else if (Utils.selected == 4) {
              startActivity(new Intent(this, NumericLockFirstTime.class));
            } else if (Utils.selected == 2) {
              startActivity(new Intent(this, PatternLock.class));
            }
          }
        }
        paramView = Toast.makeText(this, "Incorrect Password", 0);
        paramView.setGravity(16, 0, 0);
        paramView.show();
        this.password.setText("");
        return;
      case 2130968630: 
        paramView = new Intent("chooserapplock");
        paramView.setFlags(67108864);
        startActivity(paramView);
        finish();
        return;
      case 2130968629: 
        this.password.setText(this.password.getText().toString() + "0");
        return;
      case 2130968600: 
        this.password.setText(this.password.getText().toString() + "1");
        return;
      case 2130968620: 
        this.password.setText(this.password.getText().toString() + "2");
        return;
      case 2130968621: 
        this.password.setText(this.password.getText().toString() + "3");
        return;
      case 2130968622: 
        this.password.setText(this.password.getText().toString() + "4");
        return;
      case 2130968623: 
        this.password.setText(this.password.getText().toString() + "5");
        return;
      case 2130968624: 
        this.password.setText(this.password.getText().toString() + "6");
        return;
      case 2130968625: 
        this.password.setText(this.password.getText().toString() + "7");
        return;
      case 2130968626: 
        this.password.setText(this.password.getText().toString() + "8");
        return;
      case 2130968627: 
        this.password.setText(this.password.getText().toString() + "9");
        return;
      case 2130968628: 
        this.password.setText("");
        return;
      }
      return;
    }
    catch (Exception paramView) {}
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2130903053);
    setRequestedOrientation(1);
    try
    {
      this.sp = getSharedPreferences("com.rockin.applock2", 0);
      this.e = this.sp.edit();
      this.password = ((EditText)findViewById(2130968619));
      this.submit = ((Button)findViewById(2130968606));
      this.backallpage = ((Button)findViewById(2130968630));
      this.cc = new CommonClass(this);
      this.submit.setOnClickListener(this);
      this.backallpage.setOnClickListener(this);
      this.submit.setOnClickListener(this);
      this.backallpage.setOnClickListener(this);
      this.button0 = ((Button)findViewById(2130968629));
      this.button0.setOnClickListener(this);
      this.button1 = ((Button)findViewById(2130968600));
      this.button1.setOnClickListener(this);
      this.button2 = ((Button)findViewById(2130968620));
      this.button2.setOnClickListener(this);
      this.button3 = ((Button)findViewById(2130968621));
      this.button3.setOnClickListener(this);
      this.button4 = ((Button)findViewById(2130968622));
      this.button4.setOnClickListener(this);
      this.button5 = ((Button)findViewById(2130968623));
      this.button5.setOnClickListener(this);
      this.button6 = ((Button)findViewById(2130968624));
      this.button6.setOnClickListener(this);
      this.button7 = ((Button)findViewById(2130968625));
      this.button7.setOnClickListener(this);
      this.button8 = ((Button)findViewById(2130968626));
      this.button8.setOnClickListener(this);
      this.button9 = ((Button)findViewById(2130968627));
      this.button9.setOnClickListener(this);
      this.buttonclr = ((Button)findViewById(2130968628));
      this.buttonclr.setOnClickListener(this);
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


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\NumericLockChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */