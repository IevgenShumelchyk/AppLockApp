package com.rockin.applock2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperSelectedApps
  extends SQLiteOpenHelper
{
  static int counter = 0;
  static String dbname;
  static String tbname = "Tasks";
  static int ver;
  Context c;
  String col1 = "PackageName";
  String[] cols = { this.col1 };
  SQLiteDatabase sld;
  
  static
  {
    dbname = "Database2";
    ver = 1;
  }
  
  public HelperSelectedApps(Context paramContext)
  {
    super(paramContext, dbname, null, ver);
    this.c = paramContext;
    this.sld = getWritableDatabase();
  }
  
  public void clearAll()
  {
    this.sld.execSQL("delete from " + tbname + ";");
  }
  
  public void clearData(String paramString)
  {
    this.sld.execSQL("delete from " + tbname + " where PackageName='" + paramString + "';");
  }
  
  public void closeDatabase()
  {
    this.sld.close();
  }
  
  public Cursor display()
  {
    return this.sld.query(tbname, this.cols, null, null, null, null, null);
  }
  
  public void insert(String paramString)
  {
    this.sld.execSQL("insert into " + tbname + " values('" + paramString + "');");
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    if (counter == 0) {
      paramSQLiteDatabase.execSQL("create table " + tbname + "(" + this.col1 + " TEXT NOT NULL);");
    }
    counter += 1;
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
}


/* Location:              D:\ANDROID\Decompile\AppLock-dex2jar.jar!\com\rockin\applock2\HelperSelectedApps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */