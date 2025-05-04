package com.example.ExerciseApp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exerciseapp.R;

public class MainActivity extends AppCompatActivity {

    Button btnStartWorkout, btnSettings, btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Αυτό το XML πρέπει να είναι το δικό σου νέο layout

        // Συνδέουμε τα κουμπιά
        btnStartWorkout = findViewById(R.id.btnStartWorkout);
        btnSettings = findViewById(R.id.btnSettings);
        btnProfile = findViewById(R.id.btnProfile);

        // Κουμπί για Start Workout (Μπορεί να ανοίξει π.χ. μια νέα Activity)
        btnStartWorkout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WorkoutActivity.class);
            startActivity(intent);
        });

        // Κουμπί για Settings
        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        // Κουμπί για Profile
        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
    }
}
