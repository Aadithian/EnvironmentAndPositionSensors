package com.example.position;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.position.R;

public class MagneticFieldDataActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor magneticFieldSensor;

    private TextView xTextView, yTextView, zTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);

        // Initialize the SensorManager and the geomagnetic field sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        // Initialize TextViews
        xTextView = findViewById(R.id.textViewXAxis);
        yTextView = findViewById(R.id.textViewYAxis);
        zTextView = findViewById(R.id.textViewZAxis);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the sensor listener when the activity is resumed
        sensorManager.registerListener(this, magneticFieldSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the sensor listener when the activity is paused
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Update the TextViews with the magnetic field data
            xTextView.setText("X: " + x + " μT");
            yTextView.setText("Y: " + y + " μT");
            zTextView.setText("Z: " + z + " μT");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing for now
    }
}
