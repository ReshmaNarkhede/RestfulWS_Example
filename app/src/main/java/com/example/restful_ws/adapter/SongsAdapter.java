package com.example.restful_ws.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.restful_ws.R;
import com.example.restful_ws.model.SongModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.MyViewHolder> {
    private List<SongModel.Result> results;
    private Context context;
    private RecyclerViewClickListener listener;

    public SongsAdapter(Context context, List<SongModel.Result> results, RecyclerViewClickListener listener) {
        this.results = results;
        this.context = context;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public View itemView;
        public ImageView thumbImage;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.artist_name);
            year = (TextView) view.findViewById(R.id.year);
            thumbImage = (ImageView) view.findViewById(R.id.thumb_image);
            itemView = view.findViewById(R.id.item_view);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_list_row_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final SongModel.Result songModelObj = results.get(position);
        holder.title.setText(songModelObj.getTrackName());
        holder.genre.setText(songModelObj.getArtistName());
        holder.year.setText(getDate(songModelObj.getReleaseDate()));

        String url = songModelObj.getArtworkUrl100();

        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.thumbImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(songModelObj);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public String getDate(String strdate){
        String dateStr = null;
        Date date = null;
        DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateFormat outputDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = inputDate.parse(strdate);
            dateStr = outputDate.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }
}