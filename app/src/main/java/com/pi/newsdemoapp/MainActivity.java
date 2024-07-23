package com.pi.newsdemoapp;

import static com.pi.newsdemoapp.api.ApiManager.API_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.pi.newsdemoapp.api.ApiManager;
import com.pi.newsdemoapp.api.model.ArticleDM;
import com.pi.newsdemoapp.api.model.ArticlesResponse;
import com.pi.newsdemoapp.api.model.SourceDM;
import com.pi.newsdemoapp.api.model.SourcesResponse;
import com.pi.newsdemoapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArticlesAdapter articlesAdapter = new ArticlesAdapter(new ArrayList<>());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.main);
        initListeners();
        initRecyclerView();
        getSources();

    }

    private void initRecyclerView() {
        articlesAdapter.onClickListener = articleDM -> {
            Intent intent = new Intent(this, ArticleDetailsActivity.class);
            intent.putExtra("article", articleDM);

            startActivity(intent);
        };
        binding.articlesRecyclerview.setAdapter(articlesAdapter);
    }

    private void initListeners() {
        binding.retryButton.setOnClickListener(view -> {
            getSources();
        });

        binding.sourcesTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String id = (String) tab.getTag();
                loadArticles(id);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                String id = (String) tab.getTag();
                loadArticles(id);
            }
        });
    }

    private void loadArticles(String id) {
        showLoading();
        ApiManager.getWebServices().getArticles(API_KEY, id).enqueue(
                new Callback<ArticlesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ArticlesResponse> call,
                                           @NonNull Response<ArticlesResponse> response) {
                        hideLoading();
                        ArticlesResponse articlesResponse = response.body();
                        if (response.isSuccessful() && articlesResponse != null) {
                            List<ArticleDM> articleDMS = articlesResponse.getArticles();
                            if (articleDMS != null && !articleDMS.isEmpty()) {
                                articlesAdapter.refreshArticles(articleDMS);
                            } else {
                                showError("Unable to load articles.");
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArticlesResponse> call,
                                          @NonNull Throwable throwable) {
                        hideLoading();
                        showError("Something went wrong please try again later");
                    }
                }
        );

    }

    private void initTabs(List<SourceDM> sources) {
        for (int i = 0; i < sources.size(); i++) {
            SourceDM source = sources.get(i);
            TabLayout.Tab tab = binding.sourcesTabs.newTab();
            tab.setText(source.getName());
            tab.setTag(source.getId());
            binding.sourcesTabs.addTab(tab);
        }
    }

    private void getSources() {
        showLoading();
        hideError();
        ApiManager.getWebServices().getSources(API_KEY)
                .enqueue(new Callback<SourcesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<SourcesResponse> call,
                                           @NonNull Response<SourcesResponse> response) {
                        hideLoading();
                        SourcesResponse sourceResponse = response.body();
                        List<SourceDM> sources = sourceResponse.getSources();
                        if (response.isSuccessful() && sources != null && !sources.isEmpty()) {
                            initTabs(sources);
                        } else {
                            //todo: show error view
                            showError("Something went wrong please try again later");
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<SourcesResponse> call,
                                          @NonNull Throwable throwable) {
                        hideLoading();
                        showError(throwable.getLocalizedMessage());
                    }
                });
    }

    private void showLoading() {
        binding.loadingView.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        binding.loadingView.setVisibility(View.GONE);
    }

    private void showError(String message) {
        binding.errorView.setVisibility(View.VISIBLE);
        binding.errorMessage.setText(message);
    }

    private void hideError() {
        binding.errorView.setVisibility(View.GONE);
    }
}
