package com.example.bookdatabasecollectionrxjava.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookdatabasecollectionrxjava.databinding.BooksFragmentBinding;
import com.example.bookdatabasecollectionrxjava.ui.adapter.BooksAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class BooksFragment extends Fragment {
    private BooksFragmentBinding binding;
    private BookViewModel mViewModel;
    public static final String TAG = BooksFragment.class.getName();
    private CompositeDisposable disposable = new CompositeDisposable();

    public static BooksFragment newInstance(){return new BooksFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BooksFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(),
                new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()))
                .get(BookViewModel.class);

        RecyclerView booksRv = binding.booksRv;
        booksRv.setLayoutManager(new LinearLayoutManager(requireContext()));

        disposable.add(mViewModel.getListOfBooks().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bookData->{
                    BooksAdapter booksAdapter = new BooksAdapter(bookData);
                    booksAdapter.setOnItemClickListener((position) -> {
                        Uri uri = Uri.parse(booksAdapter.getItem(position).getUrl());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    });

                    booksRv.setAdapter(booksAdapter);

                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(booksRv.getContext(), DividerItemDecoration.VERTICAL);
                    booksRv.addItemDecoration(dividerItemDecoration);
                }, e -> Log.d(TAG, e.toString())));
    }

    @Override
    public void onDestroyView() {
        disposable.dispose();
        binding = null;
        super.onDestroyView();
    }
}
