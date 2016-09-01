/*
package ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
 import com.oasystem.R;


*/
/**
 * Created by lqs on 2016/3/8.
 *//*

public class MixView extends Activity{
    int[] images=new int[]{
            R.drawable.daily,
    };
    int currentImg=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        LinearLayout main=(LinearLayout)findViewById(R.id.root);
        final ImageView image=new ImageView(this);
        main.addView(image);
        image.setImageResource(images[currentImg]);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(images[++currentImg % images.length]);
            }
        });
    }
}
*/
