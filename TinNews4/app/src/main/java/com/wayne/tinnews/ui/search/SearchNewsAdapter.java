package com.wayne.tinnews.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wayne.tinnews.R;
import com.wayne.tinnews.databinding.SearchNewsItemBinding;
import com.wayne.tinnews.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder> {

  // 1. Supporting data:
  private List<Article> articles = new ArrayList<>();

  public void setArticles(List<Article> newsList) {
    articles.clear();
    articles.addAll(newsList);
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_news_item, parent, false);
    return new SearchNewsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
    Article article = articles.get(position);
    holder.itemTitleTextView.setText(article.title);
    Picasso.get().load(article.urlToImage).resize(200, 200).into(holder.itemImageView);
  }

  @Override
  public int getItemCount() {
    return articles.size();
  }

  // 3. SearchNewsViewHolder:
  public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {

    ImageView itemImageView;
    TextView itemTitleTextView;

    public SearchNewsViewHolder(@NonNull View itemView) {
      super(itemView);
      SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
      itemImageView = binding.searchItemImage;
      itemTitleTextView = binding.searchItemTitle;
    }
  }
}
