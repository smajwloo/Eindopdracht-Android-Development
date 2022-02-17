package com.example.endprojectandroid.Fragments;

import androidx.annotation.NonNull;

public class CardsOverviewViewModel {

    private String cardName;
    private String cardType;
    private String cardImgLink;

    public CardsOverviewViewModel(@NonNull final String cardName, @NonNull final String cardType,
                                  @NonNull final String cardImgLink) {
        setCardName(cardName);
        setCardType(cardType);
        setCardImgLink(cardImgLink);
    }

    @NonNull
    public String getCardName() {
        return cardName;
    }

    public void setCardName(@NonNull final String cardName) {
        this.cardName = cardName;
    }

    @NonNull
    public String getCardType() {
        return cardType;
    }

    public void setCardType(@NonNull final String cardType) {
        this.cardType = cardType;
    }

    @NonNull
    public String getCardImgLink() {
        return cardImgLink;
    }

    public void setCardImgLink(@NonNull final String cardImgLink) {
        this.cardImgLink = cardImgLink;
    }
}
