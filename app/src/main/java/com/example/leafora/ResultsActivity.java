package com.example.leafora;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ResultsActivity extends AppCompatActivity {

    ImageView resultImage;
    TextView txtDiseaseName, txtPlantStatus, txtConfidence;
    Button btnPlantCare;

    String diseaseName;
    String imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        resultImage = findViewById(R.id.resultImage);
        txtDiseaseName = findViewById(R.id.txtDiseaseName);
        txtPlantStatus = findViewById(R.id.txtPlantStatus);
        txtConfidence = findViewById(R.id.txtConfidence);
        btnPlantCare = findViewById(R.id.btnPlantCare);

        imageUri = getIntent().getStringExtra("imageUri");

        if (imageUri != null) {
            resultImage.setImageURI(Uri.parse(imageUri));
        }

        diseaseName = getIntent().getStringExtra("diseaseName");

        double confidence = getIntent().getDoubleExtra(
                "confidence", -1
        );

        if (diseaseName != null) {

            String formattedName = diseaseName
                    .replace("___", " - ")
                    .replace("_", " ");

            txtDiseaseName.setText(formattedName);

            if (diseaseName.toLowerCase().endsWith("healthy")) {
                txtPlantStatus.setText("Healthy Plant");
            } else {
                txtPlantStatus.setText("Disease Detected");
            }

            if (confidence >= 0) {

                if (confidence <= 1) {
                    confidence = confidence * 100;
                }

                txtConfidence.setText(
                        String.format(
                                Locale.getDefault(),
                                "Confidence: %.2f%%",
                                confidence
                        )
                );
            }
        }

        btnPlantCare.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ResultsActivity.this,
                    PlantCareActivity.class
            );

            intent.putExtra("diseaseName", diseaseName);
            intent.putExtra("imageUri", imageUri);

            startActivity(intent);
        });
    }
}