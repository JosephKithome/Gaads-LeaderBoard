package com.sejjoh.gads_leaderboard.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sejjoh.gads_leaderboard.Models.TopLerner;
import com.sejjoh.gads_leaderboard.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<TopLerner> myList;
    Context context;

    public MyAdapter(Context context,List<TopLerner> topLerners) {
        this.context = context;
        this.myList = topLerners;
    }

    public void setTopList(List<TopLerner> movieList) {
        this.myList = movieList;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.learning_leaders, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TopLerner topLerner = myList.get(position);
        holder.name.setText(topLerner.getName());
        holder.hours.setText(topLerner.getHours().concat(" Learning hours ,"));
        holder.country.setText(topLerner.getCountry());

        Glide.with(context).load(myList.get(position)
                .getBadgeUrl()).apply(RequestOptions.centerCropTransform()).into(holder.imageTopLearner);

    }

    @Override
    public int getItemCount() {
        if(myList != null){
            return myList.size();
        }
        return 0;

    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, hours, country;
        public ImageView imageTopLearner;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.txt_name);
            hours = view.findViewById(R.id.txt_progress);
            country = view.findViewById(R.id.txt_live);
            imageTopLearner= view.findViewById(R.id.topLeranerImage);
        }
    }

}
