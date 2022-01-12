package com.example.firebaseexample.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;
import com.example.firebaseexample.R;
import com.google.firebase.auth.FirebaseAuth;

public class MyService extends Service {
    MediaPlayer mediaPlayer;
    private FirebaseAuth mAuth;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        mediaPlayer = MediaPlayer.create(this, R.raw.moonlight);
    }

    private boolean checkIfLoggedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service started! Music playing...", Toast.LENGTH_LONG).show();
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service destroyed! Music stopped...", Toast.LENGTH_LONG).show();
        mediaPlayer.release();
    }
}
