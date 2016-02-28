package com.example.nick.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.nick.builditbigger.EndpointsAsyncTask.JokeLoadListener;

public class LoadingActivity extends AppCompatActivity implements JokeLoadListener {

    private static final String TAG = "LoadingActivity";
    private EndpointsAsyncTask jokeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (jokeTask == null) {
            Log.d(TAG, "onCreate: loading a new joke.");
            jokeTask = new EndpointsAsyncTask();
            jokeTask.execute(this);
        } else {
            Log.d(TAG, "onCreate: Not loading a new joke.");
        }

    }

    /**
     * need to stop loading animation..
     *
     * @param joke a funny Chuck Norris one-liner
     */
    public void onJokeLoaded(String joke) {
        Intent i = new Intent(getApplicationContext(), DisplayJokeActivity.class);
        i.putExtra(DisplayJokeFragment.ARG_KEY, joke);
        startActivity(i);
    }
}
