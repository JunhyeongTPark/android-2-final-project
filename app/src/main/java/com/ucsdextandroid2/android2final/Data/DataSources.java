package com.ucsdextandroid2.android2final.Data;

import android.util.Log;
import com.ucsdextandroid2.android2final.BuildConfig;
import com.ucsdextandroid2.android2final.PlayerData;
import com.ucsdextandroid2.android2final.ResponseData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import java.util.Collections;
import java.util.List;

import static com.ucsdextandroid2.android2final.Data.RandomUtils.getRandomNumberInRange;

public class DataSources {

    private static DataSources instance;

    private PUBGapi pubGapi;

    private DataSources() {

        this.pubGapi = new Retrofit.Builder()
                .baseUrl("https://api.pubg.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PUBGapi.class);
    }

    public static DataSources getInstance() {
        if(instance == null)
            instance = new DataSources();

        return instance;
    }

    public void getPlayerData(String username, Callback<List<PlayerData>> callback){
        pubGapi.getPlayerStats(username).enqueue(new retrofit2.Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()){

                    try{
                        PlayerData playerData = response.body().getData().getAttributes().getGameModeStats().getSolo().copy(
                                getRandomNumberInRange(),
                                getRandomNumberInRange(),
                                getRandomNumberInRange(),
                                getRandomNumberInRange(),
                                username
                        );
                        callback.onDataFetched(Collections.singletonList(playerData));
                    } catch(NullPointerException e){
                        Log.e("DataSource", "Value was null", e);
                        callback.onDataFetched(Collections.emptyList());
                    }

                }
                else{
                    callback.onDataFetched(Collections.emptyList());
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                callback.onDataFetched(null);
            }
        });
    }

    public interface Callback<T> {
        void onDataFetched(T data);
    }

    private interface PUBGapi {

        @Headers({
                "Authorization: Bearer " + BuildConfig.api_key,
                "Accept: application/vnd.api+json"
        })
        @GET("shards/steam/players/{player_id}/seasons/lifetime")
        Call<ResponseData> getPlayerStats(@Path("player_id") String player_id);

    }
}
