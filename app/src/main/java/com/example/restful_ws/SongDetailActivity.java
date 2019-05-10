package com.example.restful_ws;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.restful_ws.model.SongModel;

public class SongDetailActivity extends AppCompatActivity {
    TextView artistName,trackName,collectionName,collectionPrize,trackPrize,currency,country;
    private SongModel.Result songObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        if(intent!=null){
            songObj = (SongModel.Result) intent.getSerializableExtra("SongObj");
        }

        artistName = findViewById(R.id.artist_name);
        trackName = findViewById(R.id.track_name);
        collectionName = findViewById(R.id.collection_name);
        collectionPrize = findViewById(R.id.collection_prize);
        trackPrize = findViewById(R.id.track_prize);
        currency = findViewById(R.id.currency);
        country = findViewById(R.id.country);

        artistName.setText(String.format("Artist Name: %s", songObj.getArtistName()));
        trackName.setText(String.format("Track Name: %s", songObj.getTrackName()));
        collectionName.setText(String.format("Collection Name: %s", songObj.getCollectionName()));
        collectionPrize.setText(String.format("Collection price: %s", songObj.getCollectionPrice()));
        trackPrize.setText(String.format("Track Price: %s", songObj.getTrackPrice()));
        currency.setText(String.format("Currency: %s", songObj.getCurrency()));
        country.setText(String.format("Country: %s", songObj.getCountry()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
