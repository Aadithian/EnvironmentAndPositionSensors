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


public class Front extends Activity implements View.OnClickListener {
    private Button btnEnvironmentSensors, btnPositionSensors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Initialize UI elements
        btnEnvironmentSensors = findViewById(R.id.btnEnvironmentSensors);
        btnPositionSensors = findViewById(R.id.btnPositionSensors);

        btnPositionSensors.setOnClickListener(this);
        btnEnvironmentSensors.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        Intent intent;

        if (v.getId() == R.id.btnEnvironmentSensors) {
            intent = new Intent(this, Environment.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnPositionSensors) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}
