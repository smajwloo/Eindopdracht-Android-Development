package com.example.endprojectandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.endprojectandroid.Fragments.CardInformationFragment;
import com.example.endprojectandroid.Fragments.CardsOverviewFragment;
import com.example.endprojectandroid.Helper.CardsOverviewViewModel;
import com.example.endprojectandroid.Helper.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.recycler_view_fragment, new CardsOverviewFragment()).commit();
    }

    @Override
    public void onItemSelected(CardsOverviewViewModel cardsOverviewViewModel) {
        boolean isPortrait =
                getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        if(isPortrait) {
            Intent cardInformationIntent = new Intent(this, CardInformationActivity.class);
            cardInformationIntent.putExtra("cardsOverviewViewModel", cardsOverviewViewModel);
            startActivity(cardInformationIntent);
        } else {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            CardInformationFragment cardInformationFragment = new CardInformationFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("cardsOverviewViewModel", cardsOverviewViewModel);
            cardInformationFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.card_info_fragment, cardInformationFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
        return true;
    }
}