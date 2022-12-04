package com.example.jokeapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<CategoryItemViewModel> oldCategoryModels;

    public CategoryRecyclerViewAdapter(Context context, ArrayList<CategoryItemViewModel> oldCategoryModels,
                                       RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.oldCategoryModels = oldCategoryModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.category_recycler_view,parent,false);
            return new ItemViewHolder(view,recyclerViewInterface);
        } else if (viewType == TYPE_HEADER) {
            View view = inflater.inflate(R.layout.header,parent,false);
            return new HeaderViewHolder(view);
        }
        throw new IllegalArgumentException();

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderViewHolder) {
            HeaderViewHolder header = (HeaderViewHolder) holder;
            header.iconText.setText(R.string.headerIconText);
            header.categoryText.setText(R.string.headerCategoryText);
        } else if(holder instanceof ItemViewHolder) {
            ItemViewHolder item = (ItemViewHolder) holder;
            item.imageView.setImageResource(oldCategoryModels.get(position).getImage());
            item.textView.setText(oldCategoryModels.get(position).getCategoryName());
        }

    }

    @Override
    public int getItemCount() {
        return oldCategoryModels.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }


    public void setData(ArrayList<CategoryItemViewModel> newCategoryModels){
        CategoryDiffCallback categoryDiffCallback = new CategoryDiffCallback(oldCategoryModels,newCategoryModels);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(categoryDiffCallback);
        oldCategoryModels = newCategoryModels;
        diffResult.dispatchUpdatesTo(this);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ItemViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.categoryIcon);
            textView = itemView.findViewById(R.id.categoryName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
    public static class HeaderViewHolder extends RecyclerView.ViewHolder{
        TextView iconText, categoryText;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iconText = itemView.findViewById(R.id.iconHeaderText);
            this.categoryText = itemView.findViewById(R.id.categoryHeaderText);
        }
    }

}
