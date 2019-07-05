package com.sherpastudio.jpmorganalbums.model.data;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

public class Album {

    @SerializedName("id")
    private final int mId;

    @SerializedName("title")
    private final String mTitle;

    public Album(@IntRange(from=0) int id, @NonNull String title) {
        this.mId = id;
        this.mTitle = title;
    }

    public int getId(){
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean isItemTheSame(Album newItem) {
        return newItem.getId() == getId();
    }

    public boolean isContentTheSame(Album newItem) {
        return getTitle().equals(newItem.getTitle());
    }
}
