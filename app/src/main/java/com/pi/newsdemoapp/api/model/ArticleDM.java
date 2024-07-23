package com.pi.newsdemoapp.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArticleDM implements Serializable {

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("author")
    private String author;

    @SerializedName("urlToImage")
    private String urlToImage;

    @SerializedName("description")
    private String description;

    @SerializedName("source")
    private SourceDM source;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    @SerializedName("content")
    private String content;

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getDescription() {
        return description;
    }

    public SourceDM getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }
}