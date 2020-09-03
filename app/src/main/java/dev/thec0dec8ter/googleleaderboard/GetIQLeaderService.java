package dev.thec0dec8ter.googleleaderboard;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetIQLeaderService {

    @GET("/api/skilliq")
    Call<ArrayList<Leader>> getAllLeaders();

}
