package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oasystem.R;

import java.util.List;

import bean.Note;

/**
 * Created by lqs on 2016/4/12.
 */
public class NoteListAdapter extends BaseAdapter {
    Context context;
    List<Note> notes;
    LayoutInflater inflater;

    public NoteListAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return notes.size();
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
        convertView=inflater.inflate(R.layout.note_list_item,null);
        TextView note=(TextView)convertView.findViewById(R.id.note);
        String content=notes.get(position).getContent();
        if (content.length()>100){
            note.setText(content.substring(0,98)+"...");
        }
        else{
            note.setText(content);
        }
        TextView createTime=(TextView)convertView.findViewById(R.id.create_time);
        createTime.setText(notes.get(position).getChangeTime());
        return convertView;
    }
}
