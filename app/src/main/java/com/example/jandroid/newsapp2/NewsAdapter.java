package com.example.jandroid.newsapp2;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>  {
    private List<News> news;
    private Context context;
    private View.OnClickListener onItemClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String temp = news.get((int) v.getTag()).getUrlArticle();
            intent.setData(Uri.parse(temp));
        }
    };


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView article, time, date, section;

        public MyViewHolder(View view){
            super(view);
            article=(TextView) view.findViewById(R.id.name_article);
            time=(TextView) view.findViewById(R.id.time);
            date=(TextView) view.findViewById(R.id.date);
            section=(TextView) view.findViewById(R.id.section_name);
        }
    }
    public NewsAdapter(Context pContext,List<News> pNews){
        this.context=pContext;
        this.news=pNews;
    }




    private String[] dataFormatter(String dateTime) {
        String[] dateAndTimeSplit = dateTime.replace("T", " ").replace("Z", " ").trim().split(" ");

        return dateAndTimeSplit;
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        itemView.setOnClickListener(mOnClickListener);

        return new MyViewHolder(itemView);
    }


    public void onBindViewHolder(MyViewHolder holder, int position) {
        News tempNews=news.get(position);
        holder.article.setText(tempNews.getNameArticle());
        holder.section.setText(tempNews.getSectionName());
        String dateTime = tempNews.getDatePublished();
        String[] dateAndTime = dataFormatter(dateTime);
        holder.date.setText(dateAndTime[0]);
        holder.time.setText(dateAndTime[1]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                News tempNews = news.get(position);

                Uri newsUri = Uri.parse(tempNews.getUrlArticle());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                v.getContext().startActivity(websiteIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return news.size();
    }
    public void clear() {
        int size = news.size();
        news.clear();
        notifyItemRangeRemoved(0, size);
    }

}
