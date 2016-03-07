package com.example.nick.jokedisplaylibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class DisplayJokeFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static final String ARG_KEY = "JOKE";

    private JokeButtonListener mListener;

    public DisplayJokeFragment() {
        // Required empty public constructor
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

        Button button = (Button) rootView.findViewById(R.id.refresh_joke);
        button.setOnClickListener(this);

        if (getContext() instanceof DisplayJokeActivity) {

            //grab the view's joke displaying text view
            TextView mJokeText = (TextView) rootView.findViewById(R.id.joke_text);

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
