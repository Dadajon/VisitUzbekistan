package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;


public class CitiesInnerActivity extends AppCompatActivity {
    ExpandableTextView expandableTextView;
    ImageButton imageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_inner_view);


        CardView restaurantsBtn = findViewById(R.id.restaurantsBtn);
        CardView mapBtn = findViewById(R.id.mapBtn);

        getIncomingIntent();

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
//                Intent intent = new Intent(CitiesActivity.this, MainMapActivity.class);
//                CitiesActivity.this.startActivity(intent);
            }
        });
    }

    private void getIncomingIntent(){
        final Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String  cityBg = bundle.getString("city_bg");
            final String cityTitle = bundle.getString("city_title");
            String cityDesc = bundle.getString("city_desc");

            setImage(cityBg, cityTitle, cityDesc);
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
}
