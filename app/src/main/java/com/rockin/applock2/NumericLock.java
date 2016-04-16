package com.rockin.applock2;

import android.app.Activity;
import android.app.ProgressDialog;
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
import java.io.IOException;
import java.util.ArrayList;

public class NumericLock
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
  ProgressDialog pd;
  SharedPreferences sp;
  Button submit;
  
  public void onBackPressed()
  {
    super.onBackPressed();
    try
    {
      Bundle localBundle = getIntent().getExtras();
      String str = null;
      Object localObject = null;
      if (localBundle != null)
      {
        str = localBundle.getString("index");
        localObject = localBundle.getString("fromservice");
      }
      if ((str != null) && (((String)localObject).equals("true")))
      {
        int i = Integer.parseInt(str);
        ServiceAppLock.selectappstrack.set(i, "0");
      }
      if (this.sp.getString("change", null) != null)
      {
        this.e.putString("change", null);
        this.e.commit();
      }
      if (localBundle == null)
      {
        localObject = new Intent(this, Chooser.class);
        ((Intent)localObject).setFlags(67108864);
        startActivity((Intent)localObject);
      }
      finish();
      return;
    }
    catch (Exception localException) {}
  }
  
  public void onClick(View paramView)
  {
    try
    {
      switch (paramView.getId())
      {
      case 2130968606: 
        this.cc.deleteFiles();
        int i = this.cc.existPassword();
        paramView = this.password.getText().toString();
        if (i == 0)
        {
          this.pd.show();
          this.e.putString("locktype", "numeric");
          this.e.putString("launched", "true");
          this.e.commit();
        }
        break;
      }
      try
      {
        this.cc.printToast();
        this.e.putString("numericpassword", paramView);
        this.e.commit();
        paramView = new Intent("installedappsapplock");
        paramView.setFlags(67108864);
        startActivity(paramView);
        finish();
        return;
        if (this.sp.getString("numericpassword", null).equals(paramView))
        {
          if (getIntent().getExtras() == null)
          {
            this.pd.show();
            paramView = new Intent("installedappsapplock");
            paramView.setFlags(67108864);
            startActivity(paramView);
            finish();
            return;
          }
          finish();
          return;
        }
        paramView = Toast.makeText(this, "Failed", 0);
        paramView.setGravity(16, 0, 0);
        paramView.show();
        return;
        paramView = new Intent("lockselectorapplock");
        paramView.setFlags(67108864);
        startActivity(paramView);
        finish();
        return;
        this.password.setText(this.password.getText().toString() + "0");
        return;
        this.password.setText(this.password.getText().toString() + "1");
        return;
        this.password.setText(this.password.getText().toString() + "2");
        return;
        this.password.setText(this.password.getText().toString() + "3");
        return;
        this.password.setText(this.password.getText().toString() + "4");
        return;
        this.password.setText(this.password.getText().toString() + "5");
        return;
        this.password.setText(this.password.getText().toString() + "6");
        return;
        this.password.setText(this.password.getText().toString() + "7");
        return;
        this.password.setText(this.password.getText().toString() + "8");
        return;
        this.password.setText(this.password.getText().toString() + "9");
        return;
        this.password.setText("");
        return;
      }
      catch (IOException localIOException)
      {
        for (;;) {}
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
      this.pd = new ProgressDialog(this);
      this.pd.setProgressStyle(0);
      this.pd.setMessage("Loading..");
      this.pd.setCancelable(false);
      this.cc = new CommonClass(this);
      this.password = ((EditText)findViewById(2130968619));
      this.submit = ((Button)findViewById(2130968606));
      this.backallpage = ((Button)findViewById(2130968630));
      if (getIntent().getExtras() != null) {
        this.backallpage.setEnabled(false);
      }
      this.backallpage.setOnClickListener(this);
      this.submit.setOnClickListener(this);
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
      ((TextView)findViewById(2130968582)).setText("Numeric");
      ((Button)findViewById(2130968601)).setVisibility(4);
      return;
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    finish();
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\NumericLock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */