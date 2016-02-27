package com.example.nick.builditbigger;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

public class DisplayJokeActivity extends AppCompatActivity implements DisplayJokeFragment.JokeButtonListener {

    private static final String TAG = "DisplayJokeActivity";
    public String joke = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(DisplayJokeFragment.ARG_KEY)) {
            joke = extras.getString(DisplayJokeFragment.ARG_KEY);
            Log.d(TAG, "onCreate: recieved joke: " + joke);
        } else {
            Log.e(TAG, "Loaded display joke activity but didn't get a joke.");
        }
        setContentView(R.layout.activity_main2);
        /*DisplayJokeFragment displayJokeFragment = new DisplayJokeFragment();
        Bundle argBundle = new Bundle();
        argBundle.putString(displayJokeFragment.ARG_KEY, joke);
        displayJokeFragment.setArguments(argBundle);*/

    }


    @Override
    public void onJokeButtonPressed() {
        Log.d(TAG, "onJokeButtonPressed: Reload a new joke now..");
    }
}
