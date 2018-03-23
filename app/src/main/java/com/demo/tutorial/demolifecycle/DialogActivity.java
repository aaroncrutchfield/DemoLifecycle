package com.demo.tutorial.demolifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity {

    private TextView tvCounter;

    private static int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button btnOk = findViewById(R.id.btn_ok);
        Button btnCancel = findViewById(R.id.btn_cancel);
        tvCounter = findViewById(R.id.tv_counter);

        Intent intent = getIntent();
        counter = intent.getIntExtra("counter", 1);

        Log.d("DialogActivity", "onCreate: " + counter);
        tvCounter.setText(String.valueOf(counter));

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewPrompt();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void createNewPrompt() {
        Intent intent = new Intent(this, DialogActivity.class);
        int newCounter = counter + 1;
        intent.putExtra("counter", newCounter);
        Toast.makeText(this, "Dialog " + newCounter + " created", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        int destroyedCounter = Integer.valueOf(tvCounter.getText().toString());
        Toast.makeText(this, "Dialog " + destroyedCounter + " destroyed", Toast.LENGTH_SHORT).show();
    }
}
