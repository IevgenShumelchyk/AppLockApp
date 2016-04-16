package content.only.skeleton.util;

import android.content.Context;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

public class WebWeb
{
  /* Error */
  public static void downloadZip(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 9
    //   3: aconst_null
    //   4: astore 5
    //   6: aconst_null
    //   7: astore 11
    //   9: aconst_null
    //   10: astore 10
    //   12: aconst_null
    //   13: astore 8
    //   15: aconst_null
    //   16: astore_3
    //   17: aload 5
    //   19: astore 4
    //   21: aload 11
    //   23: astore 6
    //   25: aload 9
    //   27: astore 7
    //   29: new 17	java/net/URL
    //   32: dup
    //   33: ldc 19
    //   35: invokespecial 22	java/net/URL:<init>	(Ljava/lang/String;)V
    //   38: invokevirtual 26	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   41: checkcast 28	java/net/HttpURLConnection
    //   44: astore_2
    //   45: aload_2
    //   46: astore_3
    //   47: aload 5
    //   49: astore 4
    //   51: aload 11
    //   53: astore 6
    //   55: aload_2
    //   56: astore 8
    //   58: aload 9
    //   60: astore 7
    //   62: aload_2
    //   63: invokevirtual 31	java/net/HttpURLConnection:connect	()V
    //   66: aload_2
    //   67: astore_3
    //   68: aload 5
    //   70: astore 4
    //   72: aload 11
    //   74: astore 6
    //   76: aload_2
    //   77: astore 8
    //   79: aload 9
    //   81: astore 7
    //   83: aload_2
    //   84: invokevirtual 35	java/net/HttpURLConnection:getResponseCode	()I
    //   87: istore_1
    //   88: iload_1
    //   89: sipush 200
    //   92: if_icmpeq +39 -> 131
    //   95: iconst_0
    //   96: ifeq +11 -> 107
    //   99: new 37	java/lang/NullPointerException
    //   102: dup
    //   103: invokespecial 38	java/lang/NullPointerException:<init>	()V
    //   106: athrow
    //   107: iconst_0
    //   108: ifeq +11 -> 119
    //   111: new 37	java/lang/NullPointerException
    //   114: dup
    //   115: invokespecial 38	java/lang/NullPointerException:<init>	()V
    //   118: athrow
    //   119: aload_2
    //   120: ifnull +7 -> 127
    //   123: aload_2
    //   124: invokevirtual 41	java/net/HttpURLConnection:disconnect	()V
    //   127: invokestatic 46	content/only/skeleton/util/Globals:releaseWakeLock	()V
    //   130: return
    //   131: aload_2
    //   132: astore_3
    //   133: aload 5
    //   135: astore 4
    //   137: aload 11
    //   139: astore 6
    //   141: aload_2
    //   142: astore 8
    //   144: aload 9
    //   146: astore 7
    //   148: aload_2
    //   149: invokevirtual 50	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   152: astore 5
    //   154: aload_2
    //   155: astore_3
    //   156: aload 5
    //   158: astore 4
    //   160: aload 11
    //   162: astore 6
    //   164: aload_2
    //   165: astore 8
    //   167: aload 5
    //   169: astore 7
    //   171: invokestatic 56	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   174: ldc 58
    //   176: invokevirtual 64	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   179: ifeq +142 -> 321
    //   182: aload_2
    //   183: astore_3
    //   184: aload 5
    //   186: astore 4
    //   188: aload 11
    //   190: astore 6
    //   192: aload_2
    //   193: astore 8
    //   195: aload 5
    //   197: astore 7
    //   199: new 66	java/io/File
    //   202: dup
    //   203: aload_0
    //   204: invokevirtual 72	android/content/Context:getExternalCacheDir	()Ljava/io/File;
    //   207: ldc 74
    //   209: invokespecial 77	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   212: astore 9
    //   214: aload_2
    //   215: astore_3
    //   216: aload 5
    //   218: astore 4
    //   220: aload 11
    //   222: astore 6
    //   224: aload_2
    //   225: astore 8
    //   227: aload 5
    //   229: astore 7
    //   231: new 79	java/io/FileOutputStream
    //   234: dup
    //   235: new 81	java/lang/StringBuilder
    //   238: dup
    //   239: aload 9
    //   241: invokevirtual 84	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   244: invokestatic 88	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   247: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   250: ldc 91
    //   252: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   258: invokespecial 99	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   261: astore_0
    //   262: sipush 1024
    //   265: newarray <illegal type>
    //   267: astore_3
    //   268: aload 5
    //   270: aload_3
    //   271: invokevirtual 105	java/io/InputStream:read	([B)I
    //   274: istore_1
    //   275: iload_1
    //   276: iconst_m1
    //   277: if_icmpne +70 -> 347
    //   280: aload 9
    //   282: invokevirtual 84	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   285: ldc 91
    //   287: invokestatic 109	content/only/skeleton/util/WebWeb:unpackZip	(Ljava/lang/String;Ljava/lang/String;)Z
    //   290: pop
    //   291: aload_0
    //   292: ifnull +7 -> 299
    //   295: aload_0
    //   296: invokevirtual 114	java/io/OutputStream:close	()V
    //   299: aload 5
    //   301: ifnull +8 -> 309
    //   304: aload 5
    //   306: invokevirtual 115	java/io/InputStream:close	()V
    //   309: aload_2
    //   310: ifnull +181 -> 491
    //   313: aload_2
    //   314: invokevirtual 41	java/net/HttpURLConnection:disconnect	()V
    //   317: invokestatic 46	content/only/skeleton/util/Globals:releaseWakeLock	()V
    //   320: return
    //   321: aload_2
    //   322: astore_3
    //   323: aload 5
    //   325: astore 4
    //   327: aload 11
    //   329: astore 6
    //   331: aload_2
    //   332: astore 8
    //   334: aload 5
    //   336: astore 7
    //   338: aload_0
    //   339: invokevirtual 118	android/content/Context:getCacheDir	()Ljava/io/File;
    //   342: astore 9
    //   344: goto -130 -> 214
    //   347: aload_0
    //   348: aload_3
    //   349: iconst_0
    //   350: iload_1
    //   351: invokevirtual 122	java/io/OutputStream:write	([BII)V
    //   354: goto -86 -> 268
    //   357: astore 8
    //   359: aload_2
    //   360: astore_3
    //   361: aload 5
    //   363: astore 4
    //   365: aload_0
    //   366: astore 6
    //   368: aload 8
    //   370: invokevirtual 125	java/lang/Exception:printStackTrace	()V
    //   373: aload_0
    //   374: ifnull +7 -> 381
    //   377: aload_0
    //   378: invokevirtual 114	java/io/OutputStream:close	()V
    //   381: aload 5
    //   383: ifnull +8 -> 391
    //   386: aload 5
    //   388: invokevirtual 115	java/io/InputStream:close	()V
    //   391: aload_2
    //   392: ifnull -75 -> 317
    //   395: aload_2
    //   396: invokevirtual 41	java/net/HttpURLConnection:disconnect	()V
    //   399: goto -82 -> 317
    //   402: astore_0
    //   403: invokestatic 46	content/only/skeleton/util/Globals:releaseWakeLock	()V
    //   406: aload_0
    //   407: athrow
    //   408: astore_0
    //   409: aload_3
    //   410: astore_2
    //   411: aload 6
    //   413: ifnull +8 -> 421
    //   416: aload 6
    //   418: invokevirtual 114	java/io/OutputStream:close	()V
    //   421: aload 4
    //   423: ifnull +8 -> 431
    //   426: aload 4
    //   428: invokevirtual 115	java/io/InputStream:close	()V
    //   431: aload_2
    //   432: ifnull +7 -> 439
    //   435: aload_2
    //   436: invokevirtual 41	java/net/HttpURLConnection:disconnect	()V
    //   439: aload_0
    //   440: athrow
    //   441: astore_0
    //   442: goto -39 -> 403
    //   445: astore_0
    //   446: goto -137 -> 309
    //   449: astore_3
    //   450: goto -19 -> 431
    //   453: astore_3
    //   454: aload 5
    //   456: astore 4
    //   458: aload_0
    //   459: astore 6
    //   461: aload_3
    //   462: astore_0
    //   463: goto -52 -> 411
    //   466: astore_0
    //   467: goto -76 -> 391
    //   470: astore_0
    //   471: aload 8
    //   473: astore_2
    //   474: aload_0
    //   475: astore 8
    //   477: aload 7
    //   479: astore 5
    //   481: aload 10
    //   483: astore_0
    //   484: goto -125 -> 359
    //   487: astore_0
    //   488: goto -369 -> 119
    //   491: goto -174 -> 317
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	494	0	paramContext	Context
    //   87	264	1	i	int
    //   44	430	2	localObject1	Object
    //   16	394	3	localObject2	Object
    //   449	1	3	localIOException	java.io.IOException
    //   453	9	3	localObject3	Object
    //   19	438	4	localObject4	Object
    //   4	476	5	localObject5	Object
    //   23	437	6	localObject6	Object
    //   27	451	7	localObject7	Object
    //   13	320	8	localObject8	Object
    //   357	115	8	localException	Exception
    //   475	1	8	localContext	Context
    //   1	342	9	localFile	File
    //   10	472	10	localObject9	Object
    //   7	321	11	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   262	268	357	java/lang/Exception
    //   268	275	357	java/lang/Exception
    //   280	291	357	java/lang/Exception
    //   347	354	357	java/lang/Exception
    //   99	107	402	finally
    //   111	119	402	finally
    //   123	127	402	finally
    //   377	381	402	finally
    //   386	391	402	finally
    //   395	399	402	finally
    //   416	421	402	finally
    //   426	431	402	finally
    //   435	439	402	finally
    //   439	441	402	finally
    //   29	45	408	finally
    //   62	66	408	finally
    //   83	88	408	finally
    //   148	154	408	finally
    //   171	182	408	finally
    //   199	214	408	finally
    //   231	262	408	finally
    //   338	344	408	finally
    //   368	373	408	finally
    //   295	299	441	finally
    //   304	309	441	finally
    //   313	317	441	finally
    //   295	299	445	java/io/IOException
    //   304	309	445	java/io/IOException
    //   416	421	449	java/io/IOException
    //   426	431	449	java/io/IOException
    //   262	268	453	finally
    //   268	275	453	finally
    //   280	291	453	finally
    //   347	354	453	finally
    //   377	381	466	java/io/IOException
    //   386	391	466	java/io/IOException
    //   29	45	470	java/lang/Exception
    //   62	66	470	java/lang/Exception
    //   83	88	470	java/lang/Exception
    //   148	154	470	java/lang/Exception
    //   171	182	470	java/lang/Exception
    //   199	214	470	java/lang/Exception
    //   231	262	470	java/lang/Exception
    //   338	344	470	java/lang/Exception
    //   99	107	487	java/io/IOException
    //   111	119	487	java/io/IOException
  }
  
  public static String fetchAppMetaData(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject1 = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)localObject1, 12000);
    HttpConnectionParams.setSoTimeout((HttpParams)localObject1, 12000);
    localObject1 = new DefaultHttpClient((HttpParams)localObject1);
    Object localObject2 = new HttpGet(Globals.METADATA_URL);
    for (;;)
    {
      try
      {
        localObject1 = new BufferedReader(new InputStreamReader(((HttpClient)localObject1).execute((HttpUriRequest)localObject2).getEntity().getContent()));
        localObject2 = ((BufferedReader)localObject1).readLine();
        if (localObject2 == null) {
          localObject1 = new JSONObject(localStringBuilder.toString());
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        continue;
      }
      try
      {
        Globals.setMetaData(paramContext, localStringBuilder.toString());
        Globals.setGA(paramContext, ((JSONObject)localObject1).getJSONObject("keys").getString("google_analytics"));
        if (((JSONObject)localObject1).getJSONArray("ads") != null) {
          Globals.setAds(paramContext, ((JSONObject)localObject1).getJSONArray("ads").toString());
        }
        ((JSONObject)localObject1).getJSONObject(Globals.getCountry(paramContext));
      }
      catch (Exception localException)
      {
        Globals.setCountry(paramContext, "en");
        localException.printStackTrace();
        continue;
      }
      return localStringBuilder.toString();
      localStringBuilder.append((String)localObject2);
    }
  }
  
  public static boolean isNewAssetsAvailable(Context paramContext)
  {
    try
    {
      Object localObject = new BasicHttpParams();
      HttpConnectionParams.setConnectionTimeout((HttpParams)localObject, 12000);
      HttpConnectionParams.setSoTimeout((HttpParams)localObject, 12000);
      localObject = new DefaultHttpClient((HttpParams)localObject);
      HttpGet localHttpGet = new HttpGet("https://apps.sam-platform.com/53fb2c818957dfa8038b4576/53fba7338957dfa7038b4582/resource");
      Log.e("assets", "https://apps.sam-platform.com/53fb2c818957dfa8038b4576/53fba7338957dfa7038b4582/resource");
      localObject = ((HttpClient)localObject).execute(localHttpGet).getFirstHeader("last-modified").getValue();
      if (((String)localObject).equals(Globals.getAssetUpdateDate(paramContext))) {
        return false;
      }
      Globals.setAssetUpdateDate(paramContext, (String)localObject);
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isNewMetaDataAvailable(Context paramContext)
  {
    try
    {
      Object localObject = new BasicHttpParams();
      HttpConnectionParams.setConnectionTimeout((HttpParams)localObject, 12000);
      HttpConnectionParams.setSoTimeout((HttpParams)localObject, 12000);
      localObject = new DefaultHttpClient((HttpParams)localObject).execute(new HttpGet(Globals.METADATA_URL)).getFirstHeader("last-modified").getValue();
      if (((String)localObject).equals(Globals.getMetadataUpdateDate(paramContext))) {
        return false;
      }
      Globals.setMetadataUpdateDate(paramContext, (String)localObject);
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  private static boolean unpackZip(String paramString1, String paramString2)
  {
    byte[] arrayOfByte;
    Object localObject;
    try
    {
      paramString2 = new ZipInputStream(new BufferedInputStream(new FileInputStream(paramString1 + paramString2)));
      arrayOfByte = new byte['Ѐ'];
      String str;
      for (;;)
      {
        localObject = paramString2.getNextEntry();
        if (localObject == null)
        {
          paramString2.close();
          return true;
        }
        str = ((ZipEntry)localObject).getName();
        if (!((ZipEntry)localObject).isDirectory()) {
          break;
        }
        new File(paramString1 + str).mkdirs();
      }
      localObject = new FileOutputStream(paramString1 + str);
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      return false;
    }
    for (;;)
    {
      int i = paramString2.read(arrayOfByte);
      if (i == -1)
      {
        ((FileOutputStream)localObject).close();
        paramString2.closeEntry();
        break;
      }
      ((FileOutputStream)localObject).write(arrayOfByte, 0, i);
    }
  }
}

