package com.example.moneykeeper.presentation.home;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.domain.model.Account;
import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseRecyclerViewAdapter;
import com.example.moneykeeper.presentation.base.ItemClickListener;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Viet Hua on 3/12/2020
 */
public class SummaryRecyclerViewAdapter extends BaseRecyclerViewAdapter<Account, SummaryRecyclerViewAdapter.ViewHolder> {
    public SummaryRecyclerViewAdapter(Context context, ItemClickListener<Account> listener) {
        super(context);
        setListener(listener);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_summary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Account data = mListData.get(position);
        holder.renderUI(data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        AnimatedPieView animatedPieView;
        TextView tvIncome , tvExpense , tvTotal;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            animatedPieView = itemView.findViewById(R.id.chart_pie_summary);
            tvIncome = itemView.findViewById(R.id.txt_income_number);
            tvExpense = itemView.findViewById(R.id.txt_amount);
            tvTotal = itemView.findViewById(R.id.txt_total_number);

        }
        public void renderUI(Account data){
            AnimatedPieViewConfig config = new AnimatedPieViewConfig();
            config.strokeMode(false);
            config.strokeWidth(150);
            config.startAngle(-90)// Starting angle offset
                    .addData(new SimplePieInfo(data.getIncome(), Color.parseColor("#00ff00"), "Income"))
                    .addData(new SimplePieInfo(data.getExpense(), Color.parseColor("#ff0000"), "Expense"))
                    .duration(1000);// draw pie animation duration
            animatedPieView.applyConfig(config);
            animatedPieView.start();
            tvIncome.setText(String.valueOf(data.getIncome()));
            tvExpense.setText("-" + String.valueOf(data.getExpense()));
            float total = data.getIncome() - data.getExpense();
            tvTotal.setText(String.valueOf(total));
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
