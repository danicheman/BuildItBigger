package com.example;

import java.io.Serializable;

/**
 * Created by NICK on 12/26/2015.
 */
public class Joke implements Serializable{
    String joke;

    public void set(String joke) {
        this.joke = joke;
    }


    public String get() {
        return this.joke;
    }

    @Override
    public String toString() {
        return this.joke;
    }

    public String getData() {
        return this.joke;
    }
}
