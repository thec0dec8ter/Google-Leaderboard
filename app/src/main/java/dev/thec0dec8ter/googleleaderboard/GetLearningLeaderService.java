package dev.thec0dec8ter.googleleaderboard;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetLearningLeaderService {

    @GET("/api/hours")
    Call<ArrayList<Leader>> getAllLeaders();

}
