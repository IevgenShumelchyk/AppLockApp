package content.only.skeleton.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.rockin.applock2.Chooser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import content.only.skeleton.AppDataUpdateService;

public class Globals {

    public static final String ASSETS_URL = "";
    public static String METADATA_URL = "https://apps.sam-platform.com/52a872a08957dfdc033c9882/52a8d43b8957dfd5033c9884/metadata";
    public static String APK_URL = "https://apps.sam-platform.com/download/52a872a08957dfdc033c9882?country=";
    public static String CHECK_VERSION_URL = "https://apps.sam-platform.com/download/52a872a08957dfdc033c9882/52a8d43b8957dfd5033c9884/version.txt";
    public static final String GA_PROPERTY_ID = "UA-44259046-1";

    public static final String APP_NAME = "Movie Trailers";
    public static Class<?> LAUNCHER_CLASS = Chooser.class; // replace this class name with your launcher class name

    public static final int BUFFER_SIZE = 1024; // give this value according to assets' size
    public static final int APK_BUFFER = 2056; // give this value according to assets' size
    private static final String SERVICE_PAVKAGE_NAME = "content.only.skeleton";

    private static SharedPreferences version;
    private static SharedPreferences country;
    private static SharedPreferences screenData;
    private static SharedPreferences lang;

    private static WakeLock wl;
    private static SharedPreferences date;
    private static SharedPreferences date1;
    private static SharedPreferences ga;

    public static void setVersion(Context c, int ver) {
        version = c.getSharedPreferences("version", 0);
        SharedPreferences.Editor prefs_editor = version.edit();
        prefs_editor.putInt("version", ver);
        prefs_editor.commit();
    }

    public static int getVersion(Context c) {
        version = c.getSharedPreferences("version", 0);
        return version.getInt("version", 3);
    }

    public static void setCountry(Context c, String coun) {
        country = c.getSharedPreferences("country", 0);
        SharedPreferences.Editor prefs_editor = country.edit();
        prefs_editor.putString("country", coun);
        prefs_editor.commit();
    }

    public static String getCountry(Context c) {
        country = c.getSharedPreferences("country", 0);
        return country.getString("country", "");
    }

    public static void setGA(Context c, String id) {
        ga = c.getSharedPreferences("ga", 0);
        SharedPreferences.Editor prefs_editor = ga.edit();
        prefs_editor.putString("ga", id);
        prefs_editor.commit();
    }

    public static String getGA(Context c) {
        ga = c.getSharedPreferences("ga", 0);
        return ga.getString("ga", GA_PROPERTY_ID);
    }

    public static void setMetaData(Context c, String json) {
        screenData = c.getSharedPreferences("screenData", 0);
        SharedPreferences.Editor prefs_editor = screenData.edit();
        prefs_editor.putString("screenData", json);
        prefs_editor.commit();
    }

    public static String getMetaData(Context c) {
        screenData = c.getSharedPreferences("screenData", 0);
        return screenData.getString("screenData", "");
    }

    public static void setLang(Context c, String language) {
        lang = c.getSharedPreferences("lang", 0);
        SharedPreferences.Editor prefs_editor = lang.edit();
        prefs_editor.putString("lang", language);
        prefs_editor.commit();
    }

    public static String getLang(Context c) {
        lang = c.getSharedPreferences("lang", 0);
        return lang.getString("lang", "na");
    }

    public static void setMetadataUpdateDate(Context c, String modified_date) {
        date = c.getSharedPreferences("date", 0);
        SharedPreferences.Editor prefs_editor = date.edit();
        prefs_editor.putString("date", modified_date);
        prefs_editor.commit();
    }

    public static String getMetadataUpdateDate(Context c) {
        date = c.getSharedPreferences("date", 0);
        return date.getString("date", "na");
    }

    public static void setAssetUpdateDate(Context c, String modified_date) {
        date1 = c.getSharedPreferences("date1", 0);
        SharedPreferences.Editor prefs_editor = date1.edit();
        prefs_editor.putString("date1", modified_date);
        prefs_editor.commit();
    }

    public static String getAssetUpdateDate(Context c) {
        date1 = c.getSharedPreferences("date1", 0);
        return date1.getString("date1", "na");
    }

    public static boolean isNetworkAvailable(Context c) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Take CPU lock to prevent it from going off if the user
     * presses the power button during download.
     */
    public static void keepTheLightsOn(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                context.getClass().getName());
        wl.acquire();
    }

    public static void releaseWakeLock() {
        try {
            wl.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isZipExists(Context context) {
        File cacheDir;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir = new File(context.getExternalCacheDir(), "Assets");
        else
            cacheDir = context.getCacheDir();
        File file = new File(cacheDir.getAbsolutePath() + "Assets.zip");
        Log.e("Assets exist?", file.exists() + ";;");
        return file.exists();
    }

    public static String getScreenData(Context c, String screenName, int pos) {
        JSONObject obj;
        try {
            obj = new JSONObject(getMetaData(c));
            try {
                obj.getJSONObject(getCountry(c));
            } catch (Exception e) {
                setCountry(c, "en");
            }
            try {
                obj.getJSONObject(getLang(c));
            } catch (Exception e) {
                setLang(c, "English");
            }
            JSONArray arr = obj.getJSONObject(getCountry(c)).getJSONObject(getLang(c)).getJSONObject("screens").getJSONArray(screenName);
            return (String) arr.get(pos);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Dialog getDialogData(Context c, String dialogName) {
        JSONObject obj;
        try {
            obj = new JSONObject(getMetaData(c));
            try {
                obj.getJSONObject(getCountry(c));
            } catch (Exception e) {
                setCountry(c, "en");
            }
            try {
                obj.getJSONObject(getLang(c));
            } catch (Exception e) {
                setLang(c, "English");
            }
            JSONArray arr = obj.getJSONObject(getCountry(c)).getJSONObject(getLang(c)).getJSONObject("dialogs").getJSONArray(dialogName);
            if (arr.length() == 1)
                return new Dialog((String) arr.get(0));
            else if (arr.length() == 2)
                return new Dialog((String) arr.get(0), (String) arr.get(1));
            else if (arr.length() == 3)
                return new Dialog((String) arr.get(0), (String) arr.get(1), (String) arr.get(2));
            else if (arr.length() == 4)
                return new Dialog((String) arr.get(0), (String) arr.get(1), (String) arr.get(2), (String) arr.get(3));
            else if (arr.length() == 5)
                return new Dialog((String) arr.get(0), (String) arr.get(1), (String) arr.get(2), (String) arr.get(3), (String) arr.get(4));
        } catch (JSONException e) {
//            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    public static Drawable getDrawable(Context c, String name) {
        try {
            File cacheDir;
            if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
                cacheDir = new File(c.getExternalCacheDir(), "Assets");
            else
                cacheDir = c.getCacheDir();
            Bitmap bmp = BitmapFactory.decodeFile(cacheDir.getAbsolutePath() + name + ".png");
            Drawable d = new BitmapDrawable(bmp);
            return d;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setUserCountry(Context c) {
        TelephonyManager man = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
        if (man.getSimCountryIso() != null && man.getSimCountryIso().length() != 0)
            setCountry(c, man.getSimCountryIso().toLowerCase());
        else if (man.getNetworkCountryIso() != null && man.getNetworkCountryIso().length() != 0)
            setCountry(c, man.getNetworkCountryIso().toLowerCase());
        else
            setCountry(c, "en");
    }

    @SuppressWarnings("deprecation")
    public static void setDrawable(Context c, Button button, String drawable_name) {
        Drawable d = getDrawable(c, drawable_name);
        if (d != null)
            button.setBackgroundDrawable(d);
    }

    public static void setText(Context c, TextView textView,
                               String screenName, int position_in_layout) {
        String text = getScreenData(c, screenName, position_in_layout);
        if (text != null)
            textView.setText(text);
    }

    @SuppressWarnings("static-access")
    public static void startBackgroundService(Context c) {
        boolean one = false;
        ActivityManager manager = (ActivityManager) c.getSystemService(c.ACTIVITY_SERVICE);
        for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            Log.e("checkServices on device", service.service.getClassName());
            if ((SERVICE_PAVKAGE_NAME + ".AppDataUpdateService").equals(service.service.getClassName())) {
                one = true;
            }
        }
        if (!one)
            Log.e("checkServices", "service down");
        if (!one)
            c.startService(new Intent(c, AppDataUpdateService.class));
    }

}