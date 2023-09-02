package com.example.position;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class GeomagneticRotationDataActivity extends Activity implements SensorEventListener {
    private TextView textViewXAxis, textViewYAxis, textViewZAxis;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);

        // Initialize UI elements
        textViewXAxis = findViewById(R.id.textViewXAxis);
        textViewYAxis = findViewById(R.id.textViewYAxis);
        textViewZAxis = findViewById(R.id.textViewZAxis);

        // Initialize sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);

        // Check if the game rotation vector sensor is available
        if (sensor == null) {
            textViewXAxis.setText("Geomagnetic Rotation Vector sensor not available on this device");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register game rotation vector sensor listener
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister game rotation vector sensor listener to save battery
        if (sensor != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR) {
            float[] rotationMatrix = new float[9];
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);

            // Extract the X, Y, and Z axis values from the rotation matrix
            float[] orientationValues = new float[3];
            SensorManager.getOrientation(rotationMatrix, orientationValues);

            // Convert radians to degrees for a more human-readable format
            float xDegrees = (float) Math.toDegrees(orientationValues[0]);
            float yDegrees = (float) Math.toDegrees(orientationValues[1]);
            float zDegrees = (float) Math.toDegrees(orientationValues[2]);

            // Display the X, Y, and Z axis values in the TextViews
            textViewXAxis.setText("X-Axis: " + xDegrees + "°");
            textViewYAxis.setText("Y-Axis: " + yDegrees + "°");
            textViewZAxis.setText("Z-Axis: " + zDegrees + "°");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }
}
