package com.example.recycleview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class WordListAdapter extends
        RecyclerView.Adapter<WordListAdapter.WordViewHolder>  {
    private final LinkedList<String> mWordList;
    private final LinkedList<String> mWordList2;
    private LayoutInflater mInflater;
    private Context context;
    public WordListAdapter(Context ct,
                           LinkedList<String> wordList,LinkedList<String> wordList2) {
        context = ct;
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
        this.mWordList2 = wordList2;

    }
    @Override
    public int getItemCount() {
        return mWordList.size();
    }
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item,
                parent, false);
        return new WordViewHolder(mItemView, this);
    }
    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        String mCurrent2 = mWordList2.get(position);
        holder.wordItemView.setText(mCurrent);
        holder.wordItemView2.setText(mCurrent2);
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView wordItemView;
        public final TextView wordItemView2;
        final WordListAdapter mAdapter;
        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            wordItemView2 = itemView.findViewById(R.id.wordcontent);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
            Intent intent=new Intent(context, recipe.class);
            intent.putExtra("position",mPosition);
            context.startActivity(intent);
        }
    }


}
