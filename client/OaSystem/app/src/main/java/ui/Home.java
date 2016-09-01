/*
package ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.oasystem.R;

*/
/**
 * Created by lqs on 2016/3/9.
 *//*

public class Home extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        GridView gridView=(GridView)findViewById(R.id.gridView1);
        int[] images=new int[]{
          R.drawable.daily,

        };
        gridView.setAdapter(new MyAdapter(images,this));
    }
    private class MyAdapter extends BaseAdapter{
        private  int[] images;
        private Context context;

        public MyAdapter(int[] images, Context context) {
            this.images = images;
            this.context = context;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int resId=images[position];
            ImageView imageView=new ImageView(context);
            imageView.setImageResource(resId);
            return  imageView;
        }
    }

}


*/
