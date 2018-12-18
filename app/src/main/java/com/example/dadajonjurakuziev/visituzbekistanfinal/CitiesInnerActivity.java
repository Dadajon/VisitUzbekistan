package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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

import java.util.ArrayList;
import java.util.List;

public class CitiesInnerActivity extends AppCompatActivity {
    ExpandableTextView expandableTextView;
    ImageButton imageButton;

    private FirebaseFirestore db;
    private CollectionReference reviewRef;
    private ReviewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_inner_view);


        CardView restaurantsBtn = findViewById(R.id.restaurantsBtn);
        CardView mapBtn = findViewById(R.id.mapBtn);

        getIncomingIntent();

        FloatingActionButton btnAddReview = findViewById(R.id.btn_add_review);
        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CitiesInnerActivity.this, NewReviewActivity.class);
                CitiesInnerActivity.this.startActivity(intent);
            }
        });

        restaurantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CitiesInnerActivity.this, RestaurantsActivity.class);
                CitiesInnerActivity.this.startActivity(intent);
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CitiesInnerActivity.this, MainMapActivity.class);
                CitiesInnerActivity.this.startActivity(intent);
            }
        });
        setUpRecyclerView();
    }

    private void getIncomingIntent(){
        final Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String  cityBg = bundle.getString("city_bg");
            final String cityTitle = bundle.getString("city_title");
            String cityDesc = bundle.getString("city_desc");

            setImage(cityBg, cityTitle, cityDesc);

            switch (cityTitle){
                case "Tashkent":
                    db = FirebaseFirestore.getInstance();
//                    reviewRef = db.collection("Cities/Tashkent/Reviews");
                    reviewRef = db.collection("Cities/Samarkand/Reviews");
                    break;
                case "Bukhara":
                    db = FirebaseFirestore.getInstance();
//                    reviewRef = db.collection("Cities/Bukhara/Reviews");
                    reviewRef = db.collection("Cities/Samarkand/Reviews");
                    break;
                case "Samarkand":
                    db = FirebaseFirestore.getInstance();
                    reviewRef = db.collection("Cities/Samarkand/Reviews");
                    break;
                case "Khiva":
                    db = FirebaseFirestore.getInstance();
//                    reviewRef = db.collection("Cities/Khiva/Reviews");
                    reviewRef = db.collection("Cities/Samarkand/Reviews");
                    break;
                case "Nukus":
                    db = FirebaseFirestore.getInstance();
//                    reviewRef = db.collection("Cities/Nukus/Reviews");
                    reviewRef = db.collection("Cities/Samarkand/Reviews");
                    break;
                case "Kokand":
                    db = FirebaseFirestore.getInstance();
//                    reviewRef = db.collection("Cities/Kokand/Reviews");
                    reviewRef = db.collection("Cities/Samarkand/Reviews");
                    break;
                case "Fergana":
                    db = FirebaseFirestore.getInstance();
//                    reviewRef = db.collection("Cities/Fergana/Reviews");
                    reviewRef = db.collection("Cities/Samarkand/Reviews");
                    break;
                case "Termiz":
                    db = FirebaseFirestore.getInstance();
//                    reviewRef = db.collection("Cities/Termiz/Reviews");
                    reviewRef = db.collection("Cities/Samarkand/Reviews");
                    break;
                case "Andijan":
                    db = FirebaseFirestore.getInstance();
//                    reviewRef = db.collection("Cities/Andijan/Reviews");
                    reviewRef = db.collection("Cities/Samarkand/Reviews");
                    break;
                case "Shakhrisabz":
                    db = FirebaseFirestore.getInstance();
//                    reviewRef = db.collection("Cities/Shakhrisabz/Reviews");
                    reviewRef = db.collection("Cities/Samarkand/Reviews");
                    break;
            }

            CardView sightsBtn = findViewById(R.id.sightsBtn);
            sightsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CitiesInnerActivity.this, SightsActivity.class);
                    intent.putExtra("city_title", cityTitle);
                    CitiesInnerActivity.this.startActivity(intent);
                }
            });
        }
    }

    private void setImage(String city_Bg, String cityTitle, String cityDesc){

        ImageView cBG = findViewById(R.id.city_image);
        Glide.with(CitiesInnerActivity.this)
                .load(city_Bg)
                .into(cBG);
        cBG.setColorFilter(0xffAEAEAE, PorterDuff.Mode.MULTIPLY);


        TextView cTitle = findViewById(R.id.city_title);
        cTitle.setText(cityTitle);

        expandableTextView = findViewById(R.id.expandable_text_view);
        expandableTextView.setText(cityDesc);

        imageButton = findViewById(R.id.expand_collapse);
        imageButton.setColorFilter(0xff78849E, PorterDuff.Mode.SRC_ATOP);
    }

    private void setUpRecyclerView() {
        Query query = reviewRef.orderBy("rating", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Review> options = new FirestoreRecyclerOptions.Builder<Review>()
                .setQuery(query, Review.class)
                .build();

        adapter = new ReviewAdapter(options, this);
        RecyclerView recyclerView = findViewById(R.id.cities_review_card_view);
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
