package com.rockin.applock2;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class InstalledAppsCustomAdapter
  extends BaseAdapter
{
  private List<App> data;
  LayoutInflater inflater;
  PackageManager pm;
  
  public InstalledAppsCustomAdapter(Context paramContext, List<App> paramList)
  {
    this.inflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.pm = paramContext.getPackageManager();
    this.data = paramList;
  }
  
  public int getCount()
  {
    return this.data.size();
  }
  
  public Object getItem(int paramInt)
  {
    return this.data.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    App localApp = (App)getItem(paramInt);
    TextView localTextView;
    CheckBox localCheckBox;
    if (paramView == null)
    {
      paramView = this.inflater.inflate(2130903051, null);
      localTextView = (TextView)paramView.findViewById(2130968617);
      localCheckBox = (CheckBox)paramView.findViewById(2130968618);
      paramViewGroup = (ImageView)paramView.findViewById(2130968616);
      paramView.setTag(new InstalledAppsViewHolder(localTextView, localCheckBox, paramViewGroup));
      localCheckBox.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = (CheckBox)paramAnonymousView;
          int i = Integer.parseInt(paramAnonymousView.getTag().toString());
          if (paramAnonymousView.isChecked())
          {
            InstalledApps.selectedappslist.add((App)InstalledAppsCustomAdapter.this.data.get(i));
            return;
          }
          InstalledApps.selectedappslist.remove(InstalledAppsCustomAdapter.this.data.get(i));
        }
      });
      localCheckBox.setTag(Integer.valueOf(paramInt));
      if (!InstalledApps.selectedappslist.contains(localApp)) {
        break label161;
      }
      localCheckBox.setChecked(true);
    }
    for (;;)
    {
      localTextView.setText(localApp.getName());
      paramViewGroup.setBackgroundDrawable(localApp.getIcon());
      return paramView;
      paramViewGroup = (InstalledAppsViewHolder)paramView.getTag();
      localTextView = paramViewGroup.getTextView();
      localCheckBox = paramViewGroup.getCheckBox();
      paramViewGroup = paramViewGroup.getImageView();
      break;
      label161:
      localCheckBox.setChecked(false);
    }
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\InstalledAppsCustomAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */