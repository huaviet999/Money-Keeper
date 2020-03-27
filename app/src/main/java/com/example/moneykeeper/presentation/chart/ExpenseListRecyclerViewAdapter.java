package com.example.moneykeeper.presentation.chart;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.domain.model.Percent;
import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseRecyclerViewAdapter;
import com.example.moneykeeper.presentation.base.ItemClickListener;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import utils.MathUtils;

/**
 * Created by Viet Hua on 3/14/2020
 */
public class ExpenseListRecyclerViewAdapter extends BaseRecyclerViewAdapter<Percent, ExpenseListRecyclerViewAdapter.ViewHolder> {
    public ExpenseListRecyclerViewAdapter(Context context, ItemClickListener<Percent> listener) {
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
        Percent data = mListData.get(position);
        holder.renderUI(data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tvCategory, tvPercent, tvAmount;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            tvCategory = itemView.findViewById(R.id.txt_category);
            tvPercent = itemView.findViewById(R.id.txt_percent);
            tvAmount = itemView.findViewById(R.id.txt_amount);
        }

        public void renderUI(Percent data) {
            tvCategory.setText(data.getCategory().getName());
            tvPercent.setText(String.format("%.2f %%",data.getPercent()));
            String amount = MathUtils.getFormatNumberFromLong(data.getSum());
            tvAmount.setText(amount);
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
