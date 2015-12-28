package com.example.nick.builditbigger;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nick.myapplication.backend.myApi.model.Joke;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.GET;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DisplayJokeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DisplayJokeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayJokeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String API_BASE_URL = "http://localhost:8080/_ah/myApi/v1/";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DisplayJokeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DisplayJokeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayJokeFragment newInstance(String param1, String param2) {
        DisplayJokeFragment fragment = new DisplayJokeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_joke, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class GCELoadJoke extends AsyncTask<Void,Void,Void>  {

        private Retrofit retrofit;
        private Gson gson;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        @Override
        protected Void doInBackground(Void... params) {

            JokeApiEndpointInterface apiService = retrofit.create(JokeApiEndpointInterface.class);

            try {
                Call<Joke> call = apiService.getJoke();
                call.execute();
            } catch (IOException e) {
                //handle it!
            }

            return null;
        }


    }
    public interface JokeApiEndpointInterface {
        @GET("/joke")
        Call<Joke> getJoke();
    }
}
