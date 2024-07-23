package com.pi.newsdemoapp.api;

import com.pi.newsdemoapp.api.model.ArticlesResponse;
import com.pi.newsdemoapp.api.model.SourcesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServices {

    @GET("v2/top-headlines/sources")
    Call<SourcesResponse> getSources(@Query("apiKey") String apiKey);

    @GET("v2/everything")
    Call<ArticlesResponse> getArticles(@Query("apiKey") String apiKey,
                                       @Query("sources") String sourceId);
}
