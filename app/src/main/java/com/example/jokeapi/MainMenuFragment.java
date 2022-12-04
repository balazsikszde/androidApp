package com.example.jokeapi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class MainMenuFragment extends Fragment {

    View view;
    Button  documentationButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        documentationButton = view.findViewById(R.id.documentationButton);
        String text = MainDocumentationFragmentArgs.fromBundle(getArguments()).getToDoc();
        Toast t = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        t.show();
        documentationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.cancel();
                MainMenuFragmentDirections.ToMainDocumentation action = MainMenuFragmentDirections.toMainDocumentation();
                Navigation.findNavController(view).navigate(action);
            }
        });
        return view;
    }



}