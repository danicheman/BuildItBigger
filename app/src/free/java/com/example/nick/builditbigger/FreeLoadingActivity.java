package com.example.nick.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.nick.builditbigger.EndpointsAsyncTask.JokeLoadListener;

public class FreeLoadingActivity extends AppCompatActivity implements JokeLoadListener, View.OnClickListener {

    private static final String TAG = "FreeLoadingActivity";
    private String mJoke;
    private boolean mTimerFinished = false;
    private AdDisplayTimer adDisplayTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Launch the Async Task to load the joke.
        (new EndpointsAsyncTask()).execute(this);

        findViewById(R.id.continue_button).setOnClickListener(this);

        Log.e(TAG, "onCreate: Correct On Create");
        //10 seconds, no ticks
        adDisplayTimer = new AdDisplayTimer(10000, 10000);
        adDisplayTimer.start();
    }

    /**
     * need to stop loading animation..
     *
     * @param joke a funny Chuck Norris one-liner
     */
    public void onJokeLoaded(String joke) {
        Log.d(TAG, "onJokeLoaded: The Joke loaded.");
        this.mJoke = joke;
        if (mTimerFinished) findViewById(R.id.continue_button).setClickable(true);
    }

    //when the button is clicked, load the joke
    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(), DisplayJokeActivity.class);
        i.putExtra(DisplayJokeFragment.ARG_KEY, mJoke);
        startActivity(i);
    }

    class AdDisplayTimer extends CountDownTimer {

        public AdDisplayTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        //make the button clickable.
        @Override
        public void onFinish() {
            Log.d(TAG, "onFinish: 10 seconds passed");
            if (mJoke != null) {
                findViewById(R.id.continue_button).setClickable(true);
            } else {
                mTimerFinished = true;
            }
        }
    }
}