package com.example.jokeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.jokeapi.databinding.ActivityJokeBinding;

public class JokeActivity extends AppCompatActivity {
    TextView jokeText;
    String categoryName;

    int index = 0;
    Joke currentJoke;
    ArrayList<Joke> jokes = new ArrayList<>();

    ActivityJokeBinding binding;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://v2.jokeapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Methods methods = retrofit.create(Methods.class);
    Call<Joke> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        categoryName = getIntent().getStringExtra("CATEGORY");
        jokeText = findViewById(R.id.jokeText);

        tellJoke();
        //currentJoke = jokes.get(index);

        binding = ActivityJokeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.setCategory(categoryName);

    }
    public void tellJoke(){
        call = methods.getJoke(categoryName);
        call.enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                if(!response.isSuccessful()){
                    jokeText.setText("Code: " + response.code());
                    return;
                }

                Joke joke = response.body();
                jokes.add(joke);
                binding.setJoke(jokes.get(index));
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                jokeText.setText(t.getMessage());
            }
        });

    }

    public void nextButtonClick(View view) {
        if(index == jokes.size()-1) {
            index++;
            tellJoke();
        }else{
            index++;
            binding.setJoke(jokes.get(index));
        }
    }

    public void prevButtonClick(View view) {
        if(index > 0){
            index--;
            binding.setJoke(jokes.get(index));
        }
    }
}