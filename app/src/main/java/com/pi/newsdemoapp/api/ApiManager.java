package com.pi.newsdemoapp.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    static final public String API_KEY = "a2803275cc264f5ab82151862011361a";
    static private final String BASE_URL = "https://newsapi.org";
    static private Retrofit retrofit;

    static private Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient loggingClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
            //logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(loggingClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    static public WebServices getWebServices() {
        Retrofit retrofit = getRetrofitInstance();
        return retrofit.create(WebServices.class);
    }
}