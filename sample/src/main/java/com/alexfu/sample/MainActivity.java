package com.alexfu.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alexfu.countingtextview.CountingTextSwitcher;
import com.alexfu.countingtextview.CountingTextView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final CountingTextView countingTextSwitcher = (CountingTextView) findViewById(R.id.countingTextSwitcher);

    Button increment = (Button) findViewById(R.id.increment);
    Button decrement = (Button) findViewById(R.id.decrement);

    increment.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        countingTextSwitcher.incrementTo(100);
      }
    });

    decrement.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        countingTextSwitcher.decrementTo(50);
      }
    });
  }
}
