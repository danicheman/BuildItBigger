package com.example.nick.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.example.nick.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {


    private static final String TAG = "EndpointsAsyncTask";
    private static MyApi myApiService = null;
    //private Context context;
    private JokeLoadListener mListener;

    @Override
    protected String doInBackground(Context... params) {
        Log.d(TAG, "doInBackground: starting to load joke");

        Context context = params[0];

        //context must have method "onJokeLoaded"
        if (context instanceof JokeLoadListener) {
            mListener = (JokeLoadListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement JokeLoadListener");
        }

        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    //.setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setRootUrl("http://192.168.0.104:8080/_ah/api/")
                    .setApplicationName("MyJokeService")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            //getJoke was sayHi(name)
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mListener.onJokeLoaded(result);
    }

    public interface JokeLoadListener {
        void onJokeLoaded(String joke);
    }
}

