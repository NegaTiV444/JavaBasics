package com.negativ.javabasics;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;



import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder>{

    private RecyclerView cardsRV;

    private List<Card> cards;
    private LayoutInflater mInflater;

    AdapterView.OnItemClickListener mItemClickListener;

    public RVAdapter(List<Card> Cards, Context context){
        this.cards = Cards;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.card, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, int i) {
        cardViewHolder.Header.setText(cards.get(i).getHeader());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{

        TextView Header;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            Header = itemView.findViewById(R.id.header);
        }
    }
}
