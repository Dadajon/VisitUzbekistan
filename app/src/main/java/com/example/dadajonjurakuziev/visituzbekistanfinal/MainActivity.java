package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private LinearLayout NavGridBar;
    private ImageButton navButton;
    private SearchView searchView;
    private SearchView.SearchAutoComplete mSearchAutoComplete;
    private RelativeLayout top_bar_relative;
    private LinearLayout main_bg;
    private Button citiesBtn;
    private TextView title_uzb;
    private CardView cvHome, cvAccount, cvRentalCar, cvVideos, cvAboutUs;

    private static final int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavGridBar = findViewById(R.id.NavGridBar);
        searchView = findViewById(R.id.searchView);
        top_bar_relative = findViewById(R.id.tool_bar_layout);
        navButton = findViewById(R.id.navButton);
        main_bg = findViewById(R.id.main_bg);
        citiesBtn = findViewById(R.id.citiesBtn);
        title_uzb = findViewById(R.id.title_uzb);
        cvHome = findViewById(R.id.btnHome);
        cvAccount = findViewById(R.id.btnUserProfile);
        cvRentalCar = findViewById(R.id.btnRentalCar);
        cvVideos = findViewById(R.id.btnVideos);
        cvAboutUs = findViewById(R.id.btnAboutUs);


        /*Change color of text and hint text*/
        EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.white));
        searchEditText.setHintTextColor(getResources().getColor(R.color.white));
        /*Change the underline color*/
        View searchplate = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        searchplate.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        searchView.setMaxWidth(600);

        cvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked on Home", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        cvAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked on User Profile", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });
        cvRentalCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked on Rental Car", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, RentalCarsActivity.class);
                startActivity(intent);
            }
        });
        cvVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked on Videos", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, VideosActivity.class);
                startActivity(intent);
            }
        });
        cvAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked on About Us", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });
        if (isServiceOk()) {
            init();
        }
        citiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked on Cities", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, CitiesActivity.class);
                startActivity(intent);
            }
        });
        SearchList();
    }

    private void init() {
        CardView cvMap = findViewById(R.id.btnMap);
        cvMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked on Map", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainMapActivity.class);
                startActivity(intent);
            }
        });
    }

    boolean isVisible = true;

    public void navBarShowUp(View view) {
        if (isVisible) {
            NavGridBar.setVisibility(View.VISIBLE);
            NavGridBar.animate().alpha(1.0f).setDuration(100);
            top_bar_relative.setBackgroundColor(getResources().getColor(R.color.toolbar_color));
            top_bar_relative.animate().alpha(1.0f).setDuration(100);

            main_bg.getBackground().setColorFilter(0xff959595, PorterDuff.Mode.MULTIPLY);
            citiesBtn.getBackground().setColorFilter(0xff959595, PorterDuff.Mode.MULTIPLY);
            citiesBtn.setTextColor(0xff959595);
            title_uzb.setTextColor(0xff959595);
            isVisible = false;
        } else {
            NavGridBar.setVisibility(View.INVISIBLE);
            NavGridBar.animate().alpha(0.8f).setDuration(100);
            top_bar_relative.setBackgroundColor(Color.TRANSPARENT);
            top_bar_relative.animate().alpha(1.0f).setDuration(500);

            main_bg.getBackground().setColorFilter(0xffffffff, PorterDuff.Mode.MULTIPLY);
            citiesBtn.getBackground().setColorFilter(0xffffffff, PorterDuff.Mode.MULTIPLY);
            title_uzb.setTextColor(0xffffffff);
            citiesBtn.setTextColor(0xffffffff);
            isVisible = true;
        }
    }

    public boolean isServiceOk() {

        Log.d(TAG, "isServiceOk: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map reques
            Log.d(TAG, "isServiceOk: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "is ServiceOk: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You can`t make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @SuppressLint("RestrictedApi")
    public void SearchList() {
        ArrayList<String> arraySearch = new ArrayList<>();
        final ArrayAdapter<String> adapter;
        arraySearch.addAll(Arrays.asList(getResources().getStringArray(R.array.searchArray)));

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arraySearch);

        mSearchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        mSearchAutoComplete.setDropDownBackgroundResource(R.color.white);
        mSearchAutoComplete.setDropDownAnchor(R.id.searchView);
        mSearchAutoComplete.setThreshold(1);
        mSearchAutoComplete.setAdapter(adapter);

        final View dropDownAnchor = searchView.findViewById(mSearchAutoComplete.getDropDownAnchor());
        if (dropDownAnchor != null) {
            dropDownAnchor.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                           int oldLeft, int oldTop, int oldRight, int oldBottom) {

                    // calculate width of DropdownView
                    int point[] = new int[2];
                    dropDownAnchor.getLocationOnScreen(point);
                    // x coordinate of DropDownView
                    int dropDownPadding = point[0] + mSearchAutoComplete.getDropDownHorizontalOffset();

                    Rect screenSize = new Rect();
                    getWindowManager().getDefaultDisplay().getRectSize(screenSize);
                    // screen width
                    int screenWidth = screenSize.width();

                    // set DropDownView width
                    mSearchAutoComplete.setDropDownWidth(screenWidth - dropDownPadding * 2);
                    Log.e(TAG, "DropDownAnchor: " + mSearchAutoComplete.getDropDownAnchor());

                }
            });

            searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
                @Override
                public boolean onSuggestionSelect(int i) {

                    return false;
                }

                public boolean onSuggestionClick(int i) {
                    String selectedItem = (String) adapter.getItem(i);
                    Log.d(TAG, "onSuggestionClick: SearchView result: " + selectedItem);

                    Intent intent = new Intent(MainActivity.this, CitiesInnerActivity.class);
                    if (selectedItem.equals("Samarkand")) {
                        String background = "https://firebasestorage.googleapis.com/v0/b/visituzbekistan.appspot.com/o/samarkand.jpeg?alt=media&token=b7afd4ff-d85e-4c5c-a574-979c528682ae";
                        String title = "Samarkand";
                        String description = "No name is as evocative of the Silk Road as Samarkand (Samarqand). For most people it has the mythical resonance of Zanzibar or Timbuktu, fixed in the Western popular imagination by imaginative poets and playwrights, few of whom saw the city in the flesh.\\n\\n\" +\n" +
                                "                                \"On the ground the sublime, larger-than-life monuments of Timur (Tamerlane) and the city’s long, rich history still work some kind of magic. You can visit most of Samarkand’s high-profile attractions in two or three days. If you’re short on time, at least see the Registan, Gur-e-Amir, Bibi-Khanym Mosque and Shah-i-Zinda.\\n\\n\" +\n" +
                                "                                \"Away from these islands of majesty, Samarkand is a well-groomed modern city, with a large Russian town of broad avenues and parks. The recent walling off of parts of the old town and the pedestrianisation of Toshkent street has led to the 'Disneyfication' of some areas, but there's enough grandeur left to say that Samarkand remains a breathtaking place to visit.";
                        intent.putExtra("city_bg", background);
                        intent.putExtra("city_title", title);
                        intent.putExtra("city_desc", description);

                        startActivity(intent);
                    }
                    return true;
                }
            });

        }
    }
}
