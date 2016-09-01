package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oasystem.R;

import java.util.List;

import bean.Sign;

/**
 * Created by lqs on 2016/3/30.
 */
public class SignAdapter extends BaseAdapter {
    List<Sign> signs;
    Context context;
    LayoutInflater inflater;
    public SignAdapter(List<Sign> signs, Context context) {
        this.signs = signs;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return signs.size();
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
        convertView=inflater.inflate(R.layout.manger_check_item,null);
        TextView address=(TextView)convertView.findViewById(R.id.address);
        TextView name=(TextView)convertView.findViewById(R.id.name);
        TextView time=(TextView)convertView.findViewById(R.id.time);
        Sign sign=signs.get(position);
        name.setText("签到人员："+sign.getUsername());
        address.setText("签到地点："+sign.getAddress());
        time.setText("签到时间："+sign.getTime());
        return convertView;
    }
}
