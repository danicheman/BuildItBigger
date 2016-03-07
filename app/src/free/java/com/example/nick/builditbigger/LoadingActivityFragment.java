package com.example.nick.builditbigger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by NICK on 3/5/2016.
 * A placeholder fragment containing a simple view.
 */
public class LoadingActivityFragment extends Fragment {

    private static final String TAG = "LoadingActiviyFrag";

    public LoadingActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: Loading view");
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

    /**
     * Load the admob view
     *
     * @param view               the view that was created
     * @param savedInstanceState the state that is being recovered
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onViewCreated: Launching the ad");
        super.onViewCreated(view, savedInstanceState);
        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Log.e(TAG, "onViewCreated: Loaded the ad, I Think");
    }
}
