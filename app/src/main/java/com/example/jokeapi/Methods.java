package com.example.jokeapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Methods {

    @GET("joke/{category}")
    Call<Joke> getJoke(@Path("category") String category);

}
