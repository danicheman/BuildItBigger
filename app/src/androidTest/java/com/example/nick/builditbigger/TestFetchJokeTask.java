package com.example.nick.builditbigger;

import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;
import android.test.mock.MockContext;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.example.nick.builditbigger.EndpointsAsyncTask.JokeLoadListener;

/**
 * Created by NICK on 2/28/2016.
 * <p/>
 * A connected test to make sure that our Async Task can retrieve Chuck Norris jokes from the
 * server.
 */
public class TestFetchJokeTask extends AndroidTestCase {

    private static final String TAG = "TestFetchJokeTask";
    private String mJoke = "";
    private CountDownLatch signal;
    final private static int TIMEOUT = 8;


    private class TestContext extends MockContext implements JokeLoadListener {
        @Override
        public void onJokeLoaded(String joke) {
            Log.d(TAG, "onJokeLoaded: " + joke);
            mJoke = joke;
            signal.countDown();
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        signal = new CountDownLatch(1);
    }

    public void testFetchJoke() throws InterruptedException {

        (new EndpointsAsyncTask()).execute(new TestContext());
        //wait here for TIMEOUT seconds for the joke to be loaded before continuing.
        signal.await(TIMEOUT, TimeUnit.SECONDS);

        Log.d(TAG, "testFetchJoke() Joke is " + mJoke);
        assertTrue(mJoke.length() > 0);
        assertTrue(mJoke.contains("Chuck Norris"));
    }


}
