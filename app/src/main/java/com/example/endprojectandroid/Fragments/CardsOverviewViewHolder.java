package com.example.endprojectandroid.Fragments;

import com.example.endprojectandroid.R;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CardsOverviewViewHolder extends RecyclerView.ViewHolder {

    private final TextView cardNameTextView;

    public CardsOverviewViewHolder(@NonNull View itemView) {
        super(itemView);
        cardNameTextView = (TextView) itemView.findViewById(R.id.card_name);
    }

    public void bindData(CardsOverviewViewModel cardsOverviewViewModel) {
        cardNameTextView.setText(cardsOverviewViewModel.getCardName());
    }
}
