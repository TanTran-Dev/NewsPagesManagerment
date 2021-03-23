package com.trantan.newspagesmanagerment.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.trantan.newspagesmanagerment.model.ItemDataNew;

import java.util.List;

@Dao
public interface ItemDataNewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNew(ItemDataNew itemDataNew);

    @Update
    void updateNew(ItemDataNew itemDataNew);

    @Query("DELETE FROM New")
    void deleteAll();

    @Query("SELECT * FROM New")
    public List<ItemDataNew> findAllItemDataNews();
}
