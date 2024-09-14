package com.wayne.tinnews.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.wayne.tinnews.model.Article;
import com.wayne.tinnews.model.NewsResponse;
import com.wayne.tinnews.repository.NewsRepository;

public class HomeViewModel extends ViewModel {

  private final NewsRepository repository;
  private final MutableLiveData<String> countryInput = new MutableLiveData<>();

  public HomeViewModel(NewsRepository newsRepository) {
    this.repository = newsRepository;
  }

  public void setCountryInput(String country) {
    countryInput.setValue(country);
  }

  public LiveData<NewsResponse> getTopHeadlines() {
    return Transformations.switchMap(countryInput, repository::getTopHeadlines);
  }

  public void setFavoriteArticleInput(Article article) {
    repository.favoriteArticle(article);
  }
}
