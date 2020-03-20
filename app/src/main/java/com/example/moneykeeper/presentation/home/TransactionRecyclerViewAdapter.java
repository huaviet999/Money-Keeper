package com.example.moneykeeper.presentation.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.domain.model.Category;
import com.example.domain.model.Transaction;
import com.example.moneykeeper.R;
import com.example.moneykeeper.presentation.base.BaseRecyclerViewAdapter;
import com.example.moneykeeper.presentation.base.ItemClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import utils.MathUtils;
import utils.TimeUtils;

/**
 * Created by Viet Hua on 3/11/2020
 */
public class TransactionRecyclerViewAdapter extends BaseRecyclerViewAdapter<Transaction, TransactionRecyclerViewAdapter.ViewHolder> {

    public TransactionRecyclerViewAdapter(Context context, ItemClickListener<Transaction> listener) {
        super(context);
        setListener(listener);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction data = mListData.get(position);
        holder.renderUI(data);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView imgCategory;
        TextView tvCategory, tvAmount, tvAccountName, tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            imgCategory = itemView.findViewById(R.id.img_category);
            tvCategory = itemView.findViewById(R.id.txt_category);
            tvAmount = itemView.findViewById(R.id.txt_amount);
            tvAccountName = itemView.findViewById(R.id.txt_wallet_name);
            tvDate = itemView.findViewById(R.id.txt_date);

        }

        public void renderUI(Transaction data) {
            String date = TimeUtils.convertMillisecondsToShortDateFormat(data.getDate());
            String amount = MathUtils.getFormatNumberFromLong(data.getAmount());
            tvDate.setText(date);
            tvAmount.setText(amount);
            tvCategory.setText(data.getCategory().getName());
            setCategoryImage(data.getCategory());
        }

        public void setCategoryImage(Category category) {
            imgCategory.setImageResource(context.getResources().getIdentifier(category.getCImage(), "drawable", context.getPackageName()));
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
