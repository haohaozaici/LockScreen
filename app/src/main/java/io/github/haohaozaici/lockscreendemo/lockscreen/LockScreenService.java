package io.github.haohaozaici.lockscreendemo.lockscreen;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by haohao on 2017/11/29.
 */

public class LockScreenService extends Service {

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {

    IntentFilter mScreenOffFilter = new IntentFilter();
    mScreenOffFilter.addAction(Intent.ACTION_SCREEN_OFF);
    registerReceiver(mScreenOffReceiver, mScreenOffFilter);

    return super.onStartCommand(intent, flags, startId);
  }

  private BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver() {
    @SuppressWarnings("deprecation")
    @Override
    public void onReceive(Context context, Intent intent) {
      if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
        Intent mLockIntent = new Intent(context, LockScreenActivity.class);
        mLockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
            | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(mLockIntent);
      }
    }
  };


}
