package io.github.haohaozaici.lockscreendemo.lockscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;

/**
 * Created by haohao on 2017/11/29.
 */

public class LockScreenActivity extends AppCompatActivity {

  public static final String MSG_LAUNCH_HOME = "MSG_LAUNCH_HOME";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);


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
}
