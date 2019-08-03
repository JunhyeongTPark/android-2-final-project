package com.ucsdextandroid2.android2final.Search;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsdextandroid2.android2final.PlayerData;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<PlayerViewHolder> {
    private List<? extends PlayerData> players = new ArrayList<>();

    private OnItemClickListener<PlayerData> onItemClickListener;

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlayerViewHolder playerViewHolder = PlayerViewHolder.inflate(parent);
        playerViewHolder.setClickListener(item -> {
            if (onItemClickListener != null)
                onItemClickListener.onItemClicked(item);
        });

        return playerViewHolder;
    }

    public void submitList(@Nullable List<? extends PlayerData> list){
        this.players = list;
    }

    public void setOnItemClickListener(OnItemClickListener<PlayerData> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private PlayerData getItem(int position) {
        return players.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() { return players.size();  }
}
