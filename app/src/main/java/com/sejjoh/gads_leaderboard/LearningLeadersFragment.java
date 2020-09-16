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
import com.sejjoh.gads_leaderboard.Common.GetClient;
import com.sejjoh.gads_leaderboard.Common.GetInterface;
import com.sejjoh.gads_leaderboard.Models.TopLerner;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 *created by joseph mulingwa kithome on
 */

public class LearningLeadersFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<TopLerner> myTo20List =new ArrayList<TopLerner>();
    MyAdapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_learning_leaders_fragment, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView =(RecyclerView)view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new MyAdapter(getContext(),myTo20List);
        recyclerView.setAdapter(recyclerAdapter);

        GetInterface getInterface = GetClient.getClient().create(GetInterface.class);
        Call<List<TopLerner>> call = getInterface.getTop20();
        call.enqueue(new Callback<List<TopLerner>>() {
            @Override
            public void onResponse(Call<List<TopLerner>> call, Response<List<TopLerner>> response) {
                assert response.body() != null;
                myTo20List.addAll(response.body());
                Log.d("TAG","Response = "+myTo20List);
                recyclerAdapter.setTopList(myTo20List);

            }

            @Override
            public void onFailure(Call<List<TopLerner>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());

            }
        });

         }
    }