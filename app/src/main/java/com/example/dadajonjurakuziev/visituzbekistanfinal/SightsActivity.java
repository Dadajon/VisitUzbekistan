package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class SightsActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference sightsRef;

    private SightsViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sights);

        getIncomingIntent();
        setUpRecyclerView();
    }

    private void getIncomingIntent() {
        final Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            final String cityTitle = bundle.getString("city_title");

            switch (cityTitle){
                case "Samarkand":
                    sightsRef = db.collection("Cities/Samarkand/TopSights");
                    break;
                case "Tashkent":
                    sightsRef = db.collection("Cities/Tashkent/TopSights");
                    break;

            }

            setImage(cityTitle);
        }
    }

    private void setImage(String cityTitle) {
        TextView desc = findViewById(R.id.sights_desc);
        desc.setText(cityTitle);
    }

    private void setUpRecyclerView() {
        Query query = sightsRef.orderBy("id", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Sights> options = new FirestoreRecyclerOptions.Builder<Sights>()
                .setQuery(query, Sights.class)
                .build();

        adapter = new SightsViewAdapter(options, this);
        RecyclerView recyclerView = findViewById(R.id.sightsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
