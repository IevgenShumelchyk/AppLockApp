package com.rockin.applock2;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import java.io.IOException;

public class PasswordMenu
  extends Fragment
  implements View.OnClickListener
{
  CommonClass cc;
  private SlidingMenu drawer;
  private SharedPreferences.Editor e;
  private EditText emailid;
  private EditText name;
  private ProgressDialog pd;
  private String semailid;
  private String sname;
  private SharedPreferences sp;
  
  public PasswordMenu() {}
  
  public PasswordMenu(SlidingMenu paramSlidingMenu)
  {
    this.drawer = paramSlidingMenu;
  }
  
  public static PasswordMenu newInstance()
  {
    PasswordMenu localPasswordMenu = new PasswordMenu();
    localPasswordMenu.setArguments(new Bundle());
    return localPasswordMenu;
  }
  
  public void getStringValues()
    throws Exception
  {
    this.sname = this.name.getText().toString();
    this.semailid = this.emailid.getText().toString();
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == 2130968601)
    {
      try
      {
        getStringValues();
        if (this.name.getText().toString().length() == 0)
        {
          Toast.makeText(getActivity(), "Please enter your name.", 0).show();
          return;
        }
        if (this.emailid.getText().toString().length() == 0)
        {
          Toast.makeText(getActivity(), "Please enter your email.", 0).show();
          return;
        }
      }
      catch (Exception paramView)
      {
        paramView.printStackTrace();
        return;
      }
      if (!Utils.isEmailValid(this.emailid.getText().toString()))
      {
        Toast.makeText(getActivity(), "Please enter a valid email.", 0).show();
        return;
      }
      this.pd.show();
      this.e.putString("formfilled", "true");
      this.e.putString("name", this.sname);
      this.e.putString("emailid", this.semailid);
      this.e.commit();
      try
      {
        this.cc.printToast();
        this.pd.dismiss();
        paramView = Toast.makeText(getActivity(), "Backup info saved.", 0);
        paramView.setGravity(16, 0, 0);
        paramView.show();
        this.drawer.toggle();
        return;
      }
      catch (IOException paramView)
      {
        for (;;) {}
      }
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130903045, paramViewGroup, false);
    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
    this.cc = new CommonClass(getActivity());
    this.pd = new ProgressDialog(getActivity());
    this.pd.setProgressStyle(0);
    this.pd.setMessage("Loading..");
    this.pd.setCancelable(false);
    this.sp = getActivity().getSharedPreferences("com.rockin.applock2", 0);
    this.e = this.sp.edit();
    this.sname = this.sp.getString("name", "");
    this.semailid = this.sp.getString("emailid", "");
    this.name = ((EditText)paramLayoutInflater.findViewById(2130968598));
    this.name.setText(this.sname);
    this.emailid = ((EditText)paramLayoutInflater.findViewById(2130968599));
    this.emailid.setText(this.semailid);
    ((Button)paramLayoutInflater.findViewById(2130968601)).setOnClickListener(this);
    return paramLayoutInflater;
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\PasswordMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */