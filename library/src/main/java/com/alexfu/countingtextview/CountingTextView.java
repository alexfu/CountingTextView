package com.alexfu.countingtextview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by alexfu on 10/21/15.
 */
public class CountingTextView extends TextView {
  private int current, ceiling;

  public CountingTextView(Context context) {
    super(context);
    init();
  }

  public CountingTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public CountingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    setText(String.valueOf(current));
  }

  public void incrementTo(int value) {
    ceiling = value;
    increment();
  }

  public void decrementTo(int value) {
    ceiling = value;
    decrement();
  }

  private void increment() {
    if (current < ceiling) {
      current = (++current) + getSkipCount();
      setText(String.valueOf(current));
      postDelayed(new Runnable() {
        @Override
        public void run() {
          increment();
        }
      }, 5);
    }
  }

  private void decrement() {
    if (current > ceiling) {
      current = (--current) - getSkipCount();
      setText(String.valueOf(current));
      postDelayed(new Runnable() {
        @Override
        public void run() {
          decrement();
        }
      }, 5);
    }
  }

  private int getSkipCount() {
    return (ceiling - current)/100;
  }
}
