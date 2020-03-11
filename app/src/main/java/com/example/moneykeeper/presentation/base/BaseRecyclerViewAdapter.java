package com.example.moneykeeper.presentation.base;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Viet Hua on 3/11/2020
 */
public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> mListData;
    protected ItemClickListener<T> mListener;
    protected Context context;
    protected LayoutInflater mLayoutInflater;

    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
        mListData = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setListener(ItemClickListener<T> listener) {
        this.mListener = listener;
    }

    public void setData(List<T> data) {
        if (data == null) {
            return;
        }
        mListData.clear();
        mListData.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(T t) {
        mListData.add(t);
        notifyItemInserted(mListData.size() - 1);
    }

    public void clearData() {
        mListData.clear();
        notifyDataSetChanged();
    }

    public void removeData(T t) {
        int pos = mListData.indexOf(t);
        removeDataByPosition(pos);
    }

    public void removeDataByPosition(int position) {
        if (position < 0) {
            return;
        }
        mListData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mListData.size());
    }

    public T getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }
}
