package content.only.skeleton;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import content.only.skeleton.util.WebWeb;

public class AppDataUpdateService extends Service {

    private Handler handler;
    private long milliSecondsInAnHour = 3600000;

    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (WebWeb.isNewMetaDataAvailable(AppDataUpdateService.this))
                    WebWeb.fetchAppMetaData(AppDataUpdateService.this);
//				if(WebWeb.isNewAssetsAvailable(AppDataUpdateService.this))
//					WebWeb.downloadZip(AppDataUpdateService.this);
            }
        };

        Thread thr = new Thread(null, new ServiceWorker(), "BackgroundService");
        thr.start();
    }

    class ServiceWorker implements Runnable {
        public void run() {
            boolean run = true;
            while (run) {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                handler.sendMessage(msg);
                long sleepTime = 0;
                sleepTime = milliSecondsInAnHour * 24;
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}
