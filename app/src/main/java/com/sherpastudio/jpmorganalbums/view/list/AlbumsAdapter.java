package com.sherpastudio.jpmorganalbums.view.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sherpastudio.jpmorganalbums.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

class AlbumsAdapter extends RecyclerView.Adapter<AlbumViewHolder> {

    private List<AlbumItemData> mItemList;

    AlbumsAdapter() {
        mItemList = new ArrayList<>();
    }

    protected void setAlbums(@NonNull List<AlbumItemData> newAlbums) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return mItemList.size();
            }

            @Override
            public int getNewListSize() {
                return newAlbums.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                AlbumItemData oldItem = mItemList.get(oldItemPosition);
                AlbumItemData newItem = newAlbums.get(newItemPosition);
                return oldItem.isItemTheSame(newItem);
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                AlbumItemData oldItem = mItemList.get(oldItemPosition);
                AlbumItemData newItem = newAlbums.get(newItemPosition);
                return oldItem.isContentTheSame(newItem);
            }
        });
        mItemList = newAlbums;
        result.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        holder.bind(mItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
