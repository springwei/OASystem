package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oasystem.R;

import java.util.List;

import bean.User;

/**
 * Created by lqs on 2016/3/24.
 */
public class UserListAdapter extends BaseAdapter {
    Context context;
    List<User> users;
    LayoutInflater inflater;

    public UserListAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return users.size();
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
        convertView =inflater.inflate(R.layout.users_list_item,null);
        TextView name=(TextView)convertView.findViewById(R.id.name);
        TextView department=(TextView)convertView.findViewById(R.id.department);
        TextView role=(TextView)convertView.findViewById(R.id.role);


        User user=users.get(position);
        name.setText(user.getName());
        department.setText(user.getDepartment());
        role.setText(user.getRole());
        return convertView;
    }
}
