package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oasystem.R;

import org.w3c.dom.Text;

import java.util.List;

import bean.LeaveTable;

/**
 * Created by lqs on 2016/3/17.
 */
public class LeaveAdapter extends BaseAdapter {
    List<LeaveTable> leaveTableList;
    Context context;
    LayoutInflater inflater;

    public LeaveAdapter(Context context, List<LeaveTable> leaveTableList) {
        this.context = context;
        this.leaveTableList = leaveTableList;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return leaveTableList.size();
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

        convertView=inflater.inflate(R.layout.leave_list_item,null);
        TextView name=(TextView)convertView.findViewById(R.id.name);
        TextView topic=(TextView)convertView.findViewById(R.id.topic);
     /*   TextView content=(TextView)convertView.findViewById(R.id.content);*/
        TextView beginTime=(TextView)convertView.findViewById(R.id.begin_time);
        TextView endTime=(TextView)convertView.findViewById(R.id.end_time);
        name.setText(leaveTableList.get(position).getUser().getName());
        topic.setText(leaveTableList.get(position).getTopic());
        /*content.setText(leaveTableList.get(position).getContent());*/
        beginTime.setText(leaveTableList.get(position).getBeginTime());
        endTime.setText(leaveTableList.get(position).getEndTime());
       // System.out.print(leaveTableList.get(position).getBeginTime());
        return convertView;
    }
}
