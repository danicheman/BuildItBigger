package com.example.nick.builditbigger;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Joke;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.GET;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DisplayJokeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayJokeFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static final String ARG_KEY = "JOKE";
    private static final String TAG = "DisplayJokeFragment";
    // TODO: Rename and change types of parameters
    private String joke;


    private JokeButtonListener mListener;
    private TextView mJokeText;
    public DisplayJokeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param joke A string containing a joke.
     * @return A new instance of fragment DisplayJokeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayJokeFragment newInstance(String joke) {
        DisplayJokeFragment fragment = new DisplayJokeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_KEY, joke);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onJokeButtonPressed();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_display_joke, container, false);
        //grab the view's joke displaying text view
        mJokeText = (TextView) rootView.findViewById(R.id.joke_text);


        Bundle args = getArguments();
        if (args != null && args.containsKey(ARG_KEY)) {
            joke = args.getString(ARG_KEY);

            mJokeText.setText(joke);
        } else {
            Log.d(TAG, "onCreateView: No joke in args.");
        }

        if (getContext() instanceof DisplayJokeActivity) {
            Log.d(TAG, "onCreateView: Getting joke from activity.");
            mJokeText.setText(((DisplayJokeActivity) getContext()).joke);
        }
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof JokeButtonListener) {
            mListener = (JokeButtonListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement JokeButtonListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface JokeButtonListener {
        void onJokeButtonPressed();
    }



}
