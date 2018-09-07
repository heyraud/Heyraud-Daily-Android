package io.github.heyraud.daily.android.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 描述
 *
 * @author aero.tang
 * @version 2018/9/6 21:54
 */
public class RemoteService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
