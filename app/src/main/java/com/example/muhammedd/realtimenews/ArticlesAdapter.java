package com.example.muhammedd.realtimenews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Muhammed Roudy on 11/11/2016.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {

    private List<Article> articleList;
    Context context;


    public ArticlesAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_list_row,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Article article = articleList.get(position);
        holder.title.setText(article.getTitle());
        Picasso.with(context).load(article.getUrlToImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.article_text);
            image = (ImageView) itemView.findViewById(R.id.article_image);
        }
    }
}
