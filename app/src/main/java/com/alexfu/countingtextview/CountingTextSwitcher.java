package com.alexfu.countingtextview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * CountingTextView2
 *
 * @author alexfu
 */
public class CountingTextSwitcher extends FrameLayout {
  private int current = -1, ceiling = 0;
  private int counter = 0;

  private boolean continuous;

  public CountingTextSwitcher(Context context) {
    super(context);
  }

  public CountingTextSwitcher(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CountingTextSwitcher(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    View offscreen = getChildAt(resolveIndex(counter + 1));
    offscreen.setTranslationY(getHeight());
  }

  public void incrementTo(int finalValue) {
    continuous = true;
    ceiling = finalValue;
    showNext();
  }

  public void increment() {
    continuous = false;
    showNext();
  }

  public void reset() {
    current = -1;
  }

  private void showNext() {
    final TextView view = (TextView) getChildAt(resolveIndex(counter));
    final TextView offscreen = (TextView) getChildAt(resolveIndex(counter + 1));

    offscreen.setText(String.valueOf(++current));

    AnimatorSet set = new AnimatorSet();
    set.playTogether(makeOnScreenAnimator(view), makeOffScreenAnimator(offscreen));
    set.setInterpolator(new AccelerateInterpolator());

    if (continuous) {
      int duration = calculateFrameDuration(1000);
      set.setDuration(duration);
    } else {
      set.setDuration(300);
    }

    set.start();
    counter++;
  }

  private AnimatorSet makeOnScreenAnimator(final View view) {
    AnimatorSet animations = new AnimatorSet();
    Animator translate = ObjectAnimator.ofFloat(view, "translationY", -getHeight());
    Animator alpha = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
    animations.playTogether(translate, alpha);
    animations.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        view.setTranslationY(getHeight());
      }
    });
    return animations;
  }

  private AnimatorSet makeOffScreenAnimator(View view) {
    AnimatorSet animations = new AnimatorSet();
    Animator translate = ObjectAnimator.ofFloat(view, "translationY", 0);
    Animator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
    animations.playTogether(translate, alpha);
    animations.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        if (current < ceiling) {
          showNext();
        }
      }
    });
    return animations;
  }

  private int calculateFrameDuration(int maxDuration) {
    return ceiling/maxDuration;
  }

  /**
   * Calculates view index given the number of counts that have happened.
   */
  private int resolveIndex(int counter) {
    return counter % 2;
  }
}
