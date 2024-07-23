package com.pi.newsdemoapp.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesResponse {

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<ArticleDM> articles;

    @SerializedName("status")
    private String status;

    public int getTotalResults() {
        return totalResults;
    }

    public List<ArticleDM> getArticles() {
        return articles;
    }

    public String getStatus() {
        return status;
    }
}