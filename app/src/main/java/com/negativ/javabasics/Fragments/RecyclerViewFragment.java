package com.negativ.javabasics.Fragments;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.negativ.javabasics.Card;
import com.negativ.javabasics.DatabaseHelper;
import com.negativ.javabasics.MainActivity;
import com.negativ.javabasics.R;
import com.negativ.javabasics.RVAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RecyclerViewFragment extends Fragment {

    private List<Card> cards;
    RecyclerView rv;

    public FragmentTransaction fragmentTransaction;

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mDBHelper = new DatabaseHelper(getContext());
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, null);
        rv = (RecyclerView)view.findViewById(R.id.rv);
        cards = new ArrayList<>();
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        RVAdapter adapter = new RVAdapter(cards, getActivity());
        adapter.setClickListener(new RVAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // mDb.rawQuery("select * from topics where "+ "id=?",new String[]{"0"});
                Cursor cursor = mDb.rawQuery("select * from topics where id = " + Integer.toString(position), null);
                String header, data;
                System.out.println("Test");
                cursor.moveToFirst();
                header = cursor.getString(cursor.getColumnIndex("header"));
                data = cursor.getString(cursor.getColumnIndex("data"));
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frgmCont, TopicDataFragment.newInstance(header, data));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        rv.setAdapter(adapter);
        getDataFromDatabase("topics");


//        cards.add(new Card("Arrays"));
//        cards.add(new Card("Arrays"));
//        cards.add(new Card("Arrays"));
//        cards.add(new Card("Arrays"));
        return view;
    }

    private void getDataFromDatabase(String tableName) {
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + tableName, null);
        while (cursor.moveToNext()) {
            String dbHeader = cursor.getString(cursor.getColumnIndex("header"));
            String dbData = cursor.getString(cursor.getColumnIndex("data"));
            cards.add(new Card(dbHeader));
        }
        cursor.close();
    }

}
