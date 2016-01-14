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
 * {@link DisplayJokeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DisplayJokeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayJokeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private TextView mJokeText;
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
        View rootView = inflater.inflate(R.layout.fragment_display_joke, container, false);

        //grab the view's joke displaying text view
        mJokeText = (TextView)rootView.findViewById(R.id.joke_text);
        return rootView;
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

    private void displayJoke(Joke joke) {

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

    class GCELoadJoke extends AsyncTask<Void,Void,Joke>  {

        private static final String TAG = "GCELoadJoke";

        private static final String API_BASE_URL = "http://localhost:8080/_ah/myApi/v1/";
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
        protected Joke doInBackground(Void... params) {

            JokeApiEndpointInterface apiService = retrofit.create(JokeApiEndpointInterface.class);

            try {
                Call<Joke> call = apiService.getJoke();
                Response<Joke> response = call.execute();

                if (response.isSuccess()) {
                    Log.d(TAG, "doInBackground - success: " + response.toString());
                    return response.body();
                }

            } catch (IOException e) {
                //handle it!
                Log.e(TAG, "doInBackground: IO Exception occured: "+ e.getMessage() );
            }

            return null;
        }

        @Override
        protected void onPostExecute(Joke joke) {
            super.onPostExecute(joke);
            mJokeText.setText(joke.getData());
        }
    }
    public interface JokeApiEndpointInterface {
        @GET("/joke")
        Call<Joke> getJoke();
    }
}
