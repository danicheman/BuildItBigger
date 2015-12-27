package com.example;

//import com.werkncode.API;
import com.werkncode.ChuckNorris;

public class JokeSmith {
    public Joke getJoke() {
        return new Joke(ChuckNorris.getRandomJoke());
    }
}
