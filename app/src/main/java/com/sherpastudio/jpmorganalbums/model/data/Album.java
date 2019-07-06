package com.sherpastudio.jpmorganalbums.model.data;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Album {

    @SerializedName("id")
    private final long mId;

    @SerializedName("title")
    private final String mTitle;

    public Album(@IntRange(from=0) long id, @NonNull String title) {
        this.mId = id;
        this.mTitle = title;
    }

    public long getId(){
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

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return mId == album.mId &&
                Objects.equals(mTitle, album.mTitle);
    }
}
