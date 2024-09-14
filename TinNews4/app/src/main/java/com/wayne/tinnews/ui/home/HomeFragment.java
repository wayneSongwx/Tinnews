package com.wayne.tinnews.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.wayne.tinnews.databinding.FragmentHomeBinding;
import com.wayne.tinnews.model.Article;
import com.wayne.tinnews.repository.NewsRepository;
import com.wayne.tinnews.repository.NewsViewModelFactory;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;

import java.util.List;

public class HomeFragment extends Fragment implements CardStackListener {

  private HomeViewModel viewModel;
  private FragmentHomeBinding binding;
  private CardStackLayoutManager layoutManager;
  private List<Article> articles;

  public HomeFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    binding = FragmentHomeBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    CardSwipeAdapter cardSwipeAdapter = new CardSwipeAdapter();
    layoutManager = new CardStackLayoutManager(requireContext(), this);
    layoutManager.setStackFrom(StackFrom.Top);
    binding.homeCardStackView.setLayoutManager(layoutManager);
    binding.homeCardStackView.setAdapter(cardSwipeAdapter);

    NewsRepository repository = new NewsRepository();

    viewModel = new ViewModelProvider(this, new NewsViewModelFactory(repository))
                    .get(HomeViewModel.class);
    viewModel.setCountryInput("us");

    viewModel
        .getTopHeadlines()
        .observe(
            getViewLifecycleOwner(),
            newsResponse -> {
              if (newsResponse != null) {
                Log.d("HomeFragment", newsResponse.toString());
                articles = newsResponse.articles;
                cardSwipeAdapter.setArticles(articles);
              }
            }
        );

    binding.homeLikeButton.setOnClickListener(v -> swipeCard(Direction.Right));
    binding.homeUnlikeButton.setOnClickListener(v -> swipeCard(Direction.Left));
  }

  private void swipeCard(Direction direction) {
    SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
            .setDirection(direction)
            .setDuration(Duration.Normal.duration)
            .build();
    layoutManager.setSwipeAnimationSetting(setting);
    binding.homeCardStackView.swipe();
  }

  @Override
  public void onCardDragging(Direction direction, float ratio) {

  }

  @Override
  public void onCardSwiped(Direction direction) {
    if (direction == Direction.Left) {
      Log.d("CardStackView", "Unliked " + layoutManager.getTopPosition());
    } else if (direction == Direction.Right) {
      Log.d("CardStackView", "Liked "  + layoutManager.getTopPosition());
      viewModel.setFavoriteArticleInput(articles.get(layoutManager.getTopPosition() - 1));
    }
  }

  @Override
  public void onCardRewound() {

  }

  @Override
  public void onCardCanceled() {

  }

  @Override
  public void onCardAppeared(View view, int position) {

  }

  @Override
  public void onCardDisappeared(View view, int position) {

  }
}