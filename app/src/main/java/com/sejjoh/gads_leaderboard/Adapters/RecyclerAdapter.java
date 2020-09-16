package com.sejjoh.gads_leaderboard.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sejjoh.gads_leaderboard.Models.Skills;
import com.sejjoh.gads_leaderboard.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>  {

    private List<Skills> mySkillList;
    Context context;

    public RecyclerAdapter(Context context,List<Skills> skills) {
        this.context = context;
        this.mySkillList = skills;
    }

    public void setSkillList(List<Skills> skillsList) {
        this.mySkillList = skillsList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override

    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skill_iq_list_items, parent, false);

        return new RecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        Skills skills =mySkillList.get(position);
        holder.name.setText(skills.getName());
        holder.score.setText(skills.getScore().concat(" skill IQ Score ,"));
        holder.country.setText(skills.getCountry());

        Glide.with(context).load(mySkillList.get(position)
                .getBadgeUrl()).apply(RequestOptions.centerCropTransform()).into(holder.skillImage);


    }

    @Override
    public  int getItemCount() {
        if(mySkillList != null){
            return mySkillList.size();
        }
        return 0;

    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, score, country;
        public ImageView skillImage;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.txt_name);
            score = view.findViewById(R.id.txt_skill);
            country = view.findViewById(R.id.txt_country);
            skillImage=view.findViewById(R.id.skillsImage);

        }
    }
}
