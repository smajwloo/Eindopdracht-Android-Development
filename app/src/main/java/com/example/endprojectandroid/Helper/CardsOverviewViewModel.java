package com.example.endprojectandroid.Helper;

import android.os.Parcel;
import android.os.Parcelable;

public class CardsOverviewViewModel implements Parcelable {

    private String cardName;
    private String cardType;
    private String cardDescription;
    private int cardAtk;
    private int cardDef;
    private int cardLevel;
    private String cardRace;
    private String cardAttribute;
    private String cardImgLink;

    public CardsOverviewViewModel(final String cardName,
                                  final String cardType,
                                  final String cardDescription,
                                  final int cardAtk,
                                  final int cardDef,
                                  final int cardLevel,
                                  final String cardRace,
                                  final String cardAttribute,
                                  final String cardImgLink) {
        setCardName(cardName);
        setCardType(cardType);
        setCardDescription(cardDescription);
        setCardAtk(cardAtk);
        setCardDef(cardDef);
        setCardLevel(cardLevel);
        setCardRace(cardRace);
        setCardAttribute(cardAttribute);
        setCardImgLink(cardImgLink);
    }

    protected CardsOverviewViewModel(Parcel in) {
        cardName = in.readString();
        cardType = in.readString();
        cardDescription = in.readString();
        cardAtk = in.readInt();
        cardDef = in.readInt();
        cardLevel = in.readInt();
        cardRace = in.readString();
        cardAttribute = in.readString();
        cardImgLink = in.readString();
    }

    public static final Creator<CardsOverviewViewModel> CREATOR = new Creator<CardsOverviewViewModel>() {
        @Override
        public CardsOverviewViewModel createFromParcel(Parcel in) {
            return new CardsOverviewViewModel(in);
        }

        @Override
        public CardsOverviewViewModel[] newArray(int size) {
            return new CardsOverviewViewModel[size];
        }
    };

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public int getCardAtk() {
        return cardAtk;
    }

    public void setCardAtk(int cardAtk) {
        this.cardAtk = cardAtk;
    }

    public int getCardDef() {
        return cardDef;
    }

    public void setCardDef(int cardDef) {
        this.cardDef = cardDef;
    }

    public int getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(int cardLevel) {
        this.cardLevel = cardLevel;
    }

    public String getCardRace() {
        return cardRace;
    }

    public void setCardRace(String cardRace) {
        this.cardRace = cardRace;
    }

    public String getCardAttribute() {
        return cardAttribute;
    }

    public void setCardAttribute(String cardAttribute) {
        this.cardAttribute = cardAttribute;
    }

    public String getCardImgLink() {
        return cardImgLink;
    }

    public void setCardImgLink(String cardImgLink) {
        this.cardImgLink = cardImgLink;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cardName);
        parcel.writeString(cardType);
        parcel.writeString(cardDescription);
        parcel.writeInt(cardAtk);
        parcel.writeInt(cardDef);
        parcel.writeInt(cardLevel);
        parcel.writeString(cardRace);
        parcel.writeString(cardAttribute);
        parcel.writeString(cardImgLink);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
