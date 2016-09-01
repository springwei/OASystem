package com.oasystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.weather.LocalDayWeatherForecast;
import com.amap.api.services.weather.LocalWeatherForecast;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;

import java.util.List;

import adapter.ForeWeatherAdapter;


public class WeatherActivity extends ActionBarActivity implements WeatherSearch.OnWeatherSearchListener {


    WeatherSearchQuery mquery ;
    WeatherSearch mweathersearch;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public mLocationListener1 mLocationListener = new mLocationListener1();
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    GridView grid;
    TextView time,weather,tem,win,hum,futureTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        initView();
        startLocation();
        r();
    }
    public void initView(){
        time=(TextView)findViewById(R.id.time);
        win=(TextView)findViewById(R.id.win);
        weather=(TextView)findViewById(R.id.weather);
        hum=(TextView)findViewById(R.id.hum);
        futureTime=(TextView)findViewById(R.id.future_time);
        grid=(GridView)findViewById(R.id.forever);
        tem=(TextView)findViewById(R.id.tem);

    }
    /**
     * 实时天气查询回调
     */
    @Override
    public void onWeatherLiveSearched(LocalWeatherLiveResult weatherLiveResult, int rCode) {
        if (rCode == 1000) {
            if (weatherLiveResult != null && weatherLiveResult.getLiveResult() != null) {
                LocalWeatherLive weatherlive = weatherLiveResult.getLiveResult();
                time.setText("发布时间:"+weatherlive.getReportTime());
                weather.setText("天气"+weatherlive.getWeather());
                win.setText(weatherlive.getWindDirection() + " 风 " + weatherlive.getWindPower() + " 级");
                tem.setText("温度："+weatherlive.getTemperature()+"°");
                hum.setText("湿度："+weatherlive.getHumidity()+"%");


            }else {
               sendToast("获取失败");
            }
        }else {
           sendToast("获取失败");
        }

    }

    @Override
    public void onWeatherForecastSearched(LocalWeatherForecastResult weatherLiveResult, int rCode) {
        if (rCode == 1000) {
            if (weatherLiveResult != null && weatherLiveResult.getForecastResult() != null) {
                LocalWeatherForecast weatherlive = weatherLiveResult.getForecastResult();
                List<LocalDayWeatherForecast> localWeathers=weatherlive.getWeatherForecast();
                futureTime.setText(weatherlive.getReportTime());
                ForeWeatherAdapter adapter=new ForeWeatherAdapter(localWeathers,WeatherActivity.this);
                grid.setAdapter(adapter);
               /* sendToast(weatherlive.getWeather());
                sendToast(weatherlive.getTemperature()+"°");
                sendToast(weatherlive.getWindDirection()+"风    "+weatherlive.getWindPower()+"级");
                sendToast("湿度："+weatherlive.getHumidity()+"%");*/

            }else {
                sendToast("获取失败");
            }
        }else {
            sendToast("获取失败");
        }


    }

    public class mLocationListener1 implements AMapLocationListener {

        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {

                  initWeather(amapLocation.getCity());
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    sendToast(amapLocation.getErrorInfo());

                }
            }
        }
    }

    public void initWeather(String city){
//检索参数为城市和天气类型，实况天气为WEATHER_TYPE_LIVE、天气预报为WEATHER_TYPE_FORECAST
        mquery = new WeatherSearchQuery(city, WeatherSearchQuery.WEATHER_TYPE_LIVE);
        mweathersearch=new WeatherSearch(this);
        mweathersearch.setOnWeatherSearchListener(this);
        mweathersearch.setQuery(mquery);
        mweathersearch.searchWeatherAsyn(); //异步搜索
        mquery = new WeatherSearchQuery(city, WeatherSearchQuery.WEATHER_TYPE_FORECAST);
        mweathersearch=new WeatherSearch(this);
        mweathersearch.setOnWeatherSearchListener(this);
        mweathersearch.setQuery(mquery);
        mweathersearch.searchWeatherAsyn(); //异步搜索
    }

    public void sendToast(String toast){
       Toast.makeText(WeatherActivity.this,toast,Toast.LENGTH_SHORT).show();
    }
    private void startLocation() {
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
//设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
//设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
//设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
//给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();

    }

    private void r() {
        ImageView imageView=(ImageView)findViewById(R.id.Return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();//停止定位
    }
}
