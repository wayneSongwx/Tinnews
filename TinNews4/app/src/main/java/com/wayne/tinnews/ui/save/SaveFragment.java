package com.wayne.tinnews.ui.save;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wayne.tinnews.R;
import com.wayne.tinnews.databinding.FragmentSaveBinding;
import com.wayne.tinnews.model.Article;
import com.wayne.tinnews.repository.NewsRepository;
import com.wayne.tinnews.repository.NewsViewModelFactory;
import com.wayne.tinnews.ui.home.HomeViewModel;

public class SaveFragment extends Fragment {

  private FragmentSaveBinding binding;
  private SaveViewModel viewModel;
  public SaveFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding =  FragmentSaveBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    SavedNewsAdapter savedNewsAdapter = new SavedNewsAdapter();
    savedNewsAdapter.setItemCallback(new SavedNewsAdapter.ItemCallback() {
      @Override
      public void onOpenDetails(Article article) {

      }

      @Override
      public void onRemoveFavorite(Article article) {
         viewModel.deleteSavedArticle(article);
      }
    });
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
    binding.newsResultsRecyclerView.setLayoutManager(linearLayoutManager);
    binding.newsResultsRecyclerView.setAdapter(savedNewsAdapter);

    NewsRepository repository = new NewsRepository();

    viewModel = new ViewModelProvider(this, new NewsViewModelFactory(repository))
            .get(SaveViewModel.class);

    viewModel.getAllSavedArticles().observe(getViewLifecycleOwner(), newsResponse -> {
      if (newsResponse != null) {
        Log.d("SaveFragment", newsResponse.toString());
        savedNewsAdapter.setArticles(newsResponse);
      }
    });
  }
}