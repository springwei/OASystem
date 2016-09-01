package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oasystem.R;


import java.util.List;

import bean.Notice;

/**
 * Created by lqs on 2016/3/11.
 */
public class NoticeListAdapter  extends BaseAdapter{

    List<Notice> notices;
    Context context;
    LayoutInflater inflater;

    public NoticeListAdapter(Context context, List<Notice> notices) {
        this.context = context;
        this.notices = notices;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return notices.size();
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
        convertView=inflater.inflate(R.layout.notice_list_item,null);
        TextView name=(TextView)convertView.findViewById(R.id.name);
        TextView time=(TextView)convertView.findViewById(R.id.time);
        TextView title=(TextView)convertView.findViewById(R.id.title);
       /* TextView content=(TextView)convertView.findViewById(R.id.content);*/
        Notice notice=notices.get(position);
        name.setText(notice.getIssuer());
        time.setText(notice.getTime());
        title.setText(notice.getTitle());
        /*content.setText(notice.getContent());*/

        return convertView;
    }
}
