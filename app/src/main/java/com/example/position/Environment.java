package com.example.position;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Environment extends AppCompatActivity implements SensorEventListener {
    private TextView tempTextView;
    private TextView pressureTextView;
    private TextView lightTextView;
    private TextView humidityTextView; // New TextView for humidity
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private Sensor pressureSensor;
    private Sensor lightSensor;
    private Sensor humiditySensor; // New sensor for humidity
    private boolean isTempAvailable;
    private boolean isPressureAvailable;
    private boolean isLightAvailable;
    private boolean isHumidityAvailable; // Flag to check humidity sensor availability

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.environment);

        tempTextView = findViewById(R.id.tempTextView);
        pressureTextView = findViewById(R.id.pressureTextView);
        lightTextView = findViewById(R.id.lightTextView);
        humidityTextView = findViewById(R.id.humidityTextView); // Initialize the humidity TextView

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Check for temperature sensor availability
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTempAvailable = true;
        } else {
            tempTextView.setText("Temperature sensor not available");
            isTempAvailable = false;
        }

        // Check for pressure sensor availability
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null) {
            pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            isPressureAvailable = true;
        } else {
            pressureTextView.setText("Pressure sensor not available");
            isPressureAvailable = false;
        }

        // Check for light sensor availability
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            isLightAvailable = true;
        } else {
            lightTextView.setText("Light sensor not available");
            isLightAvailable = false;
        }

        // Check for humidity sensor availability
        if (sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null) {
            humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            isHumidityAvailable = true;
        } else {
            humidityTextView.setText("Humidity sensor not available");
            isHumidityAvailable = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE && isTempAvailable) {
            tempTextView.setText("Temperature: " + sensorEvent.values[0] + " °C");
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_PRESSURE && isPressureAvailable) {
            pressureTextView.setText("Pressure: " + sensorEvent.values[0] + " hPa");
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT && isLightAvailable) {
            lightTextView.setText("Light Sensor: " + sensorEvent.values[0] + " lux");
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY && isHumidityAvailable) {
            humidityTextView.setText("Humidity: " + sensorEvent.values[0] + " %");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Handle accuracy changes if needed
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isTempAvailable) {
            sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (isPressureAvailable) {
            sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (isLightAvailable) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (isHumidityAvailable) {
            sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isTempAvailable) {
            sensorManager.unregisterListener(this, tempSensor);
        }
        if (isPressureAvailable) {
            sensorManager.unregisterListener(this, pressureSensor);
        }
        if (isLightAvailable) {
            sensorManager.unregisterListener(this, lightSensor);
        }
        if (isHumidityAvailable) {
            sensorManager.unregisterListener(this, humiditySensor);
        }
    }
}
