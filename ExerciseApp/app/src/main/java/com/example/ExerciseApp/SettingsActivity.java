package com.example.ExerciseApp;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Switch;
import android.widget.Spinner;
import android.widget.RadioButton;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchNotifications;
    private Spinner languageSpinner;
    private RadioButton radioLight;
    private RadioButton radioDark;
    private Button btnSaveSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);  // Διασύνδεση με το αρχείο XML

        // Αντιστοιχία των στοιχείων της διάταξης με τα στοιχεία της Activity
        switchNotifications = findViewById(R.id.switchNotifications);
        languageSpinner = findViewById(R.id.languageSpinner);
        radioLight = findViewById(R.id.radioLight);
        radioDark = findViewById(R.id.radioDark);
        btnSaveSettings = findViewById(R.id.btnSaveSettings);

        // Προσθήκη click listener για το κουμπί αποθήκευσης
        btnSaveSettings.setOnClickListener(v -> {
            // Απλά για παράδειγμα: Αν αποθηκευτούν οι ρυθμίσεις, κάνε ένα Toast
            String theme = radioLight.isChecked() ? "Light" : "Dark";
            String language = languageSpinner.getSelectedItem().toString();
            boolean notificationsEnabled = switchNotifications.isChecked();

            Toast.makeText(this, "Settings saved!\nTheme: " + theme + "\nLanguage: " + language +
                    "\nNotifications: " + notificationsEnabled, Toast.LENGTH_SHORT).show();
        });
    }
}