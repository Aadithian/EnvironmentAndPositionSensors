package com.example.position;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log; // Import Log for debugging

public class ProximityDataActivity extends Activity implements SensorEventListener {
    private TextView textViewDistance;
    private SensorManager sensorManager;
    private Sensor proximitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_data);

        // Initialize sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        // Initialize the TextView for displaying proximity data
        textViewDistance = findViewById(R.id.textViewDistance);

        // Check if the proximity sensor is available
        if (proximitySensor == null) {
            textViewDistance.setText("Proximity sensor not available on this device");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register proximity sensor listener
        if (proximitySensor != null) {
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister proximity sensor listener to save battery
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float distance = event.values[0];
            String distanceText;

            if (distance == 0) {
                distanceText = "NEAR";
            } else {
                distanceText = "FAR";
            }

            Log.d("ProximityDataActivity", "Distance: " + distanceText); // Log for debugging
            textViewDistance.setText("Distance: " + distanceText);
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }
}
