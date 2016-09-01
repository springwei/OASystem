package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oasystem.R;

import java.util.List;

import bean.Daly;
import bean.Date;
import bean.User;

/**
 * Created by lqs on 2016/4/14.
 */
public class DateAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
       List<Date> dates;

    public DateAdapter(List<Date> dates, Context context) {
        this.dates = dates;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dates.size();
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
        convertView=inflater.inflate(R.layout.date_item,null);
        TextView name=(TextView)convertView.findViewById(R.id.name);
        TextView department=(TextView)convertView.findViewById(R.id.department);
        TextView role=(TextView)convertView.findViewById(R.id.role);
        TextView topic=(TextView)convertView.findViewById(R.id.topic);
        TextView content=(TextView)convertView.findViewById(R.id.content);
        TextView time=(TextView)convertView.findViewById(R.id.time);
        User user=dates.get(position).getUser();
      //  name.setText(user.getName());
       // department.setText(user.getDepartment());
     //   role.setText(user.getRole());
        topic.setText(dates.get(position).getTopic());
        content.setText((dates.get(position).getContent()));
        time.setText(dates.get(position).getTime());
        return convertView;
    }
}
