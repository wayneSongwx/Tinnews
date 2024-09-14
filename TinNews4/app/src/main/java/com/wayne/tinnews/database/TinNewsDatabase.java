package com.wayne.tinnews.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wayne.tinnews.model.Article;

@Database(entities = {Article.class}, version = 1)
public abstract class TinNewsDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
