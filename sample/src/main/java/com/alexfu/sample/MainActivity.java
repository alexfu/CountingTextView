package com.alexfu.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alexfu.countingtextview.CountingTextSwitcher;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final CountingTextSwitcher countingTextSwitcher = (CountingTextSwitcher) findViewById(R.id.countingTextSwitcher);
    countingTextSwitcher.setAnimationDuration(200);

    Button increment = (Button) findViewById(R.id.increment);
    Button increment2 = (Button) findViewById(R.id.increment2);

    increment.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        countingTextSwitcher.increment();
      }
    });

    increment2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        countingTextSwitcher.reset();
        countingTextSwitcher.incrementTo(2);
      }
    });
  }
}
