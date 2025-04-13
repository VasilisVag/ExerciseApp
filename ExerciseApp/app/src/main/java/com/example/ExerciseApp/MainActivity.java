package com.example.ExerciseApp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.WriterException;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.mlkit.vision.barcode.Barcode;


import android.Manifest;
import android.content.pm.PackageManager;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 100;
    private ImageView qrImageView;
    private Button btnGenerateQRCode;
    private Button btnScanQRCode;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrImageView = findViewById(R.id.qrImageView);
        btnGenerateQRCode = findViewById(R.id.btnGenerateQRCode);
        btnScanQRCode = findViewById(R.id.btnScanQRCode);
        txtResult = findViewById(R.id.txtResult);

        // Δημιουργία QR Code για το πρόγραμμα γυμναστικής
        String gymProgram = "Πρόγραμμα Γυμναστικής:\n" +
                "1. Προθέρμανση - 10 λεπτά\n" +
                "2. Push-ups - 3x15\n" +
                "3. Squats - 3x20\n" +
                "4. Τρέξιμο - 20 λεπτά\n" +
                "5. Διατάσεις - 5 λεπτά";

        // Πατώντας το κουμπί για δημιουργία QR Code
        btnGenerateQRCode.setOnClickListener(v -> generateQRCode(gymProgram));

        // Πατώντας το κουμπί για σάρωση QR Code
        btnScanQRCode.setOnClickListener(v -> startQRScanner());

        // Ζητήστε άδειες
        requestPermissions();
    }

    private void generateQRCode(String text) {
        try {
            // Δημιουργία QR Code με ZXing
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500);
            Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.RGB_565);

            for (int x = 0; x < 500; x++) {
                for (int y = 0; y < 500; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            qrImageView.setImageBitmap(bitmap); // Εμφανίζει το QR code στην οθόνη
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private void startQRScanner() {
        // Ξεκινάει την κάμερα για σάρωση QR Code
        Intent intent = new Intent(MainActivity.this, ScannerActivity.class);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            // Αν σκανάρεται ένα QR Code
            String qrResult = data.getStringExtra("result");
            if (qrResult != null) {
                txtResult.setText("Σαρωμένο QR Code: " + qrResult);
            } else {
                txtResult.setText("Δεν βρέθηκε QR Code");
            }
        }
    }

    //  άδειες για την κάμερα
    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        }
    }

    // Διαχείριση των αποτελεσμάτων των αδειών
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Άδεια κάμερας παραχωρήθηκε!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Άδεια κάμερας αρνήθηκε!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
