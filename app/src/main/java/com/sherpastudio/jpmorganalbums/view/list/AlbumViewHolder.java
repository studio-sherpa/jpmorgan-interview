package com.sherpastudio.jpmorganalbums.view.list;

import android.view.View;
import android.widget.TextView;

import com.sherpastudio.jpmorganalbums.R;
import com.sherpastudio.jpmorganalbums.model.data.Album;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumViewHolder extends RecyclerView.ViewHolder {

    TextView mTitleView;
    private Album album;

    public AlbumViewHolder(@NonNull View itemView) {
        super(itemView);
        mTitleView = itemView.findViewById(R.id.album_title);
        mTitleView.setOnClickListener(v -> {
            AlbumListFragmentDirections.DetailAction detailAction =
                    AlbumListFragmentDirections.detailAction(album.getId());
            Navigation.findNavController(v).navigate(detailAction);
        });
    }

    public void bind(Album album) {
        this.album = album;
        mTitleView.setText(album.getTitle());
    }
}
