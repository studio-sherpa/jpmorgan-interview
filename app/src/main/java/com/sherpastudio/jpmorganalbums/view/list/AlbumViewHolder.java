package com.sherpastudio.jpmorganalbums.view.list;

import android.view.View;
import android.widget.TextView;

import com.sherpastudio.jpmorganalbums.R;
import com.sherpastudio.jpmorganalbums.model.data.Album;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumViewHolder extends RecyclerView.ViewHolder {

    TextView mTitleView;

    public AlbumViewHolder(@NonNull View itemView) {
        super(itemView);
        mTitleView = itemView.findViewById(R.id.album_title);
    }

    public void bind(Album album) {
        mTitleView.setText(album.getTitle());
    }
}
