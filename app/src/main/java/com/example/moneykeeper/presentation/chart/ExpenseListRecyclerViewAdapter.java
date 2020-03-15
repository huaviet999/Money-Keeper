package com.example.moneykeeper.presentation.chart;

import android.content.Context;
import android.graphics.Color;
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

/**
 * Created by Viet Hua on 3/14/2020
 */
public class ExpenseListRecyclerViewAdapter extends BaseRecyclerViewAdapter<ModelTest1, ExpenseListRecyclerViewAdapter.ViewHolder> {
    public ExpenseListRecyclerViewAdapter(Context context, ItemClickListener<ModelTest1> listener) {
        super(context);
        setListener(listener);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_expense, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelTest1 data = mListData.get(position);
        holder.renderUI(data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tvType, tvPercent, tvNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            tvType = itemView.findViewById(R.id.txt_expense_type);
            tvPercent = itemView.findViewById(R.id.txt_percent);
            tvNumber = itemView.findViewById(R.id.txt_expense_number);
        }

        public void renderUI(ModelTest1 data) {
            tvType.setText(data.getExpenseType().toString());
            tvPercent.setText(data.getPercent());
            tvNumber.setText(data.getMoney());
        }

        @Override
        public void onClick(View view) {
            if (mListener == null) return;
            mListener.onClickListener(getAdapterPosition(), null);
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}
