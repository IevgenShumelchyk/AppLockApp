package content.only.skeleton.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Environment;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings.Secure;
import android.support.v4.app.NotificationCompat.Builder;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.rockin.applock2.Chooser;
import content.only.skeleton.AppDataUpdateService;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Globals
{
  private static final String AD_TEXT = "Check out this useful App";
  private static final String AD_URL = "https://apps.sam-platform.com/download/52a872778957dfd3033c9889?country=*&tag=[LIVE]_Conversion_Test";
  public static final int APK_BUFFER_SIZE = 2056;
  public static String APK_URL = "https://apps.sam-platform.com/download/53fb2c818957dfa8038b4576?country=xcvxcv";
  public static final String APP_NAME = "App Lock";
  private static final int APP_VERSION = 6;
  public static final String ASSETS_URL = "https://apps.sam-platform.com/53fb2c818957dfa8038b4576/53fba7338957dfa7038b4582/resource";
  public static final String BASE_URL = "https://apps.sam-platform.com/53fb2c818957dfa8038b4576/53fba7338957dfa7038b4582/";
  public static final int BUFFER_SIZE = 1024;
  public static String CHECK_VERSION_URL = "https://apps.sam-platform.com/download/53fb2c818957dfa8038b4576/53fba7338957dfa7038b4582/version.txt";
  private static final String GA_PROPERTY_ID = "UA-40641021-1";
  public static Class<?> LAUNCHER_CLASS = Chooser.class;
  public static String METADATA_URL = "https://apps.sam-platform.com/53fb2c818957dfa8038b4576/53fba7338957dfa7038b4582/metadata";
  private static final String SERVICE_PAVKAGE_NAME = "content.only.skeleton";
  private static SharedPreferences ad_no;
  private static SharedPreferences ads;
  private static SharedPreferences airpush;
  public static float[] bat1 = { 10.14F, 9.13F, 9.1F, 8.01F, 9.12F, 9.15F };
  public static float[] bat2 = { 1.14F, 0.13F, 1.1F, 1.01F, 2.02F, 1.15F, 0.8F };
  public static float[] bat3 = { 0.14F, 0.03F, 1.13F, 0.01F, 0.02F, 1.9F };
  public static float[] bat4 = { 3.14F, 3.03F, 3.13F, 3.01F, 3.02F, 3.9F };
  private static SharedPreferences cel;
  private static SharedPreferences country;
  private static SharedPreferences date;
  private static SharedPreferences date1;
  private static SharedPreferences day_count;
  private static SharedPreferences ga;
  private static SharedPreferences lang;
  private static SharedPreferences screenData;
  private static SharedPreferences version;
  private static PowerManager.WakeLock wl;
  
  private static Ad getAd(Context paramContext)
  {
    localAd = new Ad();
    try
    {
      JSONObject localJSONObject = new JSONObject(getMetaData(paramContext));
      try
      {
        localJSONArray = localJSONObject.getJSONObject(getCountry(paramContext)).getJSONObject(getLang(paramContext)).getJSONArray("ads");
        setCurrentAdNumber(paramContext, getCurrentAdNumber(paramContext) + 1);
        int i = getCurrentAdNumber(paramContext);
        if (i >= localJSONArray.length()) {
          setCurrentAdNumber(paramContext, 0);
        }
        localAd.setText(localJSONArray.getJSONObject(i).getString("text"));
        localAd.setUrl(localJSONArray.getJSONObject(i).getString("link"));
        return localAd;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          JSONArray localJSONArray = localJSONObject.getJSONObject("en").getJSONObject("English").getJSONArray("ads");
          localException.printStackTrace();
        }
      }
      return localAd;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      localAd.setText("Check out this useful App");
      localAd.setUrl("https://apps.sam-platform.com/download/52a872778957dfd3033c9889?country=*&tag=[LIVE]_Conversion_Test");
    }
  }
  
  public static String getAds(Context paramContext)
  {
    ads = paramContext.getSharedPreferences("ads", 0);
    return ads.getString("ads", "");
  }
  
  public static int getAirPushOption(Context paramContext)
  {
    airpush = paramContext.getSharedPreferences("airpush", 0);
    return airpush.getInt("airpush", 0);
  }
  
  public static String getAndroidID(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
  
  public static String getAssetUpdateDate(Context paramContext)
  {
    date1 = paramContext.getSharedPreferences("date1", 0);
    return date1.getString("date1", "na");
  }
  
  public static Bitmap getBitmap(Context paramContext, String paramString)
  {
    try
    {
      if (Environment.getExternalStorageState().equals("mounted")) {}
      for (paramContext = new File(paramContext.getExternalCacheDir(), "Assets");; paramContext = paramContext.getCacheDir()) {
        return BitmapFactory.decodeFile(paramContext.getAbsolutePath() + paramString + ".png");
      }
      return null;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean getCelcius(Context paramContext)
  {
    cel = paramContext.getSharedPreferences("cel", 0);
    return cel.getBoolean("cel", false);
  }
  
  public static String getCountry(Context paramContext)
  {
    country = paramContext.getSharedPreferences("country", 0);
    return country.getString("country", "");
  }
  
  public static int getCurrentAdNumber(Context paramContext)
  {
    ad_no = paramContext.getSharedPreferences("ad_no", 0);
    return ad_no.getInt("ad_no", -1);
  }
  
  public static int getDayCount(Context paramContext)
  {
    day_count = paramContext.getSharedPreferences("day_count", 0);
    return day_count.getInt("day_count", 0);
  }
  
  /* Error */
  public static Dialog getDialogData(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: new 123	org/json/JSONObject
    //   3: dup
    //   4: aload_0
    //   5: invokestatic 127	content/only/skeleton/util/Globals:getMetaData	(Landroid/content/Context;)Ljava/lang/String;
    //   8: invokespecial 130	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   11: astore_2
    //   12: aload_2
    //   13: aload_0
    //   14: invokestatic 133	content/only/skeleton/util/Globals:getCountry	(Landroid/content/Context;)Ljava/lang/String;
    //   17: invokevirtual 137	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   20: pop
    //   21: aload_2
    //   22: aload_0
    //   23: invokestatic 133	content/only/skeleton/util/Globals:getCountry	(Landroid/content/Context;)Ljava/lang/String;
    //   26: invokevirtual 137	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   29: aload_0
    //   30: invokestatic 140	content/only/skeleton/util/Globals:getLang	(Landroid/content/Context;)Ljava/lang/String;
    //   33: invokevirtual 137	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   36: pop
    //   37: aload_2
    //   38: aload_0
    //   39: invokestatic 133	content/only/skeleton/util/Globals:getCountry	(Landroid/content/Context;)Ljava/lang/String;
    //   42: invokevirtual 137	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   45: aload_0
    //   46: invokestatic 140	content/only/skeleton/util/Globals:getLang	(Landroid/content/Context;)Ljava/lang/String;
    //   49: invokevirtual 137	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   52: ldc_w 305
    //   55: invokevirtual 137	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   58: aload_1
    //   59: invokevirtual 145	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   62: astore_0
    //   63: aload_0
    //   64: invokevirtual 159	org/json/JSONArray:length	()I
    //   67: iconst_1
    //   68: if_icmpne +46 -> 114
    //   71: new 307	content/only/skeleton/util/Dialog
    //   74: dup
    //   75: aload_0
    //   76: iconst_0
    //   77: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   80: checkcast 237	java/lang/String
    //   83: invokespecial 312	content/only/skeleton/util/Dialog:<init>	(Ljava/lang/String;)V
    //   86: areturn
    //   87: astore_3
    //   88: aload_0
    //   89: ldc -78
    //   91: invokestatic 316	content/only/skeleton/util/Globals:setCountry	(Landroid/content/Context;Ljava/lang/String;)V
    //   94: goto -73 -> 21
    //   97: astore_0
    //   98: aload_0
    //   99: invokevirtual 317	org/json/JSONException:printStackTrace	()V
    //   102: aconst_null
    //   103: areturn
    //   104: astore_3
    //   105: aload_0
    //   106: ldc -76
    //   108: invokestatic 320	content/only/skeleton/util/Globals:setLang	(Landroid/content/Context;Ljava/lang/String;)V
    //   111: goto -74 -> 37
    //   114: aload_0
    //   115: invokevirtual 159	org/json/JSONArray:length	()I
    //   118: iconst_2
    //   119: if_icmpne +27 -> 146
    //   122: new 307	content/only/skeleton/util/Dialog
    //   125: dup
    //   126: aload_0
    //   127: iconst_0
    //   128: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   131: checkcast 237	java/lang/String
    //   134: aload_0
    //   135: iconst_1
    //   136: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   139: checkcast 237	java/lang/String
    //   142: invokespecial 323	content/only/skeleton/util/Dialog:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   145: areturn
    //   146: aload_0
    //   147: invokevirtual 159	org/json/JSONArray:length	()I
    //   150: iconst_3
    //   151: if_icmpne +35 -> 186
    //   154: new 307	content/only/skeleton/util/Dialog
    //   157: dup
    //   158: aload_0
    //   159: iconst_0
    //   160: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   163: checkcast 237	java/lang/String
    //   166: aload_0
    //   167: iconst_1
    //   168: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   171: checkcast 237	java/lang/String
    //   174: aload_0
    //   175: iconst_2
    //   176: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   179: checkcast 237	java/lang/String
    //   182: invokespecial 326	content/only/skeleton/util/Dialog:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   185: areturn
    //   186: aload_0
    //   187: invokevirtual 159	org/json/JSONArray:length	()I
    //   190: iconst_4
    //   191: if_icmpne +43 -> 234
    //   194: new 307	content/only/skeleton/util/Dialog
    //   197: dup
    //   198: aload_0
    //   199: iconst_0
    //   200: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   203: checkcast 237	java/lang/String
    //   206: aload_0
    //   207: iconst_1
    //   208: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   211: checkcast 237	java/lang/String
    //   214: aload_0
    //   215: iconst_2
    //   216: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   219: checkcast 237	java/lang/String
    //   222: aload_0
    //   223: iconst_3
    //   224: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   227: checkcast 237	java/lang/String
    //   230: invokespecial 329	content/only/skeleton/util/Dialog:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   233: areturn
    //   234: aload_0
    //   235: invokevirtual 159	org/json/JSONArray:length	()I
    //   238: iconst_5
    //   239: if_icmpne -137 -> 102
    //   242: new 307	content/only/skeleton/util/Dialog
    //   245: dup
    //   246: aload_0
    //   247: iconst_0
    //   248: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   251: checkcast 237	java/lang/String
    //   254: aload_0
    //   255: iconst_1
    //   256: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   259: checkcast 237	java/lang/String
    //   262: aload_0
    //   263: iconst_2
    //   264: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   267: checkcast 237	java/lang/String
    //   270: aload_0
    //   271: iconst_3
    //   272: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   275: checkcast 237	java/lang/String
    //   278: aload_0
    //   279: iconst_4
    //   280: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   283: checkcast 237	java/lang/String
    //   286: invokespecial 332	content/only/skeleton/util/Dialog:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   289: astore_0
    //   290: aload_0
    //   291: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	292	0	paramContext	Context
    //   0	292	1	paramString	String
    //   11	27	2	localJSONObject	JSONObject
    //   87	1	3	localException1	Exception
    //   104	1	3	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   12	21	87	java/lang/Exception
    //   0	12	97	org/json/JSONException
    //   12	21	97	org/json/JSONException
    //   21	37	97	org/json/JSONException
    //   37	87	97	org/json/JSONException
    //   88	94	97	org/json/JSONException
    //   105	111	97	org/json/JSONException
    //   114	146	97	org/json/JSONException
    //   146	186	97	org/json/JSONException
    //   186	234	97	org/json/JSONException
    //   234	290	97	org/json/JSONException
    //   21	37	104	java/lang/Exception
  }
  
  public static Drawable getDrawable(Context paramContext, String paramString)
  {
    try
    {
      if (Environment.getExternalStorageState().equals("mounted")) {}
      for (paramContext = new File(paramContext.getExternalCacheDir(), "Assets");; paramContext = paramContext.getCacheDir()) {
        return new BitmapDrawable(BitmapFactory.decodeFile(paramContext.getAbsolutePath() + paramString + ".png"));
      }
      return null;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static String getGA(Context paramContext)
  {
    ga = paramContext.getSharedPreferences("ga", 0);
    return ga.getString("ga", "UA-40641021-1");
  }
  
  public static String getLang(Context paramContext)
  {
    lang = paramContext.getSharedPreferences("lang", 0);
    return lang.getString("lang", "na");
  }
  
  public static String getMetaData(Context paramContext)
  {
    screenData = paramContext.getSharedPreferences("screenData", 0);
    return screenData.getString("screenData", "");
  }
  
  public static String getMetadataUpdateDate(Context paramContext)
  {
    date = paramContext.getSharedPreferences("date", 0);
    return date.getString("date", "na");
  }
  
  /* Error */
  public static String getScreenData(Context paramContext, String paramString, int paramInt)
  {
    // Byte code:
    //   0: new 123	org/json/JSONObject
    //   3: dup
    //   4: aload_0
    //   5: invokestatic 127	content/only/skeleton/util/Globals:getMetaData	(Landroid/content/Context;)Ljava/lang/String;
    //   8: invokespecial 130	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   11: astore_3
    //   12: aload_3
    //   13: aload_0
    //   14: invokestatic 133	content/only/skeleton/util/Globals:getCountry	(Landroid/content/Context;)Ljava/lang/String;
    //   17: invokevirtual 137	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   20: pop
    //   21: aload_3
    //   22: aload_0
    //   23: invokestatic 133	content/only/skeleton/util/Globals:getCountry	(Landroid/content/Context;)Ljava/lang/String;
    //   26: invokevirtual 137	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   29: aload_0
    //   30: invokestatic 140	content/only/skeleton/util/Globals:getLang	(Landroid/content/Context;)Ljava/lang/String;
    //   33: invokevirtual 137	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   36: pop
    //   37: aload_3
    //   38: aload_0
    //   39: invokestatic 133	content/only/skeleton/util/Globals:getCountry	(Landroid/content/Context;)Ljava/lang/String;
    //   42: invokevirtual 137	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   45: aload_0
    //   46: invokestatic 140	content/only/skeleton/util/Globals:getLang	(Landroid/content/Context;)Ljava/lang/String;
    //   49: invokevirtual 137	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   52: ldc_w 357
    //   55: invokevirtual 137	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   58: aload_1
    //   59: invokevirtual 145	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   62: iload_2
    //   63: invokevirtual 311	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   66: checkcast 237	java/lang/String
    //   69: areturn
    //   70: astore 4
    //   72: aload_0
    //   73: ldc -78
    //   75: invokestatic 316	content/only/skeleton/util/Globals:setCountry	(Landroid/content/Context;Ljava/lang/String;)V
    //   78: goto -57 -> 21
    //   81: astore_0
    //   82: aload_0
    //   83: invokevirtual 317	org/json/JSONException:printStackTrace	()V
    //   86: aconst_null
    //   87: areturn
    //   88: astore 4
    //   90: aload_0
    //   91: ldc -76
    //   93: invokestatic 320	content/only/skeleton/util/Globals:setLang	(Landroid/content/Context;Ljava/lang/String;)V
    //   96: goto -59 -> 37
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	paramContext	Context
    //   0	99	1	paramString	String
    //   0	99	2	paramInt	int
    //   11	27	3	localJSONObject	JSONObject
    //   70	1	4	localException1	Exception
    //   88	1	4	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   12	21	70	java/lang/Exception
    //   0	12	81	org/json/JSONException
    //   12	21	81	org/json/JSONException
    //   21	37	81	org/json/JSONException
    //   37	70	81	org/json/JSONException
    //   72	78	81	org/json/JSONException
    //   90	96	81	org/json/JSONException
    //   21	37	88	java/lang/Exception
  }
  
  public static int getVersion(Context paramContext)
  {
    version = paramContext.getSharedPreferences("version", 0);
    return version.getInt("version", 6);
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    boolean bool = false;
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isZipExists(Context paramContext)
  {
    if (Environment.getExternalStorageState().equals("mounted")) {}
    for (paramContext = new File(paramContext.getExternalCacheDir(), "Assets");; paramContext = paramContext.getCacheDir())
    {
      paramContext = new File(paramContext.getAbsolutePath() + "Assets.zip");
      Log.e("Assets exist?", paramContext.exists() + ";;");
      return paramContext.exists();
    }
  }
  
  public static void keepTheLightsOn(Context paramContext)
  {
    wl = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(1, paramContext.getClass().getName());
    wl.acquire();
  }
  
  public static void releaseWakeLock()
  {
    try
    {
      wl.release();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static void setAds(Context paramContext, String paramString)
  {
    ads = paramContext.getSharedPreferences("ads", 0);
    paramContext = ads.edit();
    paramContext.putString("ads", paramString);
    paramContext.commit();
  }
  
  public static void setAirPushOption(Context paramContext, int paramInt)
  {
    airpush = paramContext.getSharedPreferences("airpush", 0);
    paramContext = airpush.edit();
    paramContext.putInt("airpush", paramInt);
    paramContext.commit();
  }
  
  public static void setAssetUpdateDate(Context paramContext, String paramString)
  {
    date1 = paramContext.getSharedPreferences("date1", 0);
    paramContext = date1.edit();
    paramContext.putString("date1", paramString);
    paramContext.commit();
  }
  
  public static void setCountry(Context paramContext, String paramString)
  {
    country = paramContext.getSharedPreferences("country", 0);
    paramContext = country.edit();
    paramContext.putString("country", paramString);
    paramContext.commit();
  }
  
  public static void setCurrentAdNumber(Context paramContext, int paramInt)
  {
    ad_no = paramContext.getSharedPreferences("ad_no", 0);
    paramContext = ad_no.edit();
    paramContext.putInt("ad_no", paramInt);
    paramContext.commit();
  }
  
  public static void setDayCount(Context paramContext, int paramInt)
  {
    day_count = paramContext.getSharedPreferences("day_count", 0);
    paramContext = day_count.edit();
    paramContext.putInt("day_count", paramInt);
    paramContext.commit();
  }
  
  public static void setDrawable(Context paramContext, View paramView, String paramString)
  {
    paramContext = getDrawable(paramContext, paramString);
    if (paramContext != null) {
      paramView.setBackgroundDrawable(paramContext);
    }
  }
  
  public static void setGA(Context paramContext, String paramString)
  {
    ga = paramContext.getSharedPreferences("ga", 0);
    paramContext = ga.edit();
    paramContext.putString("ga", paramString);
    paramContext.commit();
  }
  
  public static void setLang(Context paramContext, String paramString)
  {
    lang = paramContext.getSharedPreferences("lang", 0);
    paramContext = lang.edit();
    paramContext.putString("lang", paramString);
    paramContext.commit();
  }
  
  public static void setMetaData(Context paramContext, String paramString)
  {
    screenData = paramContext.getSharedPreferences("screenData", 0);
    paramContext = screenData.edit();
    paramContext.putString("screenData", paramString);
    paramContext.commit();
  }
  
  public static void setMetadataUpdateDate(Context paramContext, String paramString)
  {
    date = paramContext.getSharedPreferences("date", 0);
    paramContext = date.edit();
    paramContext.putString("date", paramString);
    paramContext.commit();
  }
  
  public static void setText(Context paramContext, Button paramButton, String paramString, int paramInt)
  {
    paramContext = getScreenData(paramContext, paramString, paramInt);
    if (paramContext != null) {
      paramButton.setText(paramContext);
    }
  }
  
  public static void setText(Context paramContext, TextView paramTextView, String paramString, int paramInt)
  {
    paramContext = getScreenData(paramContext, paramString, paramInt);
    if (paramContext != null) {
      paramTextView.setText(paramContext);
    }
  }
  
  public static void setUserCountry(Context paramContext)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    if ((localTelephonyManager.getSimCountryIso() != null) && (localTelephonyManager.getSimCountryIso().length() != 0))
    {
      setCountry(paramContext, localTelephonyManager.getSimCountryIso().toLowerCase());
      return;
    }
    if ((localTelephonyManager.getNetworkCountryIso() != null) && (localTelephonyManager.getNetworkCountryIso().length() != 0))
    {
      setCountry(paramContext, localTelephonyManager.getNetworkCountryIso().toLowerCase());
      return;
    }
    setCountry(paramContext, "en");
  }
  
  public static void setVersion(Context paramContext, int paramInt)
  {
    version = paramContext.getSharedPreferences("version", 0);
    paramContext = version.edit();
    paramContext.putInt("version", paramInt);
    paramContext.commit();
  }
  
  public static void showAdNotification(Context paramContext)
  {
    try
    {
      Object localObject1 = getAd(paramContext);
      Object localObject2 = new Intent("android.intent.action.VIEW");
      ((Intent)localObject2).setData(Uri.parse(((Ad)localObject1).getUrl().replaceAll("xoxo", getCountry(paramContext))));
      localObject2 = PendingIntent.getActivity(paramContext, 0, (Intent)localObject2, 0);
      localObject1 = new NotificationCompat.Builder(paramContext).setContentTitle(paramContext.getResources().getString(2131230744)).setContentText(((Ad)localObject1).getText()).setSmallIcon(2130837527).setContentIntent((PendingIntent)localObject2).build();
      localObject2 = (NotificationManager)paramContext.getSystemService("notification");
      ((Notification)localObject1).flags |= 0x10;
      PowerManager.WakeLock localWakeLock = ((PowerManager)paramContext.getApplicationContext().getSystemService("power")).newWakeLock(268435482, "TAG");
      localWakeLock.acquire();
      ((KeyguardManager)paramContext.getApplicationContext().getSystemService("keyguard")).newKeyguardLock("TAG").disableKeyguard();
      ((NotificationManager)localObject2).notify(999, (Notification)localObject1);
      localWakeLock.release();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void startBackgroundService(Context paramContext)
  {
    int i = 0;
    Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        if (i == 0) {
          Log.e("checkServices", "service down");
        }
        if (i == 0) {
          paramContext.startService(new Intent(paramContext, AppDataUpdateService.class));
        }
        return;
      }
      if ("content.only.skeleton.AppDataUpdateService".equals(((ActivityManager.RunningServiceInfo)localIterator.next()).service.getClassName())) {
        i = 1;
      }
    }
  }
}
