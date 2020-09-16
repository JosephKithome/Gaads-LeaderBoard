package com.sejjoh.gads_leaderboard.Common;

import com.sejjoh.gads_leaderboard.Models.Skills;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillApiInterface {
    @GET("/api/skilliq")
    Call<List<Skills>> getSkills();
}
