package dev.thec0dec8ter.googleleaderboard;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    public static String BASE_URL = "https://gadsapi.herokuapp.com";

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetroSubmissionInstance(){
        return new Retrofit.Builder().baseUrl("https://docs.google.com/forms/d/e/").build();
    }

}
