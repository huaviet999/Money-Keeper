package com.example.moneykeeper.presentation.category;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.domain.model.Category;
import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseRecyclerViewAdapter;
import com.example.moneykeeper.presentation.base.ItemClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryRecyclerViewAdapter extends BaseRecyclerViewAdapter<Category, CategoryRecyclerViewAdapter.ViewHolder> {
    private  int categoryIndex = -1; //Default no category choose;
    public CategoryRecyclerViewAdapter(Context context, ItemClickListener<Category> itemClickListener) {
        super(context);
        setListener(itemClickListener);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = mListData.get(position);
        holder.renderUI(category);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView imageCategory;
        TextView tvCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            imageCategory = itemView.findViewById(R.id.img_category);
            tvCategory = itemView.findViewById(R.id.txt_category);
        }

        public void renderUI(Category data) {
            tvCategory.setText(data.getName());
            if(categoryIndex == getAdapterPosition()){
                imageCategory.setImageResource(context.getResources().getIdentifier(data.getcImage(),"drawable",context.getPackageName()));
            } else{
                imageCategory.setImageResource(context.getResources().getIdentifier(data.getnImage(),"drawable",context.getPackageName()));
            }

        }

        @Override
        public void onClick(View view) {
            if (mListener == null) return;
            categoryIndex = getAdapterPosition();
            notifyDataSetChanged();


            mListener.onClickListener(getAdapterPosition(), null);
        }

        @Override
        public boolean onLongClick(View view) {
            if (mListener != null) {
                mListener.onLongClickListener(getAdapterPosition(), null);
            }
            return false;
        }
    }
}
