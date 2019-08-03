package com.ucsdextandroid2.android2final.Search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ucsdextandroid2.android2final.PlayerData;
import com.ucsdextandroid2.android2final.R;

public class PlayerViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView nameView;

    private PlayerData currentPlayer;
    private OnItemClickListener<PlayerData> clickListener;

    public static PlayerViewHolder inflate(@NonNull ViewGroup parent){
        return new PlayerViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.viewholder_player, parent, false));
    }

    private PlayerViewHolder(@NonNull View itemView){
        super(itemView);

        imageView = itemView.findViewById(R.id.vsi_image);
        nameView = itemView.findViewById(R.id.vsi_username);

        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(clickListener != null && currentPlayer != null){
                    clickListener.onItemClicked(currentPlayer);
                }
            }
        });
    }

    public void bind(PlayerData playerData) {
        this.currentPlayer = playerData;

        nameView.setText(playerData.getUsername());
    }

    public void setClickListener(OnItemClickListener<PlayerData> clickListener){
        this.clickListener = clickListener;
    }
}
