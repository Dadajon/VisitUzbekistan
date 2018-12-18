package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewAdapter extends FirestoreRecyclerAdapter<Review, ReviewAdapter.ReviewHolder> {
    Context mCtx;
    public ReviewAdapter(@NonNull FirestoreRecyclerOptions<Review> options, Context mCtx) {
        super(options);
        this.mCtx = mCtx;
    }

    @Override
    protected void onBindViewHolder(@NonNull ReviewHolder holder, int position, @NonNull Review model) {
        holder.textViewTitle.setText(model.getTitle());
        holder.textViewReview.setText(model.getReview());
        holder.textViewRating.setText(String.valueOf(model.getRating()));

        Glide.with(mCtx)
                .load(model.getProfilePhotoUrl())
                .into(holder.profileImage);
        holder.profileName.setText(model.getProfileName());
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_review_card, viewGroup, false);
        return new ReviewHolder(view);
    }

    class ReviewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewReview, textViewRating, profileName;
        CircleImageView profileImage;

        public ReviewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewRating = itemView.findViewById(R.id.text_view_rating);
            textViewReview = itemView.findViewById(R.id.text_view_review);

            profileImage = itemView.findViewById(R.id.profile_image);
            profileName = itemView.findViewById(R.id.profile_name);
        }
    }
}
