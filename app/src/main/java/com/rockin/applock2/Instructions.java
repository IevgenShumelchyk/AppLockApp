package com.rockin.applock2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.analytics.tracking.android.EasyTracker;

public class Instructions
  extends Activity
  implements View.OnClickListener
{
  ImageButton backallpage;
  
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
    switch (paramView.getId())
    {
    default: 
      return;
    }
    paramView = new Intent("chooserapplock");
    paramView.setFlags(67108864);
    startActivity(paramView);
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2130903052);
    setRequestedOrientation(1);
    ((TextView)findViewById(2130968582)).setText("Instructions");
    ((TextView)findViewById(2130968595)).setText(Html.fromHtml("<b>Numeric:</b> You can set your password in numeric and lock the list of important Apps which you want to protect from being accessed by others.<br><br><b>Pattern:</b> If you find it difficult to remember a number or a word just set a pattern as a password to lock all your important Apps from being accessed by others.<br><br><b>Gesture:</b> This is the most secure password. You can draw a quick picture or turn your hand on the screen to lock and unlock. It is very convenient and safe.<br><br><b>String:</b> With the help of this option user can set a password using letters. This is the easiest way to set the password and lock your Apps. User has to input the correct password set to open the Apps later."));
  }
  
  protected void onPause()
  {
    super.onPause();
    finish();
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
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\Instructions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */