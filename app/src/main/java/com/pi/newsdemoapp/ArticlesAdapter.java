package com.pi.newsdemoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pi.newsdemoapp.api.model.ArticleDM;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder> {
    List<ArticleDM> articleDMList;
    OnClickListener onClickListener;

    public ArticlesAdapter(List<ArticleDM> articleDMList) {
        this.articleDMList = articleDMList;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView,
                                                 int viewType) {
        View articleView = LayoutInflater.from(recyclerView.getContext()).
                inflate(R.layout.item_article, recyclerView, false);
        return new ArticlesViewHolder(articleView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {
        ArticleDM articleDM = articleDMList.get(position);
        holder.title.setText(articleDM.getTitle());
        holder.description.setText(articleDM.getDescription());
        Glide.with(holder.itemView).
                load(articleDM.getUrlToImage())
                .into(holder.articleImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onArticleClick(articleDM);
            }
        });
    }

    public void refreshArticles(List<ArticleDM> articleDMList) {
        this.articleDMList = articleDMList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return articleDMList.size();
    }

    interface OnClickListener {
        public void onArticleClick(ArticleDM articleDM);
    }

    class ArticlesViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView articleImage;

        public ArticlesViewHolder(@NonNull View constrainLayout) {
            super(constrainLayout);
            title = constrainLayout.findViewById(R.id.article_title);
            description = constrainLayout.findViewById(R.id.article_description);
            articleImage = constrainLayout.findViewById(R.id.article_image);
        }
    }
}
