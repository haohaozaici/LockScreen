package io.github.haohaozaici.lockscreendemo.lockscreen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build.VERSION_CODES;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by haohao on 2017/11/29.
 */

public class UnderView extends View {

  private float mStartX;
  private View mMoveView;
  private float mWidth;

  public UnderView(@NonNull Context context) {
    super(context);
    mWidth = getWidth();
    mMoveView = getRootView();
  }

  public UnderView(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public UnderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = VERSION_CODES.LOLLIPOP)
  public UnderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    final int action = event.getAction();
    final float nx = event.getX();
    switch (action) {
      case MotionEvent.ACTION_DOWN:
        mStartX = nx;
        onAnimationEnd();
      case MotionEvent.ACTION_MOVE:
        handleMoveView(nx);
        break;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_CANCEL:
        doTriggerEvent(nx);
        break;
    }
    return true;
  }

  private void handleMoveView(float x) {
    float movex = x - mStartX;
    if (movex < 0) {
      movex = 0;
    }
    mMoveView.setTranslationX(movex);

    float mWidthFloat = (float) mWidth;//屏幕显示宽度
    if (getBackground() != null) {
      getBackground().setAlpha(
          (int) ((mWidthFloat - mMoveView.getTranslationX()) / mWidthFloat * 200));//初始透明度的值为200
    }
  }

  private void doTriggerEvent(float x) {
    float movex = x - mStartX;
    if (movex > (mWidth * 0.4)) {
      moveMoveView(mWidth - mMoveView.getLeft(), true);//自动移动到屏幕右边界之外，并finish掉

    } else {
      moveMoveView(-mMoveView.getLeft(), false);//自动移动回初始位置，重新覆盖
    }
  }

  private void moveMoveView(float to, boolean exit) {
    ObjectAnimator animator = ObjectAnimator.ofFloat(mMoveView, "translationX", to);
    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        if (getBackground() != null) {
          getBackground().setAlpha(
              (int) (((float) mWidth - mMoveView.getTranslationX()) / (float) mWidth * 200));
        }
      }
    });//随移动动画更新背景透明度
    animator.setDuration(250).start();

    if (exit) {
      animator.addListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
//          mainHandler.obtainMessage(LockScreenActivity.MSG_LAUNCH_HOME).sendToTarget();
          super.onAnimationEnd(animation);
        }
      });
    }//监听动画结束，利用Handler通知Activity退出
  }
}
