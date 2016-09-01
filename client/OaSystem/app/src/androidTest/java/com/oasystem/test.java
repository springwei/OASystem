package com.oasystem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by lqs on 2016/3/9.
 */
public class test extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ImageView imageView=(ImageView)findViewById(R.id.image);
        imageView.setImageResource(R.drawable.a);
    }
}
