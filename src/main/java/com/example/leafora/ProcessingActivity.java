package com.example.leafora;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.Interpreter;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProcessingActivity extends AppCompatActivity {

    private Interpreter tflite;
    private String[] labels;
    private TextView txtStatus;

    private final ExecutorService executor =
            Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);

        txtStatus = findViewById(R.id.txtStatus);

        String imageUri = getIntent().getStringExtra("imageUri");

        if (imageUri == null) {
            showError("No image received!");
            return;
        }

        executor.execute(() -> {
            try {
                loadModel();
                loadLabels();

                Bitmap bitmap = loadImage(Uri.parse(imageUri));

                if (bitmap == null) {
                    throw new Exception("Unable to load image.");
                }

                float[][][][] input = preprocessImage(bitmap);

                float[][] output = new float[1][38];

                tflite.run(input, output);

                int bestIndex = 0;

                for (int i = 1; i < 38; i++) {
                    if (output[0][i] > output[0][bestIndex]) {
                        bestIndex = i;
                    }
                }

                String diseaseName = labels[bestIndex];
                double confidence = output[0][bestIndex];

                runOnUiThread(() -> {

                    android.content.Intent intent =
                            new android.content.Intent(
                                    ProcessingActivity.this,
                                    ResultsActivity.class
                            );

                    intent.putExtra("imageUri", imageUri);
                    intent.putExtra("diseaseName", diseaseName);
                    intent.putExtra("confidence", confidence);

                    startActivity(intent);
                    finish();
                });

            } catch (Exception e) {
                e.printStackTrace();
                showError("Analysis failed: " + e.getMessage());
            }
        });
    }

    private void loadModel() throws Exception {

        try (android.content.res.AssetFileDescriptor descriptor =
                     getAssets().openFd("leafora_model.tflite");
             FileInputStream stream =
                     new FileInputStream(descriptor.getFileDescriptor())) {

            FileChannel channel = stream.getChannel();

            MappedByteBuffer modelBuffer = channel.map(
                    FileChannel.MapMode.READ_ONLY,
                    descriptor.getStartOffset(),
                    descriptor.getDeclaredLength()
            );

            tflite = new Interpreter(modelBuffer);
        }
    }

    private void loadLabels() throws Exception {

        StringBuilder json = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        getAssets().open("leafora_labels.json")
                ))) {

            String line;

            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        }

        JSONArray array = new JSONArray(json.toString());

        labels = new String[array.length()];

        for (int i = 0; i < array.length(); i++) {
            labels[i] = array.getString(i);
        }

        if (labels.length != 38) {
            throw new Exception("Incorrect number of labels!");
        }
    }

    private Bitmap loadImage(Uri uri) throws Exception {

        try (InputStream stream =
                     getContentResolver().openInputStream(uri)) {

            if (stream == null) {
                throw new Exception("Cannot open image.");
            }

            return BitmapFactory.decodeStream(stream);
        }
    }

    private float[][][][] preprocessImage(Bitmap original) {

        Bitmap resized = Bitmap.createScaledBitmap(
                original, 224, 224, true
        );

        float[][][][] input = new float[1][224][224][3];

        for (int y = 0; y < 224; y++) {
            for (int x = 0; x < 224; x++) {

                int pixel = resized.getPixel(x, y);

                input[0][y][x][0] = (pixel >> 16) & 0xFF;
                input[0][y][x][1] = (pixel >> 8) & 0xFF;
                input[0][y][x][2] = pixel & 0xFF;
            }
        }

        return input;
    }

    private void showError(String message) {

        runOnUiThread(() -> {

            Toast.makeText(
                    ProcessingActivity.this,
                    message,
                    Toast.LENGTH_LONG
            ).show();

            txtStatus.setText("Analysis failed. Please try again.");
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        executor.shutdown();

        if (tflite != null) {
            tflite.close();
        }
    }
}