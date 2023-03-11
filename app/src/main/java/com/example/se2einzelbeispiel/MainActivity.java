package com.example.se2einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText matrikelnummer;
    TextView output;
    Button send;
    Button calc;
    static Handler handler;
    int ggt;


    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matrikelnummer = findViewById(R.id.matrikelnummerInput);
        output = findViewById(R.id.output);
        send = findViewById(R.id.sendButton);
        calc = findViewById(R.id.calcButton);

        //2.2
        calc.setOnClickListener(v -> calculateExercise3(String.valueOf(matrikelnummer.getText())));

        //2.1 Message vom Server ueber Handler erhalten und ausgeben
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                output.setText(msg.getData().get("key").toString());
            }
        };
    }
    //2.1 Input vom User an SendAndReceive uebegeben
    public void send(View view){
        SendAndReceive sendAndReceive = new SendAndReceive(handler);
        sendAndReceive.execute(matrikelnummer.getText().toString());
    }


    //2.2 Aufgabe 3
    //Anmerkungen:
    // - ggt von der Ziffer 0 nicht beruecksichtigt
    // - die Ziffer wird auf sich selbst nicht als ggt gesehen (bei anderen indexes jedoch schon)
    // - Doppelnennungen werden herausgestrichen
    // - Index startet bei 0
    void calculateExercise3(String matrikelnummerString){
        List<String> list = new ArrayList<>();
        int[] matrikelnummerArray = new int[matrikelnummerString.length()];
        for (int i = 0; i < matrikelnummerString.length(); i++) {
            matrikelnummerArray[i] = matrikelnummerString.charAt(i) - '0';
        }
        for (int i = 0; i < matrikelnummerArray.length; i++) {
            for (int j = i+1; j < matrikelnummerArray.length; j++) {
                if(matrikelnummerArray[i] > 1 && matrikelnummerArray[j] > 1) {
                    ggt=calcggt(matrikelnummerArray[i], matrikelnummerArray[j]);
                    if (ggt > 1) {
                        list.add("(" + i + ", " + j + ")");
                    }
                }
            }
        }
        if(!list.isEmpty()){
            String outputString = list.toString();
            outputString = outputString.substring(1, outputString.length() - 1);
            output.setText(outputString);
        }else{
            output.setText("");
        }
    }

    public int calcggt(int a, int b){
        while (a != 0 && b != 0) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }


}