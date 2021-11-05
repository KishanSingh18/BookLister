package com.example.android.booklister;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class Asyncloader extends AsyncTaskLoader<List<books>> {
    String url;
    public Asyncloader(Context context,String url) {
        super(context);
        this.url=url;
        Log.e("Lkfjlk","jjdflkjk");
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<books> loadInBackground() {
        if(url==null){
            return null;
        }Log.e("hg","gygth");

        List<books> booklist=query.apicall(url);
        return booklist;
    }
}
