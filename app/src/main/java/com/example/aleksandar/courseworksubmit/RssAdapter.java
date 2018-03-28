package com.example.aleksandar.courseworksubmit;

/**
 * Created by Aleksandar Krastev S1433655 on 28/03/2018.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RssAdapter extends RecyclerView.Adapter<RssAdapter.MyViewHolder> {
    ArrayList<RssItem> rssItems;
    Context contxt;

    public RssAdapter(ArrayList<RssItem> rssItems, Context contxt) {
        this.rssItems = rssItems;
        this.contxt = contxt;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(contxt).inflate(R.layout.new_row_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RssItem current=rssItems.get(position);
        holder.Title.setText(current.getTitle());
        holder.Description.setText(current.getDescription());
        holder.Date.setText(current.getPubDate());
    }

    @Override
    public int getItemCount() {
        return rssItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title,Description,Date;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            Title= (TextView) itemView.findViewById(R.id.title_text);
            Description= (TextView) itemView.findViewById(R.id.description_text);
            Date= (TextView) itemView.findViewById(R.id.date_text);
            cardView= (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}