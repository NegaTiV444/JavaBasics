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
    private ItemClickListener mClickListener;

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

    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView Header;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            Header = itemView.findViewById(R.id.header);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public String getItemHeader(int id) {
        return cards.get(id).getHeader();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
