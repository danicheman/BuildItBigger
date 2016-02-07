package com.example.nick.builditbigger;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DisplayJokeActivity extends AppCompatActivity implements DisplayJokeFragment.JokeButtonListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    public void onJokeButtonPressed(Uri uri) {
        //load up that joke!
    }
}
