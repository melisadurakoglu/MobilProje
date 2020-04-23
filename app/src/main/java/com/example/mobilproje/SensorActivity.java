package com.example.mobilproje;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SensorActivity extends AppCompatActivity {

    TextView tv2;
    SensorManager sensorManager;
    SensorEventListener sensorEventListener;
    Sensor lightSensor,accelerometerSensor;
    Timer timer;
    TimerTask timerTask;
    Handler timerHandler;
    CountDownTimer counterTimer;

    private int second=0;
    List<Sensor> listSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        tv2=(TextView)findViewById(R.id.tv2);
        tv2.setVisibility(View.GONE);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        listSensor = sensorManager.getSensorList(Sensor.TYPE_ALL);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (accelerometerSensor == null) {
            Toast.makeText(this, "AccelerometerSensor is not found.", Toast.LENGTH_LONG).show();
            finish();
        }
        if (lightSensor == null) {
            Toast.makeText(this, "LightSensor is not found.", Toast.LENGTH_LONG).show();
            finish();
        }
        List<Sensor> mList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (int i = 1; i < mList.size(); i++) {
            tv2.setVisibility(View.VISIBLE);
            tv2.append("\n" + mList.get(i).getName() + "\n" + mList.get(i).getVendor() + "\n" + mList.get(i).getVersion());
        }
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                second++;
            }
        },0,1000);//Her 1000 milisaniyede(1 saniyede) bir second değerini 1 artır.

        sensorEventListener=new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.sensor.getType()==Sensor.TYPE_GYROSCOPE){ //Sensör jiroskopsa jiroskopu çalıştır.
                    float x = event.values[0]; //sensor değerini okumak için.
                    float y = event.values[1];
                    float z = event.values[2];
                    if(x != 0 || y != 0 || z != 0)//Hareket algılandığında sıfırla.
                    {
                        second=0;
                    }
                    if (x == 0 && y == 0 && z == 0) {//Eğer hareket yoksa saniyeyi kontrol et.
                        if(second==5){//Eğer 5 saniye boyunca hareketsiz ise uygulamayı kapat.
                            finish();
                        }

                    }
                }

                if(event.sensor.getType()==Sensor.TYPE_LIGHT){ //Sensör light sensörse light sensörü çalıştır.
                    float deger = event.values[0]; //sensor değerini okumak için.
                    if (deger > 100){
                        tv2.setBackgroundColor(Color.BLACK);
                        tv2.setTextColor(Color.WHITE);
                    }
                    else {
                        tv2.setBackgroundColor(Color.WHITE);
                        tv2.setTextColor(Color.BLACK);
                    }

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener,lightSensor,SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(sensorEventListener,accelerometerSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
}
