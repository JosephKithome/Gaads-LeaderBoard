package com.sejjoh.gads_leaderboard.Common;

import com.sejjoh.gads_leaderboard.Models.TopLerner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetInterface {
    @GET("/api/hours")
    Call<List<TopLerner>> getTop20();
}
