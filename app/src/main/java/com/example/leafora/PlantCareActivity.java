package com.example.leafora;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlantCareActivity extends AppCompatActivity {

    TextView txtCarePlant, txtCareDisease, txtCareTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_care);

        txtCarePlant = findViewById(R.id.txtCarePlant);
        txtCareDisease = findViewById(R.id.txtCareDisease);
        txtCareTips = findViewById(R.id.txtCareTips);

        String diseaseName = getIntent().getStringExtra("diseaseName");

        if (diseaseName == null || diseaseName.isEmpty()) {
            diseaseName = "Unknown Disease";
        }

        String plantName = "Unknown Plant";
        String disease = diseaseName;

        // Separate plant name and disease name
        if (diseaseName.contains("___")) {
            String[] parts = diseaseName.split("___", 2);

            plantName = parts[0].replace("_", " ");
            disease = parts[1].replace("_", " ");
        }

        txtCarePlant.setText(plantName);
        txtCareDisease.setText(disease);

        String lowerDisease = disease.toLowerCase();

        // Healthy plant advice
        if (lowerDisease.contains("healthy")) {

            txtCareTips.setText(
                    "Your plant looks healthy!\n\n" +
                            "• Water your plant regularly.\n\n" +
                            "• Provide sufficient sunlight.\n\n" +
                            "• Use suitable soil and nutrients.\n\n" +
                            "• Check leaves regularly for unusual changes.\n\n" +
                            "• Keep your plant clean and well-maintained."
            );

        }

        // Leaf Scorch advice
        else if (lowerDisease.contains("scorch")) {

            txtCareTips.setText(
                    "Recommended Care Tips:\n\n" +
                            "• Remove severely affected leaves carefully.\n\n" +
                            "• Maintain consistent soil moisture.\n\n" +
                            "• Avoid excessive fertilizer application.\n\n" +
                            "• Water near the soil instead of wetting leaves.\n\n" +
                            "• Monitor the plant for further symptoms."
            );

        }

        // Fungal disease advice
        else if (lowerDisease.contains("blight") ||
                lowerDisease.contains("mildew") ||
                lowerDisease.contains("mold") ||
                lowerDisease.contains("scab") ||
                lowerDisease.contains("rust") ||
                lowerDisease.contains("rot") ||
                lowerDisease.contains("esca")) {

            txtCareTips.setText(
                    "Recommended Care Tips:\n\n" +
                            "• Remove severely infected plant parts.\n\n" +
                            "• Avoid watering directly over leaves.\n\n" +
                            "• Provide proper spacing and airflow.\n\n" +
                            "• Avoid leaving infected plant debris around.\n\n" +
                            "• Monitor the plant regularly."
            );

        }

        // Bacterial disease advice
        else if (lowerDisease.contains("bacterial") ||
                lowerDisease.contains("spot") ||
                lowerDisease.contains("canker")) {

            txtCareTips.setText(
                    "Recommended Care Tips:\n\n" +
                            "• Remove severely infected leaves.\n\n" +
                            "• Avoid handling wet plants.\n\n" +
                            "• Clean gardening tools regularly.\n\n" +
                            "• Avoid splashing water between plants.\n\n" +
                            "• Seek locally recommended treatment if symptoms spread."
            );

        }

        // General disease advice
        else {

            txtCareTips.setText(
                    "Recommended Care Tips:\n\n" +
                            "• Remove severely damaged leaves.\n\n" +
                            "• Maintain proper watering practices.\n\n" +
                            "• Provide suitable sunlight and airflow.\n\n" +
                            "• Keep gardening tools clean.\n\n" +
                            "• Monitor your plant and seek expert advice for treatment."
            );
        }
    }
}