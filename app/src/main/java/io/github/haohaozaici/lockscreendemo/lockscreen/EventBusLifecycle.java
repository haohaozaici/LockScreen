package io.github.haohaozaici.lockscreendemo.lockscreen;

import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by haohao on 2017/12/4.
 */

public class EventBusLifecycle implements DefaultLifecycleObserver {


  @Override
  public void onStart(@NonNull LifecycleOwner owner) {
    EventBus.getDefault().register(owner);

  }

  @Override
  public void onStop(@NonNull LifecycleOwner owner) {
    EventBus.getDefault().unregister(owner);

  }


}
