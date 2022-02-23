package com.example.endprojectandroid.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.endprojectandroid.Helper.CardsOverviewViewModel;
import com.example.endprojectandroid.Helper.CheckVariableAvailability;
import com.example.endprojectandroid.R;

public class CardInformationFragment extends Fragment implements CheckVariableAvailability {

    private TextView cardAtk;
    private TextView cardDef;
    private TextView cardLevel;
    private TextView cardRace;
    private TextView cardAttribute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_information, container, false);

        cardAtk = view.findViewById(R.id.card_atk);
        cardDef = view.findViewById(R.id.card_def);
        cardLevel = view.findViewById(R.id.card_level);
        cardRace = view.findViewById(R.id.card_race);
        cardAttribute = view.findViewById(R.id.card_attribute);

        if (getArguments() != null) {
            CardsOverviewViewModel cardsOverviewViewModel = getArguments().getParcelable("cardsOverviewViewModel");
            setCardInformation(cardsOverviewViewModel);
        }

        return view;
    }

    private void setCardInformation(CardsOverviewViewModel cardsOverviewViewModel) {
        setVariableTextInteger(cardAtk, getString(R.string.card_atk_placeholder), cardsOverviewViewModel.getCardAtk());
        setVariableTextInteger(cardDef, getString(R.string.card_def_placeholder), cardsOverviewViewModel.getCardDef());
        setVariableTextInteger(cardLevel, getString(R.string.card_level_placeholder), cardsOverviewViewModel.getCardLevel());

        setVariableTextString(cardRace, getString(R.string.card_race_placeholder), cardsOverviewViewModel.getCardRace());
        setVariableTextString(cardAttribute, getString(R.string.card_attribute_placeholder), cardsOverviewViewModel.getCardAttribute());
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
}