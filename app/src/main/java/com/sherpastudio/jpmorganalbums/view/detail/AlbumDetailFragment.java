package com.sherpastudio.jpmorganalbums.view.detail;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sherpastudio.jpmorganalbums.Injection;
import com.sherpastudio.jpmorganalbums.R;
import com.sherpastudio.jpmorganalbums.databinding.AlbumDetailFragmentBinding;
import com.sherpastudio.jpmorganalbums.view.list.AlbumListViewModel;

public class AlbumDetailFragment extends Fragment {

    private AlbumDetailViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AlbumDetailFragmentBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.album_detail_fragment, container, false);

        mViewModel = ViewModelProviders.of(
                this,
                Injection.provideViewModelFactory()).get(AlbumDetailViewModel.class);

        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(mViewModel);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        long albumId = AlbumDetailFragmentArgs.fromBundle(getArguments()).getAlbumId();
        mViewModel.setAlbumId(albumId);
    }
}
