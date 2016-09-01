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
import bean.User;

/**
 * Created by lqs on 2016/4/14.
 */
public class DalyAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
       List<Daly> dalys;

    public DalyAdapter(List<Daly> dalys, Context context) {
        this.dalys = dalys;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dalys.size();
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
        convertView=inflater.inflate(R.layout.daly_item,null);
        TextView name=(TextView)convertView.findViewById(R.id.name);
        TextView department=(TextView)convertView.findViewById(R.id.department);
        TextView role=(TextView)convertView.findViewById(R.id.role);
        TextView topic=(TextView)convertView.findViewById(R.id.topic);
        TextView content=(TextView)convertView.findViewById(R.id.content);
        TextView time=(TextView)convertView.findViewById(R.id.time);
        User user=dalys.get(position).getUser();
      //  name.setText(user.getName());
       // department.setText(user.getDepartment());
     //   role.setText(user.getRole());
        topic.setText(dalys.get(position).getTopic());
        content.setText((dalys.get(position).getContent()));
        time.setText(dalys.get(position).getTime());
        return convertView;
    }
}
