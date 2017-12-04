package io.github.haohaozaici.lockscreendemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.github.haohaozaici.lockscreendemo.lockscreen.LockScreenService;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    startService(new Intent(this, LockScreenService.class));

//    stopService(new Intent(this, LockScreenService.class));

  }
}
