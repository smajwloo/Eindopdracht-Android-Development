package com.example.endprojectandroid.Fragments;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.endprojectandroid.Helper.NetworkImageViewLoader;
import com.example.endprojectandroid.R;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CardsOverviewViewHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private final TextView cardNameTextView;
    private final TextView cardTypeTextView;

    private final ImageLoader imageLoader;
    private final NetworkImageView networkImageView;

    public CardsOverviewViewHolder(@NonNull View itemView) {
        super(itemView);
        context = this.itemView.getContext();
        imageLoader = NetworkImageViewLoader.getInstance(context).getImageLoader();

        cardNameTextView = itemView.findViewById(R.id.card_name);
        cardTypeTextView = itemView.findViewById(R.id.card_type);
        networkImageView = itemView.findViewById(R.id.card_image);

    }

    public void bindData(CardsOverviewViewModel cardsOverviewViewModel) {
        cardNameTextView.setText(context.getString(R.string.cardsoverview_card_name,
                cardsOverviewViewModel.getCardName()));

        cardTypeTextView.setText(context.getString(R.string.cardsoverview_card_type,
                cardsOverviewViewModel.getCardType()));

        imageLoader.get(cardsOverviewViewModel.getCardImgLink(), ImageLoader.getImageListener(networkImageView,
                R.drawable.card_placeholder, R.drawable.card_undefined));
        networkImageView.setImageUrl(cardsOverviewViewModel.getCardImgLink(), imageLoader);
    }
}
