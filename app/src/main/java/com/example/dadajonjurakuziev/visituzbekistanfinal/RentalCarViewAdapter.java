package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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

public class RentalCarViewAdapter extends FirestoreRecyclerAdapter<RentalCars, RentalCarViewAdapter.RentalCarHolder> {
    Context mCtx;
    public RentalCarViewAdapter(@NonNull FirestoreRecyclerOptions<RentalCars> options, Context mCtx) {
        super(options);
        this.mCtx = mCtx;
    }

    @Override
    protected void onBindViewHolder(@NonNull RentalCarHolder holder, int position, @NonNull final RentalCars model) {
        Glide.with(mCtx)
                .load(model.getRentalcar_image())
                .into(holder.rentalCarImageView);
        holder.rentalCarTextView.setText(model.getRentalcar_title());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, RentalcarsInnerActivity.class);
                intent.putExtra("car_type", model.getCar_type());
                intent.putExtra("car_type_desc", model.getCar_type_desc());
                intent.putExtra("car_type_details", model.getCar_type_details());
                mCtx.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public RentalCarHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_rental_cars, viewGroup, false);
        return new RentalCarHolder(view);
    }

    class RentalCarHolder extends RecyclerView.ViewHolder{
        TextView rentalCarTextView;
        ImageView rentalCarImageView;
        RelativeLayout relativeLayout;

        public RentalCarHolder(@NonNull View itemView) {
            super(itemView);
            //cards
            rentalCarImageView = itemView.findViewById(R.id.rental_car_card_bg);
            rentalCarTextView = itemView.findViewById(R.id.rental_car_card_title);
            relativeLayout = itemView.findViewById(R.id.rentalCardViewLayout);
        }
    }
}
