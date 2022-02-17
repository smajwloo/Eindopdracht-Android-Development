package com.example.endprojectandroid.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.endprojectandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CardsOverviewFragment extends Fragment {

    private static final String API_URL = "https://db.ygoprodeck.com/api/v7/cardinfo.php";

    private List<CardsOverviewViewModel> cards;
    private RecyclerView cardsOverviewRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards_overview, container, false);
        cardsOverviewRecyclerView = view.findViewById(R.id.yugioh_cards_recycler_view);
        receiveCardsFromAPI();
        return view;
    }

    private void receiveCardsFromAPI() {
        if (getContext() == null) return;

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                generateCardsList(response);
                setUpRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error: " + error);
            }
        });
        requestQueue.add(request);
    }

    private void setUpRecyclerView() {
        cardsOverviewRecyclerView.setHasFixedSize(true);
        cardsOverviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cardsOverviewRecyclerView.setAdapter(new CardsOverviewAdapter(cards));
    }

    private void generateCardsList(JSONObject response) {
        if(response.length() <= 0) return;

        cards = new ArrayList<>();

        try {
            // Receive cards objects from data
            JSONArray responseObjects = response.getJSONArray("data");
            for (int i = 0; i < responseObjects.length(); i++) {
                JSONObject cardObject = responseObjects.getJSONObject(i);

                // Receive name and type of card
                String cardName = cardObject.getString("name");
                String cardType = cardObject.getString("type");

                // Receive image URL of card
                JSONArray cardImgObjects = cardObject.getJSONArray("card_images");
                String cardImgLink = cardImgObjects.getJSONObject(0).getString("image_url");

                cards.add(new CardsOverviewViewModel(cardName, cardImgLink, cardType));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}