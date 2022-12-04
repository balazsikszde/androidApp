package com.example.jokeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements RecyclerViewInterface {

    ArrayList<CategoryItemViewModel> categoryModels = new ArrayList<>();
    int[] categoryImages = {R.drawable.programming,R.drawable.dark,R.drawable.pun,R.drawable.spooky,R.drawable.christmas,R.drawable.misc};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        RecyclerView recyclerView = findViewById(R.id.categoryList);

        setUpCategoryModels();
        CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(
                this,
                categoryModels,
                this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CategoryItemViewModel cat1 = new CategoryItemViewModel(
                100,
                "added",
                R.drawable.ic_launcher_background);
        categoryModels.add(cat1);
        adapter.setData(categoryModels);


    }
    private void setUpCategoryModels(){
        String[] categoryNames = getResources().getStringArray(R.array.category_list);

        for (int i = 0; i<categoryNames.length;i++){
            categoryModels.add(new CategoryItemViewModel(i,categoryNames[i],categoryImages[i]));
        }
    }

    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(this,JokeActivity.class);
        intent.putExtra("CATEGORY",categoryModels.get(pos).getCategoryName());
        startActivity(intent);
    }
}