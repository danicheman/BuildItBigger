package com.example.nick.builditbigger;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

public class DisplayJokeActivity extends AppCompatActivity implements DisplayJokeFragment.JokeButtonListener {

    private static final String TAG = "DisplayJokeActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    public void onJokeButtonPressed(Uri uri) {

        //load up that joke!
        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));
    }
}
