package com.utkarsh.githubkotlin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<LanguageItems> itemListU;
    private Context context;


    public ItemAdapter(Context applicationContext, List<LanguageItems> items) {
        this.itemListU = items;
        this.context = applicationContext;
//        Collections.sort(this.itemListU
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new ItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {

        LanguageItems item = itemListU.get(position);
        holder.titleTv.setText(item.getName());
        holder.starTv.setText(String.valueOf(item.getStargazers_count()));
        holder.descTv.setText(item.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailedActivity.class);
                intent.putExtra("login",item.owner.getLogin());
                intent.putExtra("name",item.getName());
                intent.putExtra("description",item.getDescription());
                intent.putExtra("stargazers_count",String.valueOf(item.getStargazers_count()));
                intent.putExtra("forks_count",String.valueOf(item.getForks_count()));
                intent.putExtra("avatar_url",item.owner.getAvatar_url());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return itemListU.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTv,starTv,descTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.repoTitle);
            starTv = itemView.findViewById(R.id.starCount);
            descTv = itemView.findViewById(R.id.repoDescreption);
        }
    }


}
//class UComprator implements Comparator<LanguageItems> {
//
//    @Override
//    public int compare(LanguageItems t0, LanguageItems t1) {
//
//        return t0.getStargazers_count();
//    }
//}