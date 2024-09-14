package com.wayne.tinnews.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.wayne.tinnews.model.Article;

import java.util.List;

@Dao
public interface ArticleDao {
    @Insert
    void saveArticle(Article article);

    @Delete
    void deleteArticle(Article article);

    @Query("select * from Article")
    LiveData<List<Article>> getAllArticles();
}
