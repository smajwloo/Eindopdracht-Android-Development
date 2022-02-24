package com.example.endprojectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.endprojectandroid.Helper.CardsOverviewViewModel;
import com.example.endprojectandroid.Helper.CheckVariableAvailability;
import com.example.endprojectandroid.Helper.NetworkImageViewLoader;

public class CardInformationActivity extends AppCompatActivity implements CheckVariableAvailability {

//    TODO: Refactor to fragment, in order to provide a landscape view
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

            Button editCardButton = findViewById(R.id.edit_card_button);
            editCardButton.setOnClickListener(e -> onEditButtonClick(cardsOverviewViewModel));
        }
    }

    @Override
    public void setVariableTextInteger(TextView textView, String placeholder, int variableValue) {
        if (variableValue == -1) {
            textView.setText(getString(R.string.na_placeholder, placeholder));
        } else {
            textView.setText(getString(R.string.variable_placeholder_integer, placeholder, variableValue));
        }
    }

    @Override
    public void setVariableTextString(TextView textView, String placeholder, String variableValue) {
        if (variableValue == null) {
            textView.setText(getString(R.string.na_placeholder, placeholder));
        } else {
            textView.setText(getString(R.string.variable_placeholder_string, placeholder, variableValue));
        }
    }

    private void setCardInformation(CardsOverviewViewModel cardsOverviewViewModel) {
        cardName.setText(cardsOverviewViewModel.getCardName());
        cardType.setText(getString(R.string.card_type_placeholder, cardsOverviewViewModel.getCardType()));
        cardDescription.setText(cardsOverviewViewModel.getCardDescription());

        setVariableTextInteger(cardAtk, getString(R.string.card_atk_placeholder), cardsOverviewViewModel.getCardAtk());
        setVariableTextInteger(cardDef, getString(R.string.card_def_placeholder), cardsOverviewViewModel.getCardDef());
        setVariableTextInteger(cardLevel, getString(R.string.card_level_placeholder), cardsOverviewViewModel.getCardLevel());

        setVariableTextString(cardRace, getString(R.string.card_race_placeholder), cardsOverviewViewModel.getCardRace());
        setVariableTextString(cardAttribute, getString(R.string.card_attribute_placeholder), cardsOverviewViewModel.getCardAttribute());

        ImageLoader imageLoader = NetworkImageViewLoader.getInstance(this).getImageLoader();
        networkImageView.setImageUrl(cardsOverviewViewModel.getCardImgLink(), imageLoader);
    }

    private void onEditButtonClick(CardsOverviewViewModel cardsOverviewViewModel) {
        Intent editCardActivity = new Intent(this, EditCardActivity.class);
        editCardActivity.putExtra("cardsOverviewViewModel", cardsOverviewViewModel);
        startActivity(editCardActivity);
        finish();
    }
}