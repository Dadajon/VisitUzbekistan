package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.List;

public class SightsViewAdapter extends FirestoreRecyclerAdapter<Sights, SightsViewAdapter.SightsHolder> {
    Context mCtx;

    public SightsViewAdapter(@NonNull FirestoreRecyclerOptions<Sights> options, Context mCtx) {
        super(options);
        this.mCtx = mCtx;
    }

    @Override
    protected void onBindViewHolder(@NonNull SightsHolder holder, int position, @NonNull final Sights model) {
        Glide.with(mCtx)
                .load(model.getSights_bg())
                .into(holder.imageView);
        holder.imageView.setColorFilter(0xfff0f0f0, PorterDuff.Mode.MULTIPLY);
        holder.sightsTitle.setText(model.getSights_title());
        holder.sightsDesc.setText(model.getSights_desc());

        holder.sightsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, SightsInnerActivity.class);
                intent.putExtra("sights_bg", model.getSights_bg());
                intent.putExtra("sights_title", model.getSights_title());
                intent.putExtra("sights_desc", model.getSights_desc());
                intent.putExtra("sights_details", model.getSights_details());
                intent.putExtra("sights_full_desc", model.getSights_fullDesc());
                mCtx.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public SightsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_sights, viewGroup, false);
        return new SightsHolder(view);
    }

    class SightsHolder extends RecyclerView.ViewHolder{
        TextView sightsTitle;
        ImageView imageView;
        TextView sightsDesc;
        CardView sightsCardView;
        TextView sightsDetails;
        TextView sightsFullDesc;
        LinearLayout sightsLayout;

        public SightsHolder(@NonNull View itemView) {
            super(itemView);
            //cards
            sightsTitle = itemView.findViewById(R.id.sights_title);
            imageView = itemView.findViewById(R.id.sights_bg);
            sightsDesc = itemView.findViewById(R.id.sights_card_desc);
            sightsCardView = itemView.findViewById(R.id.sights_cardView);
//            sightsDetails = itemView.findViewById(R.id.sights_inner_details_text);
            sightsFullDesc = itemView.findViewById(R.id.expandable_text);
            sightsLayout = itemView.findViewById(R.id.sights_layout);
        }
    }
}
