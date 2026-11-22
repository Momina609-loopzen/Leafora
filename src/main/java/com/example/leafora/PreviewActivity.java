package com.example.leafora;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PreviewActivity extends AppCompatActivity {

    ImageView selectedImage;
    Button btnAnalyze;
    String imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        selectedImage = findViewById(R.id.selectedImage);
        btnAnalyze = findViewById(R.id.btnAnalyze);

        imageUri = getIntent().getStringExtra("imageUri");

        if (imageUri != null) {
            selectedImage.setImageURI(Uri.parse(imageUri));
        }

        btnAnalyze.setOnClickListener(v -> {

            if (imageUri != null) {
                Intent intent = new Intent(
                        PreviewActivity.this,
                        ProcessingActivity.class);

                intent.putExtra("imageUri", imageUri);
                startActivity(intent);

            } else {
                Toast.makeText(this, "Please select an image",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}