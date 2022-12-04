package com.example.jokeapi;

public class CategoryItemViewModel {

    int id;
    String categoryName;
    int image;

    public CategoryItemViewModel(int id, String categoryName, int image) {
        this.id = id;
        this.categoryName = categoryName;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getImage() {
        return image;
    }

}
