package com.wayne.tinnews.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.wayne.tinnews.databinding.FragmentSearchBinding;
import com.wayne.tinnews.repository.NewsRepository;
import com.wayne.tinnews.repository.NewsViewModelFactory;

public class SearchFragment extends Fragment {

  private SearchViewModel viewModel;
  private FragmentSearchBinding binding;

  public SearchFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding = FragmentSearchBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    SearchNewsAdapter newsAdapter = new SearchNewsAdapter();
    GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
    binding.newsResultsRecyclerView.setLayoutManager(gridLayoutManager);
    binding.newsResultsRecyclerView.setAdapter(newsAdapter);

    binding.newsSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        if (!query.isEmpty()) {
          viewModel.setSearchInput(query);
        }
        binding.newsSearchView.clearFocus();
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        return false;
      }
    });

    NewsRepository repository = new NewsRepository();
    viewModel = new ViewModelProvider(this, new NewsViewModelFactory(repository))
                    .get(SearchViewModel.class);
    viewModel
        .searchNews()
        .observe(
            getViewLifecycleOwner(),
            newsResponse -> {
              if (newsResponse != null) {
                newsAdapter.setArticles(newsResponse.articles);
              }
            });
  }
}