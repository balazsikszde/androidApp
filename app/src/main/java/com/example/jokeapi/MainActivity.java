package com.example.jokeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void categoryClicked(View view) {
        Intent intent = new Intent(this,CategoriesActivity.class);
        startActivity(intent);
    }

    public void randomClicked(View view) {
        Intent intent = new Intent(this,JokeActivity.class);
        intent.putExtra("CATEGORY","Any");
        startActivity(intent);
    }
}