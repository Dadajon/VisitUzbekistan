package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewReviewActivity extends AppCompatActivity {
    private EditText editTextTitle, editTextReviewBody;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String profile_photo, profile_name, profile_email;
    Uri profile_photoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_review);

        editTextTitle = findViewById(R.id.edit_text_review_title);
        editTextReviewBody = findViewById(R.id.edit_text_review_body);
        progressBar = findViewById(R.id.progress_bar);

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReview();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_review_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveReview();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveReview(){
        String title = editTextTitle.getText().toString();
        String review = editTextReviewBody.getText().toString();
        int rating = 5;

        if (title.trim().isEmpty()|| review.trim().isEmpty()){
            Toast.makeText(this, "Please insert a title and review", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (mAuth.getCurrentUser() != null) {
            for (UserInfo profile : user.getProviderData()) {
                profile_name = profile.getDisplayName();
                profile_email = profile.getEmail();
                profile_photoUrl = profile.getPhotoUrl();
            }
        }
        profile_photo = profile_photoUrl.toString();

        CollectionReference reviewRef = FirebaseFirestore.getInstance().collection("Cities/Samarkand/Reviews");
        reviewRef.add(new Review(profile_name, profile_photo, rating, title, review));
        Toast.makeText(this, "UPLOAD SUCCEEDED", Toast.LENGTH_SHORT).show();
        finish();
    }
}
