package com.demo.tutorial.demolifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String ON_CREATE_MESSAGE = ": onCreate - the activity has been created\n\n";
    public static final String ON_SAVED_MESSAGE = ": onSavedInstanceState - data was saved to the Bundle\n\n";
    public static final String ON_START_MESSAGE = ": onStart - the activity is preparing to (re)enter the foreground\n\n";
    public static final String ON_RESUME_MESSAGE = ": onResume - the activity has come to the foreground\n\n";
    public static final String ON_PAUSED_MESSAGE = ": onPaused - the user is leaving the current activity, but the activity is still visible\n\n";
    public static final String ON_STOP_MESSAGE = ": onStop - the activity is no longer visible\n\n";
    public static final String ON_DESTROY_MESSAGE = ": onDestroy - this is the final call; the activity is being destroyed\n\n";

    private TextView tvLog;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for the activity as activity_main.xml
        setContentView(R.layout.activity_main);

        // Initialize views
        tvLog = findViewById(R.id.tv_log);
        Button btnReset = findViewById(R.id.btn_reset);
        Button btnAlert = findViewById(R.id.btn_alert);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertPrompt();
            }
        });

        counter++;

        // Don't concatenate string within the append method. Bad practice
        String message = counter + ON_CREATE_MESSAGE;
        tvLog.append(message);
    }

    // Start an Activity with a Dialog style
    private void alertPrompt() {
        Intent intent = new Intent(this, DialogActivity.class);
        startActivity(intent);
    }

    private void reset() {
        tvLog.setText("");
        counter = 0;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int savedCounter = savedInstanceState.getInt("counter");
        counter = savedCounter;
        counter++;

        String message = counter + ": onRestoredInstanceState - data has been restored\n\n";
        String savedLog = savedInstanceState.getString("logText");
        tvLog.setText(savedLog);
        tvLog.append(message);

    }

    // Be sure to override onSavedInstanceState method that only has the Bundle outState parameter
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        counter++;
        String message = counter + ON_SAVED_MESSAGE;
        tvLog.append(message);

        String logText = tvLog.getText().toString();
        Log.d("MainActivity", "onSaveInstanceState: " + logText);
        outState.putString("logText", logText);
        outState.putInt("counter", counter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        counter++;
        String message = counter + ON_START_MESSAGE;
        tvLog.append(message);
    }

    @Override
    protected void onResume() {
        super.onResume();

        counter++;
        String message = counter + ON_RESUME_MESSAGE;
        tvLog.append(message);
    }

    @Override
    protected void onPause() {
        super.onPause();

        counter++;
        String message = counter + ON_PAUSED_MESSAGE;
        tvLog.append(message);
    }

    @Override
    protected void onStop() {
        super.onStop();

        counter++;
        String message = counter + ON_STOP_MESSAGE;
        tvLog.append(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        counter++;
        String message = counter + ON_DESTROY_MESSAGE;
        tvLog.append(message);
    }
}
