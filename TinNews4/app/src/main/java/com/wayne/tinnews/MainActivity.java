package com.wayne.tinnews;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wayne.tinnews.model.NewsResponse;
import com.wayne.tinnews.network.NewsApi;
import com.wayne.tinnews.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  private NavController navController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    BottomNavigationView navView = findViewById(R.id.nav_view);

    NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
        .findFragmentById(R.id.nav_host_fragment);

    navController = navHostFragment.getNavController();

    NavigationUI.setupWithNavController(navView, navController);
    NavigationUI.setupActionBarWithNavController(this, navController);
  }

  @Override
  public boolean onSupportNavigateUp() {
    return navController.navigateUp();
  }
}