package com.example.position;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.position.R;


public class MainActivity extends Activity implements View.OnClickListener, SensorEventListener {
    private Button btnGameRotation, btnGeomagneticRotation, btnMagneticField, btnOrientation, btnProximity;
    private TextView textViewDistancePlaceholder;

    private SensorManager sensorManager;
    private Sensor proximitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        btnGameRotation = findViewById(R.id.btnGameRotationVector);
        btnGeomagneticRotation = findViewById(R.id.btnGeomagneticRotationVector);
        btnMagneticField = findViewById(R.id.btnMagneticField);
        btnOrientation = findViewById(R.id.btnOrientation);
        btnProximity = findViewById(R.id.btnProximity);

        // Initialize sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        // Set click listeners
        btnGameRotation.setOnClickListener(this);
        btnGeomagneticRotation.setOnClickListener(this);
        btnMagneticField.setOnClickListener(this);
        btnOrientation.setOnClickListener(this);
        btnProximity.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register proximity sensor listener
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister proximity sensor listener to save battery
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        if (v.getId() == R.id.btnGameRotationVector) {
            intent = new Intent(this, GameRotationDataActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnGeomagneticRotationVector) {
            intent = new Intent(this, GeomagneticRotationDataActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnMagneticField) {
            intent = new Intent(this, MagneticFieldDataActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnOrientation) {
            intent = new Intent(this, OrientationDataActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnProximity) {
            intent = new Intent(this, ProximityDataActivity.class);
            startActivity(intent);
        }
    }

}
