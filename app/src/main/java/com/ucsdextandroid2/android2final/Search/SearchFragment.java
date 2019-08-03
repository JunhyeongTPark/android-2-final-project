package com.ucsdextandroid2.android2final.Search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import com.ucsdextandroid2.android2final.Data.DataSources;
import com.ucsdextandroid2.android2final.Data.Debouncer;
import com.ucsdextandroid2.android2final.PlayerData;
import com.ucsdextandroid2.android2final.PlayerDetailsActivity;
import com.ucsdextandroid2.android2final.R;

import java.util.Collections;
import java.util.Objects;

public class SearchFragment extends Fragment {

    private SearchAdapter searchAdapter = new SearchAdapter();
    private String latestSearchTerm;
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.am_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(searchAdapter);

        EditText editText = view.findViewById(R.id.am_toolbar_edit_text);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                debouncer.onChange(s.toString());
            }
        });

        searchAdapter.setOnItemClickListener(new OnItemClickListener<PlayerData>() {
            @Override
            public void onItemClicked(PlayerData player) {
//                Intent intent = new Intent(SearchFragment.this, PlayerDetailsActivity.class);
//
//                intent.putExtra("playerdata", player);
//
//                startActivity(intent);
            }
        });

    }


    /**
     * Debouncer keeps us from making a bunch of api calls if the text changes too quickly. We will
     * make at most one search every 200 milliseconds even if the text changes faster than that.
     */
    private Debouncer<String> debouncer = Debouncer.create(200, new Debouncer.ChangeListener<String>() {
        @Override
        public void onChange(String item) {
            performSearch(item);
        }
    });

    private void performSearch(String term) {
        String trimmedTerm = term.trim();

        if(!Objects.equals(trimmedTerm, latestSearchTerm)) {
            latestSearchTerm = trimmedTerm;
            if(TextUtils.isEmpty(trimmedTerm)) {
                searchAdapter.submitList(Collections.emptyList());
            }
            else {
                DataSources.getInstance().getPlayerData(trimmedTerm, data -> {
                    if(!TextUtils.isEmpty(latestSearchTerm))
                        searchAdapter.submitList(data);
                });
            }
        }
    }


}
