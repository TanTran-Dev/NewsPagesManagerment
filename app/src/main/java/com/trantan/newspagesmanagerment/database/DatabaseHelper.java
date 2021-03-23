package com.trantan.newspagesmanagerment.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.trantan.newspagesmanagerment.dao.ItemDataNewsDAO;
import com.trantan.newspagesmanagerment.model.ItemDataNew;

@Database(entities = {ItemDataNew.class}, version = 2)
public abstract class DatabaseHelper extends RoomDatabase {
    private static final String DATABASE_NAME = "new_database";
    private static DatabaseHelper INSTANCE;

    public abstract ItemDataNewsDAO getItemDataNewsDAO();

    public static DatabaseHelper getInMemoryDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseHelper.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
