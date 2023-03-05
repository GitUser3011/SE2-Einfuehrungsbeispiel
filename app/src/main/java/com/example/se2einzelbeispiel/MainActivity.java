package com.example.se2einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText matrikelnummer;
    TextView output;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matrikelnummer = findViewById(R.id.matrikelnummerInput);
        output = findViewById(R.id.output);
        button = findViewById(R.id.sendButton);

        button.setOnClickListener(v -> {
            Log.d("Matrikelnummer", matrikelnummer.getText().toString());
            output.setText(matrikelnummer.getText());

        });

    }
}