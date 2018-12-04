package com.negativ.javabasics.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.negativ.javabasics.Card;
import com.negativ.javabasics.MainActivity;
import com.negativ.javabasics.R;
import com.negativ.javabasics.RVAdapter;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewFragment extends Fragment {

    private List<Card> cards;
    RecyclerView rv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, null);
        rv = (RecyclerView)view.findViewById(R.id.rv);
        cards = new ArrayList<>();
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        RVAdapter adapter = new RVAdapter(cards, getContext());
        rv.setAdapter(adapter);

        String product = "";

        Cursor cursor = MainActivity.mDb.rawQuery("SELECT * FROM clients", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product += cursor.getString(1) + " | ";
            cursor.moveToNext();
        }
        cursor.close();

        cards.add(new Card("Arrays"));
        cards.add(new Card("Arrays"));
        cards.add(new Card("Arrays"));
        cards.add(new Card("Arrays"));

        return view;
    }
}
