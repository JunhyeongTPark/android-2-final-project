package com.ucsdextandroid2.android2final;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class PlayerDetailsActivity extends AppCompatActivity {
    private TextView name = null;
    private TextView kills = null;
    private TextView assists = null;
    private TextView wins = null;
    private TextView losses = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);

        this.name = (TextView) findViewById(R.id.textViewName);
        this.kills = (TextView) findViewById(R.id.textViewKills);
        this.assists = (TextView) findViewById(R.id.textViewAssists);
        this.wins = (TextView) findViewById(R.id.textViewWins);
        this.losses = (TextView) findViewById(R.id.textViewLosses);

        PlayerData playerData = getIntent().getParcelableExtra("playerdata");

        if(playerData != null){
//            name.setText(playerData.);
            kills.setText(playerData.getKills());
            assists.setText(playerData.getAssists());
            wins.setText(playerData.getWins());
            losses.setText(playerData.getLosses());
        }
    }
}
