package com.example.headerrecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] contents = new String[20];
        for (int i = 0; i < 20; i++) {
            contents[i] = String.valueOf(i);
        }

        RecyclerView recyclerView = findViewById(R.id.header_recycler_view);
        NormalRecyclerAdapter normalAdapter = new NormalRecyclerAdapter(Arrays.asList(contents));
        HeaderRecyclerAdapter headerAdapter = new HeaderRecyclerAdapter(normalAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(headerAdapter);

        View header = LayoutInflater.from(this).inflate(R.layout.recycler_view_header, recyclerView, false);
        TextView textView = header.findViewById(R.id.item_header);
        textView.setText("测试添加列表头部");
        headerAdapter.setHeaderView(header);
    }
}