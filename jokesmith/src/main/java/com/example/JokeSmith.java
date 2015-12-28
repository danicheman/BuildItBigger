package com.example;

//import com.werkncode.API;
import com.werkncode.ChuckNorris;

public class JokeSmith {
    public Joke getJoke() {
        Joke j = new Joke();
        j.set(ChuckNorris.getRandomJoke());
        return j;
    }
}
