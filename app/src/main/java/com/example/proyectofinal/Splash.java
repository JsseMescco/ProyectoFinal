package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    private static final long SPLASH_SCREEN_DISPLAY = 3000;
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, Slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.botton_animation);

        image = findViewById(R.id.iv1);

        image.setAnimation(topAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( Splash.this, Login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN_DISPLAY);
    }
    }