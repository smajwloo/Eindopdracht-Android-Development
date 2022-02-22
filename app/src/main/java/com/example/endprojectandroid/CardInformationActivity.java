package com.example.endprojectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.endprojectandroid.Helper.CardsOverviewViewModel;
import com.example.endprojectandroid.Helper.NetworkImageViewLoader;

public class CardInformationActivity extends AppCompatActivity {

    private TextView cardName;
    private TextView cardType;
    private TextView cardDescription;
    private TextView cardAtk;
    private TextView cardDef;
    private TextView cardLevel;
    private TextView cardRace;
    private TextView cardAttribute;
    private NetworkImageView networkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_information);

        cardName = findViewById(R.id.card_name);
        cardType = findViewById(R.id.card_type);
        cardDescription = findViewById(R.id.card_description);
        cardAtk = findViewById(R.id.card_atk);
        cardDef = findViewById(R.id.card_def);
        cardLevel = findViewById(R.id.card_level);
        cardRace = findViewById(R.id.card_race);
        cardAttribute = findViewById(R.id.card_attribute);
        networkImageView = findViewById(R.id.card_image);

        if (getIntent().getExtras() != null) {
            CardsOverviewViewModel cardsOverviewViewModel = getIntent().getExtras().getParcelable("cardsOverviewViewModel");
            setCardInformation(cardsOverviewViewModel);
        }
    }

    private void setCardInformation(CardsOverviewViewModel cardsOverviewViewModel) {
        cardName.setText(cardsOverviewViewModel.getCardName());
        cardType.setText(getString(R.string.card_type_placeholder, cardsOverviewViewModel.getCardType()));
        cardDescription.setText(cardsOverviewViewModel.getCardDescription());

        if (cardsOverviewViewModel.getCardAtk() == -1) {
            cardAtk.setText(getString(R.string.card_atk_na_placeholder));
        } else {
            cardAtk.setText(getString(R.string.card_atk_placeholder, cardsOverviewViewModel.getCardAtk()));
        }

        if (cardsOverviewViewModel.getCardDef() == -1) {
            cardDef.setText(getString(R.string.card_def_na_placeholder));
        } else {
            cardDef.setText(getString(R.string.card_def_placeholder, cardsOverviewViewModel.getCardDef()));
        }

        if (cardsOverviewViewModel.getCardLevel() == -1) {
            cardLevel.setText(getString(R.string.card_level_na_placeholder));
        } else {
            cardLevel.setText(getString(R.string.card_level_placeholder, cardsOverviewViewModel.getCardLevel()));
        }

        cardRace.setText(getString(R.string.card_race_placeholder, cardsOverviewViewModel.getCardRace()));

        if (cardsOverviewViewModel.getCardAttribute() == null) {
            cardAttribute.setText(getString(R.string.card_attribute_na_placeholder));
        } else {
            cardAttribute.setText(getString(R.string.card_attribute_placeholder, cardsOverviewViewModel.getCardAttribute()));
        }

        ImageLoader imageLoader = NetworkImageViewLoader.getInstance(this).getImageLoader();
        networkImageView.setImageUrl(cardsOverviewViewModel.getCardImgLink(), imageLoader);
    }
}