package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class RentalcarsInnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentalcars_inner);

        //get data from DB
        getIncomingIntent();

        //button actions
        Button btnReadREviews = findViewById(R.id.read_reviews_btn);
        btnReadREviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        Button btnBookCar = findViewById(R.id.book_car_btn);
        btnBookCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RentalcarsInnerActivity.this, BookCarActivity.class);
                RentalcarsInnerActivity.this.startActivity(intent);
            }
        });
    }
    private void getIncomingIntent(){
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String carType = bundle.getString("car_type");
            String carTypeDesc = bundle.getString("car_type_desc");
            String carTypeDetails = bundle.getString("car_type_details");

            setImage(carType, carTypeDesc, carTypeDetails);
        }
    }

    private void setImage(String carType, String carTypeDesc, String carTypeDetails){
        ImageView car_type = findViewById(R.id.car_type);
        Glide.with(this)
                .load(carType)
                .into(car_type);

        ImageView car_type_desc = findViewById(R.id.car_type_desc);
        Glide.with(this)
                .load(carTypeDesc)
                .into(car_type_desc);

        ImageView car_type_details = findViewById(R.id.car_type_details);
        Glide.with(this)
                .load(carTypeDetails)
                .into(car_type_details);
    }
}
