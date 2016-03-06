package com.example.nick.builditbigger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DisplayJokeActivity extends AppCompatActivity implements DisplayJokeFragment.JokeButtonListener {

    private static final String TAG = "DisplayJokeActivity";
    public String joke = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(DisplayJokeFragment.ARG_KEY)) {
            joke = extras.getString(DisplayJokeFragment.ARG_KEY);
        } else {
            Log.e(TAG, "Loaded display joke activity but didn't get a joke.");
        }
        setContentView(R.layout.activity_display_joke);

    }


    @Override
    public void onJokeButtonPressed() {

        Intent i;

        if (BuildConfig.HAS_ADS) {
            i = new Intent(getApplicationContext(), FreeLoadingActivity.class);
        } else {
            i = new Intent(getApplicationContext(), LoadingActivity.class);
        }

        //pressing back should not automatically load another joke.
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);

    }
}
