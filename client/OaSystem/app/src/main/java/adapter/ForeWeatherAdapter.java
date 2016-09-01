package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amap.api.services.weather.LocalDayWeatherForecast;
import com.oasystem.R;

import java.util.List;

/**
 * Created by lqs on 2016/3/31.
 */
public class ForeWeatherAdapter extends BaseAdapter {
    List<LocalDayWeatherForecast> weathers;
    Context context;
    LayoutInflater inflater;

    public ForeWeatherAdapter(List<LocalDayWeatherForecast> weathers, Context context) {
        this.weathers = weathers;
        this.context = context;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return weathers.size();
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
        convertView=inflater.inflate(R.layout.future_weather_item,null);
        TextView time=(TextView)convertView.findViewById(R.id.time);
        TextView dayTem=(TextView)convertView.findViewById(R.id.day_tem);
        TextView dayWeather=(TextView)convertView.findViewById(R.id.day_weather);
        TextView dayWinDic=(TextView)convertView.findViewById(R.id.day_wind_dic);
        TextView dayWinPow=(TextView)convertView.findViewById(R.id.day_win_pow);
        TextView nightTem=(TextView)convertView.findViewById(R.id.night_tem);
        TextView nightWeather=(TextView)convertView.findViewById(R.id.night_weather);
        TextView nightWinDic=(TextView)convertView.findViewById(R.id.night_wind_dic);
        TextView nightWinPow=(TextView)convertView.findViewById(R.id.night_win_pow);

        LocalDayWeatherForecast weather=weathers.get(position);
        time.setText("预报时间："+weather.getDate());
        dayTem.setText("白天温度："+weather.getDayTemp());
        dayWeather.setText("白天天气:"+weather.getDayWeather());
        dayWinDic.setText("白天风向："+weather.getDayWindDirection()+"风");
        dayWinPow.setText("白天风速："+weather.getDayWindPower()+"级");
        nightTem.setText("夜间温度："+weather.getNightTemp());
        nightWeather.setText("夜间天气："+weather.getNightWeather());
        nightWinDic.setText("夜间风向："+weather.getNightWindDirection()+"风");
        nightWinPow.setText("夜间风速："+weather.getNightWindPower());
        return convertView;
    }
}
