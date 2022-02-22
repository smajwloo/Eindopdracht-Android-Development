package com.example.endprojectandroid.Helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endprojectandroid.OnClickListener;
import com.example.endprojectandroid.R;

import java.util.List;

public class CardsOverviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<CardsOverviewViewModel> cards;
    private final OnClickListener onClickListener;

    public CardsOverviewAdapter(List<CardsOverviewViewModel> cards, OnClickListener onClickListener) {
        this.cards = cards;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new CardsOverviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CardsOverviewViewHolder) holder).bindData(cards.get(position));
        ((CardsOverviewViewHolder) holder).itemView.setOnClickListener(
                view -> onClickListener.onItemSelected(cards.get(position)));
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.cards_overview_item;
    }
}
