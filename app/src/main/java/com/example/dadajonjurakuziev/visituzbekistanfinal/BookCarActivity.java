package com.example.dadajonjurakuziev.visituzbekistanfinal;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class BookCarActivity extends AppCompatActivity {
    private static final String TAG = "BookCarActivity";

    ProgressBar progressBar;
    ImageView rentcarLogo;
    WebView rentcarWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_car);

        progressBar = findViewById(R.id.progress_bar);
        rentcarLogo = findViewById(R.id.rentcaruz_logo);
        rentcarWebView = findViewById(R.id.rentcar_webView);

        progressBar.setMax(100);

        rentcarWebView.loadUrl("https://rentcar.uz/en/cars/16/0");
//        rentcarWebView.loadUrl("https://www.google.com");
        rentcarWebView.getSettings().setJavaScriptEnabled(true);
        rentcarWebView.setWebViewClient(new WebViewClient());
        rentcarWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
//                BookCarActivity.this.getSupportActionBar().setTitle(title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                rentcarLogo.setImageBitmap(icon);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (rentcarWebView.canGoBack()){
            rentcarWebView.goBack();
        }{
            finish();
        }
    }
}