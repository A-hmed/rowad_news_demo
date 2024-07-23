package com.pi.newsdemoapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.pi.newsdemoapp.api.model.ArticleDM;

public class ArticleDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        ArticleDM articleDM = (ArticleDM) getIntent().getSerializableExtra("article");
        Log.e("ArticleDetailsActivity", "article title =" + articleDM.getTitle());
    }
}