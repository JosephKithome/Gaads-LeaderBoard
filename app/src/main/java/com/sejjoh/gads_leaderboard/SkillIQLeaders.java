package com.sejjoh.gads_leaderboard;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sejjoh.gads_leaderboard.Adapters.MyAdapter;
import com.sejjoh.gads_leaderboard.Adapters.RecyclerAdapter;
import com.sejjoh.gads_leaderboard.Common.GetClient;
import com.sejjoh.gads_leaderboard.Common.SkillApiInterface;
import com.sejjoh.gads_leaderboard.Models.Skills;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIQLeaders extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Skills> topSkills =new ArrayList<Skills>();
    RecyclerAdapter recyclerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_skill_i_q_leaders, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView =(RecyclerView)view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(getContext(),topSkills);
        recyclerView.setAdapter(recyclerAdapter);

        SkillApiInterface skillApiInterface = GetClient.getClient().create(SkillApiInterface.class);
        Call<List<Skills>> call = skillApiInterface.getSkills();
        call.enqueue(new Callback<List<Skills>>() {
            @Override
            public void onResponse(Call<List<Skills>> call, Response<List<Skills>> response) {
                topSkills.addAll(response.body());
                Log.d("TAG","Response = "+topSkills);
                recyclerAdapter.setSkillList(topSkills);

            }

            @Override
            public void onFailure(Call<List<Skills>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());

            }
        });

    }
}