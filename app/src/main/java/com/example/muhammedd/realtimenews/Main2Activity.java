package com.example.muhammedd.realtimenews;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {

    Article article;
    ImageView imageView;
    TextView title, des, date, author;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        article = (Article) getIntent().getExtras().getSerializable("article");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        imageView = (ImageView) findViewById(R.id.bgheader);
        title = (TextView) findViewById(R.id.article_title);
        des = (TextView) findViewById(R.id.article_des);
        date = (TextView) findViewById(R.id.date);
        author = (TextView) findViewById(R.id.article_by);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbarLayout.setTitle("RealtimeNews");

        Picasso.with(getApplicationContext()).load(article.getUrlToImage()).into(imageView);
        title.setText(article.getTitle());
        des.setText(article.getDescription());
        date.setText(article.getPublishedAt());
        author.setText("Author: "+article.getAuthor());
    }
}
