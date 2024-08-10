package com.example.tictactoe2004;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Start MainActivity after a delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Start the next activity after 2000 milliseconds
            startMainActivity();
        }, 4000);

        // Find the LottieAnimationView using the correct ID
        LottieAnimationView lottieAnimationView = findViewById(R.id.splassss);
        lottieAnimationView.setAnimation(R.raw.splash); // Set the animation resource
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE); // Set repeat count
        lottieAnimationView.playAnimation(); // Start the animation
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, AddPlayers.class);
        startActivity(intent);
        finish();
    }
}
