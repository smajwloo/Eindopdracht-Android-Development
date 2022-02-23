package com.example.endprojectandroid.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.endprojectandroid.ErrorActivity;
import com.example.endprojectandroid.Helper.CardsOverviewAdapter;
import com.example.endprojectandroid.Helper.CardsOverviewViewModel;
import com.example.endprojectandroid.MainActivity;
import com.example.endprojectandroid.Helper.OnClickListener;
import com.example.endprojectandroid.R;
import com.example.endprojectandroid.SettingsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CardsOverviewFragment extends Fragment {

    private static final String API_URL = "https://db.ygoprodeck.com/api/v7/cardinfo.php?";

    private List<CardsOverviewViewModel> cards;
    private RecyclerView cardsOverviewRecyclerView;
    private OnClickListener onClickListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards_overview, container, false);
        cardsOverviewRecyclerView = view.findViewById(R.id.yugioh_cards_recycler_view);
        onClickListener = (MainActivity) getActivity();

        String apiUrlWithFilters = setupApiUrlFilters();
        receiveCardsFromAPI(apiUrlWithFilters);
        return view;
    }

    private String setupApiUrlFilters() {
        if (getContext() == null) return "";

//        Receive SharedPreferences of card variables
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String cardName = sharedPreferences.getString("enter_card_name", "");
        Set<String> cardTypes = sharedPreferences.getStringSet("select_card_type",  Collections.emptySet());

//        Apply filters before sending API request
        String filteredApiUrl = API_URL;
        filteredApiUrl = applyFilter("name", cardName, filteredApiUrl);

        StringBuilder combinedCardTypes = new StringBuilder();
        int index = 1;
        for (String cardType: cardTypes) {
            combinedCardTypes.append(cardType);

            if (index != cardTypes.size()) combinedCardTypes.append(",");
            index++;
        }
        filteredApiUrl = applyFilter("type", combinedCardTypes.toString(), filteredApiUrl);

        return filteredApiUrl;
    }

    private String applyFilter(String filter, String filterValue, String filteredApiUrl) {
        if (!filterValue.equals("")) {
            if (!filteredApiUrl.endsWith("?")) {
                filteredApiUrl = filteredApiUrl + "&";
            }
            filteredApiUrl = filteredApiUrl + filter + "=" + filterValue;
        }
        return filteredApiUrl;
    }

    private void receiveCardsFromAPI(String apiUrlWithFilter) {
        if (getContext() == null) return;

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, apiUrlWithFilter, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                generateCardsList(response);
                setUpRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse.statusCode == 400) {
                    Toast.makeText(getContext(), R.string.error_text_no_cards_found, Toast.LENGTH_LONG).show();
                    Intent settingsIntent = new Intent(getContext(), SettingsActivity.class);
                    startActivity(settingsIntent);
                } else {
                    Intent errorActivity = new Intent(getContext(), ErrorActivity.class);
                    startActivity(errorActivity);
                }
                if (getActivity() != null) getActivity().finish();
            }
        });
        requestQueue.add(request);
    }

    private void setUpRecyclerView() {
        cardsOverviewRecyclerView.setHasFixedSize(true);
        cardsOverviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cardsOverviewRecyclerView.setAdapter(new CardsOverviewAdapter(cards, onClickListener));
    }

    private void generateCardsList(JSONObject response) {
        if(response.length() <= 0) return;

        cards = new ArrayList<>();

        try {
            // Receive cards objects from data
            JSONArray responseObjects = response.getJSONArray("data");
            for (int i = 0; i < responseObjects.length(); i++) {
                JSONObject cardObject = responseObjects.getJSONObject(i);

                // Receive variables of card
                String cardName = cardObject.getString("name");
                String cardType = cardObject.getString("type");
                String cardDescription = cardObject.getString("desc");

                int cardAtk = -1;
                if (cardObject.has("atk")) {
                    cardAtk = cardObject.getInt("atk");
                }

                int cardDef = -1;
                if (cardObject.has("def")) {
                    cardDef = cardObject.getInt("def");
                }

                int cardLevel = -1;
                if (cardObject.has("level")) {
                    cardLevel = cardObject.getInt("level");
                }

                String cardRace = cardObject.getString("race");

                String cardAttribute = null;
                if (cardObject.has("attribute")) {
                    cardAttribute = cardObject.getString("attribute");
                }

                // Receive image URL of card
                JSONArray cardImgObjects = cardObject.getJSONArray("card_images");
                String cardImgLink = cardImgObjects.getJSONObject(0).getString("image_url");

                cards.add(new CardsOverviewViewModel(cardName, cardType, cardDescription, cardAtk,
                                                     cardDef, cardLevel, cardRace, cardAttribute,
                                                     cardImgLink));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}