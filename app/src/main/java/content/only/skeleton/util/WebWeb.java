package content.only.skeleton.util;

import android.content.Context;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class WebWeb {

    public static String fetchAppMetaData(Context c) {

        final StringBuilder builder = new StringBuilder();
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 12000);
        HttpConnectionParams.setSoTimeout(httpParameters, 12000);
        HttpClient client = new DefaultHttpClient(httpParameters);

        HttpGet httpGet = new HttpGet(Globals.METADATA_URL);
        BufferedReader reader;
        try {
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            reader = new BufferedReader(new InputStreamReader(content));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            Log.e("res callWebService", builder.toString());
            JSONObject obj = new JSONObject(builder.toString());
            try {
                Globals.setMetaData(c, builder.toString());
                obj.getJSONObject(Globals.getCountry(c));
            } catch (Exception e) {
                Globals.setCountry(c, "en");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void downloadZip(Context c) {

        try {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(Globals.ASSETS_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
                    return;

                // download the file
                input = connection.getInputStream();

                File cacheDir;
                if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
                    cacheDir = new File(c.getExternalCacheDir(), "Assets");
                else
                    cacheDir = c.getCacheDir();

                output = new FileOutputStream(cacheDir.getAbsolutePath() + "Assets.zip");

                byte data[] = new byte[Globals.BUFFER_SIZE];
                int count;
                while ((count = input.read(data)) != -1)
                    output.write(data, 0, count);

                unpackZip(cacheDir.getAbsolutePath(), "Assets.zip");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
        } finally {
            Globals.releaseWakeLock();
        }
    }

    private static boolean unpackZip(String path, String zipname) {
        InputStream is;
        ZipInputStream zis;
        try {
            String filename;
            is = new FileInputStream(path + zipname);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
            byte[] buffer = new byte[Globals.BUFFER_SIZE];
            int count;

            while ((ze = zis.getNextEntry()) != null) {
                filename = ze.getName();

                // Need to create directories if not exists, or
                // it will generate an Exception...
                if (ze.isDirectory()) {
                    File fmd = new File(path + filename);
                    fmd.mkdirs();
                    continue;
                }

                FileOutputStream fout = new FileOutputStream(path + filename);

                while ((count = zis.read(buffer)) != -1) {
                    fout.write(buffer, 0, count);
                }

                fout.close();
                zis.closeEntry();
            }

            zis.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    /**
     * To check if new data is available, if yes returns true otherwise saves lastmodified date and returns false
     */
    public static boolean isNewMetaDataAvailable(Context c) {
        try {
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 12000);
            HttpConnectionParams.setSoTimeout(httpParameters, 12000);
            HttpClient client = new DefaultHttpClient(httpParameters);

            HttpGet httpGet = new HttpGet(Globals.METADATA_URL);
            HttpResponse response = client.execute(httpGet);

            String lastModified = response.getFirstHeader("last-modified").getValue();
            Log.e("lastModified", lastModified);
            if (lastModified.equals(Globals.getMetadataUpdateDate(c)))
                return false;
            else {
                Globals.setMetadataUpdateDate(c, lastModified);
                return true;
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return false;
    }

    /**
     * To check if new data is available, if yes returns true otherwise saves lastmodified date and returns false
     */
    public static boolean isNewAssetsAvailable(Context c) {
        try {
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 12000);
            HttpConnectionParams.setSoTimeout(httpParameters, 12000);
            HttpClient client = new DefaultHttpClient(httpParameters);

            HttpGet httpGet = new HttpGet(Globals.ASSETS_URL);
            HttpResponse response = client.execute(httpGet);

            String lastModified = response.getFirstHeader("last-modified").getValue();
            Log.e("lastModified", lastModified);
            if (lastModified.equals(Globals.getAssetUpdateDate(c)))
                return false;
            else {
                Globals.setAssetUpdateDate(c, lastModified);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
