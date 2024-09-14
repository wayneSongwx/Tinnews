package com.wayne.tinnews;

import android.app.Application;

import androidx.room.Room;

import com.facebook.stetho.Stetho;
import com.wayne.tinnews.database.TinNewsDatabase;

public class TinNewsApplication extends Application {

  private static TinNewsDatabase database;

  @Override
  public void onCreate() {
    super.onCreate();
    // TODO: new code here.
    Stetho.initializeWithDefaults(this);
    database = Room.databaseBuilder(this, TinNewsDatabase.class, "tinnews_db").build();
  }

  public static TinNewsDatabase getDatabase() {
    return database;
  }
}
