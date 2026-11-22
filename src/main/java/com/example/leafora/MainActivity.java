package com.example.leafora;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    Button btnCamera, btnGallery;

    // Open gallery to select an image
    ActivityResultLauncher<String> galleryLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    uri -> {
                        if (uri != null) {
                            openPreview(uri.toString());
                        }
                    });

    // Open camera to capture a leaf image
    ActivityResultLauncher<Void> cameraLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.TakePicturePreview(),
                    bitmap -> {
                        if (bitmap != null) {
                            saveCameraImage(bitmap);
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCamera = findViewById(R.id.btnCamera);
        btnGallery = findViewById(R.id.btnGallery);

        btnCamera.setOnClickListener(v ->
                cameraLauncher.launch(null));

        btnGallery.setOnClickListener(v ->
                galleryLauncher.launch("image/*"));
    }

    // Save camera image temporarily
    private void saveCameraImage(Bitmap bitmap) {
        try {
            File file = new File(getCacheDir(), "leaf_image.jpg");

            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
            outputStream.close();

            openPreview(Uri.fromFile(file).toString());

        } catch (Exception e) {
            Toast.makeText(this, "Unable to save image",
                    Toast.LENGTH_SHORT).show();
        }
    }

    // Navigate to Preview Screen
    private void openPreview(String imageUri) {
        Intent intent = new Intent(MainActivity.this,
                PreviewActivity.class);

        intent.putExtra("imageUri", imageUri);
        startActivity(intent);
    }
}