package com.sherpastudio.jpmorganalbums.view.list;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

class AlbumItemData {

    private final int mId;
    private final String mTitle;

    AlbumItemData(@IntRange(from=0) int id, @NonNull String title) {
        this.mId = id;
        this.mTitle = title;
    }

    int getId(){
        return mId;
    }

    String getTitle() {
        return mTitle;
    }

    boolean isItemTheSame(AlbumItemData newItem) {
        return newItem.getId() == getId();
    }

    boolean isContentTheSame(AlbumItemData newItem) {
        return getTitle().equals(newItem.getTitle());
    }
}
