package com.example.moneykeeper.presentation.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.domain.model.Record;
import com.example.domain.model.Transaction;
import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseRecyclerViewAdapter;
import com.example.moneykeeper.presentation.base.Constants;
import com.example.moneykeeper.presentation.base.ItemClickListener;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import utils.MathUtils;
import utils.TimeUtils;

/**
 * Created by Viet Hua on 3/12/2020
 */
public class SummaryRecyclerViewAdapter extends BaseRecyclerViewAdapter<Record, SummaryRecyclerViewAdapter.ViewHolder> {
    public SummaryRecyclerViewAdapter(Context context, ItemClickListener<Record> listener) {
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
        Record data = mListData.get(position);
        holder.renderUI(data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        AnimatedPieView animatedPieView;
        TextView tvIncome, tvExpense, tvTotal, tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            animatedPieView = itemView.findViewById(R.id.chart_pie_summary);
            tvIncome = itemView.findViewById(R.id.txt_income_number);
            tvExpense = itemView.findViewById(R.id.txt_amount);
            tvTotal = itemView.findViewById(R.id.txt_total_number);
            tvDate = itemView.findViewById(R.id.txt_date);

        }

        public void renderUI(Record data) {
            AnimatedPieViewConfig config = new AnimatedPieViewConfig();
            config.strokeMode(false);
            config.strokeWidth(150);
            config.startAngle(-90)// Starting angle offset
                    .addData(new SimplePieInfo(data.getIncome(), ResourcesCompat.getColor(context.getResources(), R.color.income_button_color, null), "Income"))
                    .addData(new SimplePieInfo(data.getExpense(), ResourcesCompat.getColor(context.getResources(), R.color.expense_button_color, null), "Expense"))
                    .duration(1000);// draw pie animation duration
            animatedPieView.applyConfig(config);
            animatedPieView.start();
            tvIncome.setText(MathUtils.getFormatNumberFromLong(data.getIncome()));
            tvExpense.setText("-" + MathUtils.getFormatNumberFromLong(data.getExpense()));

            setSummaryTitle(data);
            setTotalTextColor(data);


        }

        private void setSummaryTitle(Record data) {
            if (data.getDay() == TimeUtils.getCurrentDate() && data.getMonth() == TimeUtils.getCurrentMonth() && data.getYear() == TimeUtils.getCurrentYear()) {
                tvDate.setText("Hôm nay");
            } else {
                tvDate.setText("Tháng " + data.getMonth() + " Năm " + data.getYear());
            }
        }
        private void setTotalTextColor(Record data){
            tvTotal.setText(MathUtils.getFormatNumberFromLong(data.getTotal()));
            tvTotal.setTextColor(data.getTotal() >= 0 ?
                    ResourcesCompat.getColor(context.getResources(), R.color.income_button_color, null) :
                    ResourcesCompat.getColor(context.getResources(), R.color.expense_button_color, null));
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
