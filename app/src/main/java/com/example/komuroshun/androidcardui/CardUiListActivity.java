package com.example.komuroshun.androidcardui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class CardUiListActivity extends AppCompatActivity implements CardUiListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_ui_list);
        CardUiListFragment cardUiListFragment = CardUiListFragment.newInstance("1", "2");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, cardUiListFragment);
        fragmentTransaction.commit();
    }

    public void onFragmentInteraction(Uri uri) {

    };
}