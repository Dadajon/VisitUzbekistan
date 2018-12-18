package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class CitiesViewAdapter extends FirestoreRecyclerAdapter<Cities, CitiesViewAdapter.CitiesHolder> {
    Context mCtx;
    public CitiesViewAdapter(@NonNull FirestoreRecyclerOptions<Cities> options, Context mCtx) {
        super(options);
        this.mCtx = mCtx;
    }

    @Override
    protected void onBindViewHolder(@NonNull CitiesHolder holder, int position, @NonNull final Cities model) {
        Glide.with(mCtx)
                .load(model.getCard_bg())
                .into(holder.cityImage);
        holder.cityTitle.setText(model.getCard_title());
        holder.pLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, CitiesInnerActivity.class);
                intent.putExtra("city_bg", model.getCard_bg());
                intent.putExtra("city_title", model.getCard_title());
                intent.putExtra("city_desc", model.getCity_desc());
                mCtx.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public CitiesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_cities_cardview, viewGroup, false);
        return new CitiesHolder(view);
    }

    class CitiesHolder extends RecyclerView.ViewHolder{
        ImageView cityImage;
        TextView cityTitle;
        RelativeLayout pLayout;

        public CitiesHolder(@NonNull View itemView) {
            super(itemView);
            cityImage = itemView.findViewById(R.id.card_bg);
            cityTitle = itemView.findViewById(R.id.card_city);
            pLayout = itemView.findViewById(R.id.pLayout);
        }
    }
}