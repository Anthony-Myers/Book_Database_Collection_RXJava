package com.example.bookdatabasecollectionrxjava;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.bookdatabasecollectionrxjava.ui.BookViewModel;
import com.example.bookdatabasecollectionrxjava.ui.BooksFragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainHostActivity extends FragmentActivity {
    private BookViewModel mViewModel;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, BooksFragment.newInstance())
                .addToBackStack(null).commit();

        mViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(BookViewModel.class);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(!sharedPreferences.getBoolean("isPopulated", false)){
            editor.putBoolean("isPopulated", true);
            disposable.add(mViewModel.populateDb()
                    .subscribeOn(Schedulers.io())
                    .subscribe());
        }
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}


