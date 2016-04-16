package com.rockin.applock2;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class InstalledAppsViewHolder
{
  CheckBox checkbox;
  ImageView imageView;
  TextView textView;
  
  public InstalledAppsViewHolder() {}
  
  public InstalledAppsViewHolder(TextView paramTextView, CheckBox paramCheckBox, ImageView paramImageView)
  {
    this.checkbox = paramCheckBox;
    this.textView = paramTextView;
    this.imageView = paramImageView;
  }
  
  public CheckBox getCheckBox()
  {
    return this.checkbox;
  }
  
  public ImageView getImageView()
  {
    return this.imageView;
  }
  
  public TextView getTextView()
  {
    return this.textView;
  }
  
  public void setCheckBox(CheckBox paramCheckBox)
  {
    this.checkbox = paramCheckBox;
  }
  
  public void setImageView(ImageView paramImageView)
  {
    this.imageView = paramImageView;
  }
  
  public void setTextView(TextView paramTextView)
  {
    this.textView = paramTextView;
  }
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\InstalledAppsViewHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */