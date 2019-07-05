/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sherpastudio.jpmorganalbums.model.repository.db.dao;


import com.sherpastudio.jpmorganalbums.model.repository.db.TableNames;
import com.sherpastudio.jpmorganalbums.model.repository.db.entity.AlbumEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(List<AlbumEntity> recipes);

    @Query("select * from " + TableNames.TABLE_ALBUMS  + " where id = :albumId")
    AlbumEntity one(long albumId);

    @Query("SELECT * FROM " + TableNames.TABLE_ALBUMS + " ORDER BY title ASC")
    List<AlbumEntity> all();

}
