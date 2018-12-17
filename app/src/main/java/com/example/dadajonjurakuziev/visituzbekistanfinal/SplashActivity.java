package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";

    //vars
    Button sub;
    ImageView ballon;
    Animation fromBottom, fromTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started SPLASH ACTIVITY");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sub = findViewById(R.id.sub);
        ballon = findViewById(R.id.ballon);
        fromTop = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);

        ballon.setAnimation(fromTop);
        sub.setAnimation(fromBottom);

    }
}
