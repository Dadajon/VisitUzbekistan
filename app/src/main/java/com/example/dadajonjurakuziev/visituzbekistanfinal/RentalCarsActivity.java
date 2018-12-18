package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class RentalCarsActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference rentalCarRef = db.collection("Rental Cars");

    private RentalCarViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_cars);

        setUpRecyclerView();
        TextView rentalCarDetails = findViewById(R.id.details_text);
        rentalCarDetails.setText("Rent city : Tashkent\n" + "Hours : 24 hours\n"+ "Address : Amir Temur Street 15\n" + "Email : info@rentcar.uz\n" + "Tel : +998 97 155 05 55");
    }

    private void setUpRecyclerView() {
        Query query = rentalCarRef.orderBy("key", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<RentalCars> options = new FirestoreRecyclerOptions.Builder<RentalCars>()
                .setQuery(query, RentalCars.class)
                .build();

        adapter = new RentalCarViewAdapter(options, this);
        RecyclerView recyclerView = findViewById(R.id.rentalCarsRecyclerView);
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


