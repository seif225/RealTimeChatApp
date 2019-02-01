package com.example.realtimechatapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    @NonNull
    Context context;
    List<Chat> messages;
    public ChatAdapter(Context context , List<Chat> messages){
        this.context=context;
        this.messages=messages;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.chat_item,viewGroup,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(messages.get(i).getName());
        viewHolder.message.setText(messages.get(i).getMessage());
    }
    @Override
    public int getItemCount() {
        if (messages == null){return 0;}
        return messages.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_tv);
            message=itemView.findViewById(R.id.msg_tv);
        }
    }
}
