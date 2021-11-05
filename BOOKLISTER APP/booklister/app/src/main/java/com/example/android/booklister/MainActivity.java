package com.example.android.booklister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<books>> {
TextView textView;
    EditText search;
    TextView text;
    Button button;
    RecyclerView recycler;
    String url;
    adapter adap;
    List<books> booklist=new ArrayList<>();
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.search_button);
        textView=findViewById(R.id.textView4);
        search = findViewById(R.id.search);
        recycler = findViewById(R.id.recycler);
        text=findViewById(R.id.notfound);
Log.e("hl","lfjdk");
textView.setVisibility(View.VISIBLE);
        adap=new adapter(booklist);
progress=findViewById(R.id.progressBar);
        recycler.setAdapter(adap);
recycler.setLayoutManager(new LinearLayoutManager(this));


button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

textView.setVisibility(View.INVISIBLE);
        adap=new adapter(booklist);
        recycler.setAdapter(adap);
        recycler.setLayoutManager(new LinearLayoutManager((MainActivity.this)));
        LoaderManager.getInstance(MainActivity.this).restartLoader(0,null,MainActivity.this);

        progress.setVisibility(View.VISIBLE);
    }
});
    }





    @Override
    public Loader<List<books>> onCreateLoader(int id, Bundle args) {
        url = "https://www.googleapis.com/books/v1/volumes?q=" + search.getText().toString().trim();

        return new Asyncloader(this,url);


    }
public void updateUI(List<books> data){
        if(data.size()==0){
    text.setVisibility(View.VISIBLE);
    text.setText("Book Not Found");
}
        adap.updatebook(data);
        adap=new adapter(data);



}
    @Override
    public void onLoadFinished(Loader<List<books>> loader, List<books> data) {
Log.e("flkdj","lkfdjjfkld");
      updateUI(data);
progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<List<books>> loader) {
adap.updatebook(new ArrayList<books>());
    }



}