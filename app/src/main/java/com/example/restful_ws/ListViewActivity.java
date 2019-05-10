package com.example.restful_ws;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.restful_ws.adapter.RecyclerViewClickListener;
import com.example.restful_ws.adapter.SongsAdapter;
import com.example.restful_ws.model.SongModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class ListViewActivity extends AppCompatActivity implements RecyclerViewClickListener {
    private List<SongModel> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SongsAdapter mAdapter;
    private SongModel songModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        Call<SongModel> call = apiService.getSongList();
        call.enqueue(new Callback<SongModel>() {
            @Override
            public void onResponse(Call<SongModel> call, retrofit2.Response<SongModel> response) {

                if(response.body()!=null) {
                    pDialog.dismiss();
                    songModel = response.body();
                    mAdapter = new SongsAdapter(ListViewActivity.this,songModel.getResults(),ListViewActivity.this);

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<SongModel> call, Throwable t) {
                pDialog.dismiss();
                Log.e("ERROR", t.toString());
            }
        });
    }

    @Override
    public void onItemClick(SongModel.Result songModel) {
        Intent intent = new Intent(ListViewActivity.this,SongDetailActivity.class);
        intent.putExtra("SongObj", songModel);
        startActivity(intent);
//        Toast.makeText(this, "CLick", Toast.LENGTH_SHORT).show();
    }
}
