package com.example.jokeapi;

public class Joke {
    private String category;
    private String type;
    private String joke;
    private String setup;
    private String delivery;

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getJoke() {
        if(type.equals("single")){
            return joke;
        }
        return setup +"\n" + delivery;

    }
}
