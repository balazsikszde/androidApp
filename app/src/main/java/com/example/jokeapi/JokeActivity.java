package com.example.jokeapi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JokeActivity extends AppCompatActivity {
    TextView jokeText;
    String categoryName;
    TextView categoryText;

    int currentJoke = 0;
    ArrayList<String> jokes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        categoryName = getIntent().getStringExtra("CATEGORY");
        jokeText = findViewById(R.id.jokeText);
        categoryText = findViewById(R.id.catergoryText);
        categoryText.setText(categoryName);

        tellJoke();
    }
    public void tellJoke(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.URL)+categoryName;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject reader = new JSONObject(response);
                            String type = reader.getString("type");
                            String joke ="";
                            if(type.equals("twopart")){
                                joke = reader.getString("setup") + "\n" + reader.getString("delivery");

                            }
                            if(type.equals("single")){
                                joke = reader.getString("joke");
                            }
                            jokes.add(joke);
                            currentJoke++;
                            jokeText.setText(joke);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        jokeText.setText("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void nextButtonClick(View view) {
        if(currentJoke==jokes.size()) {
            tellJoke();
        }else{
            currentJoke++;
            jokeText.setText(jokes.get(currentJoke-1));
        }
    }

    public void prevButtonClick(View view) {
        if(currentJoke>1){
            currentJoke--;
            jokeText.setText(jokes.get(currentJoke-1));
        }
    }
}