package com.example.nick.builditbigger;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MainActivityFragment";
    private DisplayJokeFragment.JokeButtonListener mListener;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_main, container, false);
        Button button = (Button)layout.findViewById(R.id.button);
        button.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DisplayJokeFragment.JokeButtonListener) {
            mListener = (DisplayJokeFragment.JokeButtonListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement JokeButtonListener");
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: Registered Click in Fragment, passing back to activity.");
        mListener.onJokeButtonPressed();
    }
}
