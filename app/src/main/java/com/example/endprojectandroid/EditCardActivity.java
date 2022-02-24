package com.example.endprojectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.endprojectandroid.Helper.CardsOverviewViewModel;

public class EditCardActivity extends AppCompatActivity {

//    TODO: Make sure the edited variables will be saved somehow, such that it can be found after refreshing.
    private CardsOverviewViewModel cardsOverviewViewModel;
    private EditText cardAtkInput;
    private EditText cardDefInput;
    private EditText cardLevelInput;
    private EditText cardRaceInput;
    private EditText cardAttributeInput;
    private EditText cardDescriptionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);

        if (getIntent().getExtras() != null) {
            cardsOverviewViewModel = getIntent().getExtras().getParcelable("cardsOverviewViewModel");
        }

        TextView cardName = findViewById(R.id.card_name_value);
        cardName.setText(cardsOverviewViewModel.getCardName());

        TextView cardType = findViewById(R.id.card_type_value);
        cardType.setText(cardsOverviewViewModel.getCardType());

        cardAtkInput = findViewById(R.id.card_atk_input);
        cardDefInput = findViewById(R.id.card_def_input);
        cardLevelInput = findViewById(R.id.card_level_input);
        cardRaceInput = findViewById(R.id.card_race_input);
        cardAttributeInput = findViewById(R.id.card_attribute_input);
        cardDescriptionInput = findViewById(R.id.card_description_input);
        setCardVariablesInputDefaultValue();

        Button saveEditButton = findViewById(R.id.save_edit_button);
        saveEditButton.setOnClickListener(e -> {
            editVariables();
            startCardInformationActivity();
        });

        Button cancelEditButton = findViewById(R.id.cancel_edit_button);
        cancelEditButton.setOnClickListener(e -> startCardInformationActivity());
    }

    private void setCardVariablesInputDefaultValue() {
        checkIfValueIsNA(cardAtkInput, String.valueOf(cardsOverviewViewModel.getCardAtk()));
        checkIfValueIsNA(cardDefInput, String.valueOf(cardsOverviewViewModel.getCardDef()));
        checkIfValueIsNA(cardLevelInput, String.valueOf(cardsOverviewViewModel.getCardLevel()));
        checkIfValueIsNA(cardRaceInput, cardsOverviewViewModel.getCardRace());
        checkIfValueIsNA(cardAttributeInput, cardsOverviewViewModel.getCardAttribute());
        checkIfValueIsNA(cardDescriptionInput, cardsOverviewViewModel.getCardDescription());
    }

    private void checkIfValueIsNA(EditText editText, String value) {
        if (value != null && !value.equals("-1")) {
            editText.setText(value);
        }
    }

    private void editVariables() {
        if (cardsOverviewViewModel == null) return;

        cardsOverviewViewModel.setCardAtk(editTextToInteger(cardAtkInput.getText().toString()));
        cardsOverviewViewModel.setCardDef(editTextToInteger(cardDefInput.getText().toString()));
        cardsOverviewViewModel.setCardLevel(editTextToInteger(cardLevelInput.getText().toString()));
        cardsOverviewViewModel.setCardRace(checkEditTextIsEmpty(cardRaceInput.getText().toString()));
        cardsOverviewViewModel.setCardAttribute(checkEditTextIsEmpty(cardAttributeInput.getText().toString()));
        cardsOverviewViewModel.setCardDescription(checkEditTextIsEmpty(cardDescriptionInput.getText().toString()));
    }

    private int editTextToInteger(String inputText) {
        if (inputText.matches("^[0-9]+$")) {
            return Integer.parseInt(inputText);
        }
        return -1;
    }

    private String checkEditTextIsEmpty(String inputText) {
        if (!inputText.equals("")) {
            return inputText;
        }
        return null;
    }

    private void startCardInformationActivity() {
        Intent cardInformationActivity = new Intent(this, CardInformationActivity.class);
        cardInformationActivity.putExtra("cardsOverviewViewModel", cardsOverviewViewModel);
        startActivity(cardInformationActivity);
        finish();
    }
}