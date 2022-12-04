package com.example.jokeapi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainDocumentationFragment extends Fragment {

    View view;
    Button mainMenuButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_documentation, container, false);
        mainMenuButton = view.findViewById(R.id.mainMenuButton);

        String text = MainMenuFragmentArgs.fromBundle(getArguments()).getToMenu();
        Toast t = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        t.show();

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.cancel();
                MainDocumentationFragmentDirections.ToMainMenu action = MainDocumentationFragmentDirections.toMainMenu();
                Navigation.findNavController(view).navigate(action);

            }
        });
        return view;
    }
}