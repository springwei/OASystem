package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oasystem.R;

import java.util.List;

import bean.LeaveTable;
import bean.Money;

/**
 * Created by lqs on 2016/3/17.
 */
public class MoneyAdapter extends BaseAdapter {
    List<Money> MoneyList;
    Context context;
    LayoutInflater inflater;

    public MoneyAdapter(Context context, List<Money> moneyList) {
        this.context = context;
        this.MoneyList = moneyList;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return MoneyList.size();
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

        convertView=inflater.inflate(R.layout.money_list_item,null);
        TextView name=(TextView)convertView.findViewById(R.id.name);
        TextView topic=(TextView)convertView.findViewById(R.id.topic);
        TextView time=(TextView)convertView.findViewById(R.id.time);
        name.setText(MoneyList.get(position).getUser().getName());
        topic.setText(MoneyList.get(position).getTopic());
        time.setText(MoneyList.get(position).getCreatetime());
        return convertView;
    }
}
