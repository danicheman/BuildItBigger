package com.example.nick.builditbigger;

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
public class MainActivityFragment extends Fragment implements DisplayJokeFragment.OnFragmentInteractionListener {

    private static final String TAG = "MainActivityFragment";
    
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_main, container, false);
        Button button = (Button)layout.findViewById(R.id.button);
        button.setOnClickListener((View.OnClickListener) getActivity());
        return layout;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
