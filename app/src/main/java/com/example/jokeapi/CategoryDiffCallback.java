package com.example.jokeapi;

import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;

public class CategoryDiffCallback extends DiffUtil.Callback{
    private final ArrayList<CategoryItemViewModel> oldList;
    private final ArrayList<CategoryItemViewModel> newList;

    public CategoryDiffCallback(ArrayList<CategoryItemViewModel> oldList, ArrayList<CategoryItemViewModel> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getCategoryName()
                .equals(newList.get(newItemPosition).getCategoryName());
    }


}
