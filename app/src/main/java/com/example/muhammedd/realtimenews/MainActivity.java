package com.example.muhammedd.realtimenews;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    ArrayList<Article> articlist;
    DownloadTask downloadTask;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (isNetworkAvailable(getApplicationContext())) {
            Log.i("Check", "Internet connected");

            ArticlesAdapter mAdapter;
            articlist = new ArrayList<Article>();
            downloadTask = new DownloadTask();
             recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());


            String data = "";
            try {
                data = downloadTask.execute("https://newsapi.org/v1/articles?source=techcrunch&apiKey=36c0d35d720f45dbb2856a756c129c86").get();
                createArticles(data);
            } catch (Exception e) {
                e.printStackTrace();
            }

            mAdapter = new ArticlesAdapter(articlist, getApplicationContext());

            recyclerView.setAdapter(mAdapter);

            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Article article = articlist.get(position);
                    Toast.makeText(getApplicationContext(), article.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                    intent.putExtra("article",(Serializable) article);
                    startActivity(intent);
                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));
        }else{
            Log.i("Check","No Internet");
            TextView textView = (TextView) findViewById(R.id.textInternet);
            textView.setAlpha(1);
        }





    }



    public void createArticles(String result){

        try{
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("articles");

            int count = 0;
            while (count<jsonArray.length()){

                JSONObject jObject = jsonArray.getJSONObject(count);

                Article temp = new Article();
                temp.setAuthor(jObject.getString("author"));
                temp.setTitle(jObject.getString("title"));
                temp.setDescription(jObject.getString("description"));
                temp.setPublishedAt(jObject.getString("publishedAt"));
                temp.setUrlToImage(jObject.getString("urlToImage"));
                temp.setUrl(jObject.getString("url"));

                Log.i("article",temp.getTitle());
                articlist.add(temp);
                count++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
