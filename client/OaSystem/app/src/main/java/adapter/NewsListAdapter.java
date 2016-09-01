package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oasystem.R;


import java.util.List;

import bean.News;

/**
 * Created by lqs on 2016/3/11.
 */
public class NewsListAdapter  extends BaseAdapter{

    List<News> news1;
    Context context;
    LayoutInflater inflater;

    public NewsListAdapter(Context context, List<News> news1) {
        this.context = context;
        this.news1 = news1;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return news1.size();
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
        convertView=inflater.inflate(R.layout.news_list_item,null);
        TextView name=(TextView)convertView.findViewById(R.id.name);
        TextView time=(TextView)convertView.findViewById(R.id.time);
        TextView title=(TextView)convertView.findViewById(R.id.title);
       /* TextView content=(TextView)convertView.findViewById(R.id.content);*/
        News news=news1.get(position);
        name.setText(news.getIssuer());
        time.setText(news.getTime());
        title.setText(news.getTitle());
        /*content.setText(notice.getContent());*/

        return convertView;
    }
}
