package com.example.endprojectandroid.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.endprojectandroid.Helper.CardsOverviewViewModel;
import com.example.endprojectandroid.R;

public class CardInformationFragment extends Fragment {

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
        if (cardsOverviewViewModel.getCardAtk() == -1) {
            cardAtk.setText(getString(R.string.cardinformationfragment_atk_na));
        } else {
            cardAtk.setText(getString(R.string.cardinformationfragment_atk, cardsOverviewViewModel.getCardAtk()));
        }

        if (cardsOverviewViewModel.getCardDef() == -1) {
            cardDef.setText(getString(R.string.cardinformationfragment_def_na));
        } else {
            cardDef.setText(getString(R.string.cardinformationfragment_def, cardsOverviewViewModel.getCardDef()));
        }

        if (cardsOverviewViewModel.getCardLevel() == -1) {
            cardLevel.setText(getString(R.string.cardinformationfragment_level_na));
        } else {
            cardLevel.setText(getString(R.string.cardinformationfragment_level, cardsOverviewViewModel.getCardLevel()));
        }

        cardRace.setText(getString(R.string.cardinformationfragment_race, cardsOverviewViewModel.getCardRace()));

        if (cardsOverviewViewModel.getCardAttribute() == null) {
            cardAttribute.setText(getString(R.string.cardinformationfragment_attribute_na));
        } else {
            cardAttribute.setText(getString(R.string.cardinformationfragment_attribute, cardsOverviewViewModel.getCardAttribute()));
        }
    }
}