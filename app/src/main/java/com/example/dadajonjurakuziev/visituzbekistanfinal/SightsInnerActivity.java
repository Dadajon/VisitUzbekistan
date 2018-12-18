package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.ms.square.android.expandabletextview.ExpandableTextView;

public class SightsInnerActivity extends AppCompatActivity {
    ExpandableTextView expandableTextView;
    ImageButton imageButton;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference reviewRef = db.collection("Cities/Samarkand/Reviews");

    private ReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sights_inner);

        getIncomingIntent();
        setUpRecyclerView();

        FloatingActionButton reviewBtn = findViewById(R.id.add_sights_review_btn);
        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SightsInnerActivity.this, NewReviewActivity.class);
                SightsInnerActivity.this.startActivity(intent);
            }
        });
    }

    private void getIncomingIntent(){
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String sightsBG = bundle.getString("sights_bg");
            String sightsTitle = bundle.getString("sights_title");
            String sightsDesc = bundle.getString("sights_desc");
            String sightsDetails = bundle.getString("sights_details");
            String sightsFullDesc = bundle.getString("sights_full_desc");

            setImage(sightsBG, sightsTitle, sightsDesc, sightsDetails, sightsFullDesc);
        }
    }

    private void setImage(String sightsBG, String sightsTitle, String sightsDesc, String sightsDetails, String sightsFullDesc){
        ImageView sightsInnerImageView =  findViewById(R.id.sights_inner_image);
        Glide.with(this)
                .load(sightsBG)
                .into(sightsInnerImageView);

        TextView sightsInnerTitle = findViewById(R.id.sights_inner_title);
        sightsInnerTitle.setText(sightsTitle);

        TextView sightsInnerDesc = findViewById(R.id.sights_inner_desc);
        sightsInnerDesc.setText(sightsDesc);

        TextView sightsInnerDetails= findViewById(R.id.sights_inner_details_text);
        sightsInnerDetails.setText(sightsDetails);

        expandableTextView = findViewById(R.id.expandable_text_view);
        expandableTextView.setText(sightsFullDesc);

        imageButton = findViewById(R.id.expand_collapse);
        imageButton.setColorFilter(0xff78849E, PorterDuff.Mode.SRC_ATOP);
    }

    private void setUpRecyclerView() {
        Query query = reviewRef.orderBy("rating", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Review> options = new FirestoreRecyclerOptions.Builder<Review>()
                .setQuery(query, Review.class)
                .build();

        adapter = new ReviewAdapter(options, this);
        RecyclerView recyclerView = findViewById(R.id.sights_review_card_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
