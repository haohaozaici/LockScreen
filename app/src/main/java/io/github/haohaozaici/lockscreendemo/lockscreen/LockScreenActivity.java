package io.github.haohaozaici.lockscreendemo.lockscreen;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import io.github.haohaozaici.lockscreendemo.R;
import io.github.haohaozaici.lockscreendemo.lockscreen.swipeback.SwipeBackLayout;
import io.github.haohaozaici.lockscreendemo.lockscreen.swipeback.app.SwipeBackActivity;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by haohao on 2017/11/29.
 */

public class LockScreenActivity extends SwipeBackActivity {

  public static final int MSG_LAUNCH_HOME = 1;
  private static final int VIBRATE_DURATION = 20;

  private SwipeBackLayout mSwipeBackLayout;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.lock_screen_layout);

    //取消系统锁屏
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

    //透明状态栏
    if (Build.VERSION.SDK_INT >= 21) {
      View decorView = getWindow().getDecorView();
      int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
          | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
      decorView.setSystemUiVisibility(option);
//      getWindow().setStatusBarColor(getResources().getColor(R.color.bgColor_overlay));
      getWindow().setStatusBarColor(Color.BLACK);
    }
    ActionBar actionBar = getSupportActionBar();
    actionBar.hide();

    //滑动解锁
    mSwipeBackLayout = getSwipeBackLayout();
    mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_ALL);

  }

  private void vibrate(long duration) {
    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    long[] pattern = {
        0, duration
    };
    vibrator.vibrate(pattern, -1);
  }


  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    int key = event.getKeyCode();
    switch (key) {
      case KeyEvent.KEYCODE_BACK: {
        return true;
      }
      case KeyEvent.KEYCODE_MENU: {
        return true;
      }
    }
    return super.onKeyDown(keyCode, event);
  }

  @Subscribe(threadMode = ThreadMode.POSTING)
  void unLock(UnLockScreen unLockScreen) {
    finish();

  }


}
