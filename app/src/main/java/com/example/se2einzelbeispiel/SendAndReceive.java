package com.example.se2einzelbeispiel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;

import java.io.*;
import java.net.*;
import android.os.Handler;
import android.util.Log;

public class SendAndReceive extends AsyncTask<String, Void, Void> {
    Socket socket;
    PrintWriter pw;
    Handler handler;
    public SendAndReceive(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected Void doInBackground(String[] objects) {
        String msg = String.valueOf(objects[0]);
        try {
            socket = new Socket("se2-isys.aau.at",53212);
            pw = new PrintWriter(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Log.d("Message to send: ",msg);
            pw.println(msg);
            pw.flush();
            while(!br.ready()){
                Thread.sleep(2);
            }
            String message=br.readLine();   //+= wenn es mehrere Lines waeren
            setOutputFromServer(message,"key");
            pw.close();
            socket.close();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void setOutputFromServer(String answerFromServer, String key) {
        Message message1 = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(key, answerFromServer);
        message1.setData(bundle);
        handler.sendMessage(message1);
    }


}
