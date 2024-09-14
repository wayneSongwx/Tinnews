package com.wayne.tinnews.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.wayne.tinnews.model.NewsResponse;
import com.wayne.tinnews.repository.NewsRepository;

public class SearchViewModel extends ViewModel {

  private final NewsRepository repository;
  private final MutableLiveData<String> searchInput = new MutableLiveData<>();

  public SearchViewModel(NewsRepository repository) {
    this.repository = repository;
  }

  public void setSearchInput(String query) {
    searchInput.setValue(query);
  }

  public LiveData<NewsResponse> searchNews() {
    return Transformations.switchMap(searchInput, repository::searchNews);
  }

}
