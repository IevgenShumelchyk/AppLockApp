package content.only.skeleton;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Window;
import android.view.WindowManager;

import com.rockin.applock2.R;
//import com.sammedia.leadgen.LeadGenSDK;

import org.json.JSONArray;
import org.json.JSONObject;

import content.only.skeleton.util.Globals;
import content.only.skeleton.util.WebWeb;

public class Splash extends Activity {

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);

      //  LeadGenSDK.with(this, "52a872a08957dfdc033c9882", "52a8d43b8957dfd5033c9884");
      //  LeadGenSDK.register();


        Globals.setUserCountry(Splash.this);

        if (Build.VERSION.SDK_INT >= 9) {
            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
            } catch (Exception e) {
            }
        }

        new AssetsDownloadTask().execute();
    }

    private class AssetsDownloadTask extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            Globals.keepTheLightsOn(Splash.this);
            progressDialog = ProgressDialog.show(Splash.this, "",
                    "Downloading App Resources...");
        }

        @Override
        protected Void doInBackground(Void... params) {
            Globals.startBackgroundService(Splash.this);

            if (Globals.getMetaData(Splash.this).length() == 0) { // no metadata available
                WebWeb.isNewMetaDataAvailable(Splash.this);
                WebWeb.fetchAppMetaData(Splash.this);
            }

            return null;
        }

        protected void onPostExecute(Void result) {
            progressDialog.dismiss();
            Globals.releaseWakeLock();
            proceed();
        }
    }

    public void proceed() {
        try {
            JSONObject obj = new JSONObject(Globals.getMetaData(Splash.this));
            JSONArray arr = obj.getJSONObject(Globals.getCountry(Splash.this)).getJSONArray("lang");
            if (arr.length() > 1 && Globals.getLang(Splash.this).equals("na")) {
                Globals.setLang(Splash.this, "English");
                showDialogLang();
            } else {
                finish();
                Intent intent = new Intent(Splash.this, Globals.LAUNCHER_CLASS);
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            finish();
            Intent intent = new Intent(Splash.this, Globals.LAUNCHER_CLASS);
            startActivity(intent);
        }
    }

    public void showDialogLang() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Language");
            JSONObject obj = new JSONObject(Globals.getMetaData(Splash.this));
            JSONArray arr = obj.getJSONObject(Globals.getCountry(Splash.this)).getJSONArray("lang");
            final CharSequence[] items = new CharSequence[arr.length()];
            for (int i = 0; i < arr.length(); i++)
                items[i] = arr.getString(i);
            builder.setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Globals.getLang(Splash.this);
                    Globals.setLang(Splash.this, items[which].toString());
                    finish();
                    Intent intent = new Intent(Splash.this, Globals.LAUNCHER_CLASS);
                    startActivity(intent);
                }
            });
            builder.create();
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}