package com.example.cse3311project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Item> list;
    private final OnItemListener mOnItemListener;


    public MyAdapter(Context context, ArrayList<Item> list, OnItemListener onItemListener) {
        this.context = context;
        this.list = list;
        this.mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v, mOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = list.get(position);
        holder.itemName.setText(item.getItemName());
        holder.itemPrice.setText(Float.toString(item.getItemPrice()));
        holder.itemCategory.setText(item.getItemCategory());
        holder.itemUser.setText(item.getItemUser());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView itemName, itemPrice, itemCategory, itemUser;
        OnItemListener onItemListener;

        public MyViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);

            itemName = itemView.findViewById(R.id.listItemName);
            itemPrice = itemView.findViewById(R.id.listItemPrice);
            itemCategory = itemView.findViewById(R.id.listItemCategory);
            itemUser = itemView.findViewById(R.id.listItemUser);
            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemListener.OnItemClick(getAdapterPosition());
        }
    }
    public interface OnItemListener{
        void OnItemClick(int position);
    }

}
