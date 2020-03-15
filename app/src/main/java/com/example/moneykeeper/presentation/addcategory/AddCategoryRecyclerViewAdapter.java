package com.example.moneykeeper.presentation.addcategory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.domain.model.ModelTest1;
import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseRecyclerViewAdapter;
import com.example.moneykeeper.presentation.base.ItemClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddCategoryRecyclerViewAdapter extends BaseRecyclerViewAdapter<ModelTest1, AddCategoryRecyclerViewAdapter.ViewHolder> {
    public AddCategoryRecyclerViewAdapter(Context context, ItemClickListener<ModelTest1> itemClickListener) {
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
        ModelTest1 account = mListData.get(position);
        holder.renderUI(account);
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

        public void renderUI(ModelTest1 data) {
            tvCategory.setText(data.getExpenseType().toString());
        }

        @Override
        public void onClick(View view) {
            if (mListener == null) return;
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
