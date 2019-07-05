package com.sherpastudio.jpmorganalbums.view.list;

import android.view.View;
import android.widget.TextView;

import com.sherpastudio.jpmorganalbums.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumViewHolder extends RecyclerView.ViewHolder {

    TextView mTitleView;

    public AlbumViewHolder(@NonNull View itemView) {
        super(itemView);
        mTitleView = itemView.findViewById(R.id.album_title);
    }

    public void bind(AlbumItemData albumItemData) {
        mTitleView.setText(albumItemData.getTitle());
    }
}
