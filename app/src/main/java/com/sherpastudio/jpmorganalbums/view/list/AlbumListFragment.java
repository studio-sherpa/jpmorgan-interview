package com.sherpastudio.jpmorganalbums.view.list;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sherpastudio.jpmorganalbums.R;
import com.sherpastudio.jpmorganalbums.databinding.AlbumListFragmentBinding;
import com.sherpastudio.jpmorganalbums.Injection;

public class AlbumListFragment extends Fragment {

    private AlbumListViewModel mViewModel;
    private AlbumListFragmentBinding mBinding;
    private AlbumsAdapter mAdapter;
    private SwipeRefreshLayout.OnRefreshListener mOnSwipeToRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mViewModel.refreshList();
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.album_list_fragment, container, false);

        mViewModel = ViewModelProviders.of(
                this,
                Injection.provideViewModelFactory()).get(AlbumListViewModel.class);

        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        mBinding.setViewModel(mViewModel);

        mAdapter = new AlbumsAdapter();

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.recyclerList.setAdapter(mAdapter);
        mBinding.recyclerList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        mBinding.swipeList.setOnRefreshListener(mOnSwipeToRefreshListener);

        mViewModel.getListItems().observe(getViewLifecycleOwner(), items -> {
            if(items == null) return;
            mBinding.swipeList.setRefreshing(false);
            mAdapter.setAlbums(items);
        });

        mViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> mBinding.swipeList.setRefreshing(isLoading));
    }
}
