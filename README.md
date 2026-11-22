# 🌿 Leafora
### Because Every Leaf Matters.

## 1. Introduction

Leafora is an AI-powered Android application developed to detect plant diseases through images of plant leaves. The application uses Deep Learning and a Convolutional Neural Network (CNN) model to classify plant leaves into healthy or diseased categories.

The main goal of Leafora is to make plant disease identification easier, faster, and more accessible. Users can select a leaf image from their phone gallery and receive a prediction along with a confidence percentage.

Leafora performs disease detection directly on the Android device using TensorFlow Lite, allowing the application to work offline without requiring an internet connection for predictions.

## 2. Objectives

- Develop an AI-based mobile application for plant disease detection.
- Apply CNN-based image classification.
- Integrate a trained Deep Learning model into an Android application.
- Perform real-time image prediction on mobile devices.
- Provide an easy-to-use interface for plant disease identification.
- Support offline AI inference using TensorFlow Lite.

## 3. Main Features

- **Splash Screen:** Introduces the Leafora application.
- **Image Selection:** Allows users to select leaf images from the phone gallery.
- **Image Preview:** Displays the selected image before analysis.
- **AI-Based Detection:** Analyzes leaf images using a CNN model.
- **Disease Classification:** Identifies the predicted plant disease or healthy condition.
- **Confidence Score:** Displays the model's prediction confidence percentage.
- **Plant Care Tips:** Provides plant care information.
- **Offline Detection:** Performs predictions directly on the device without internet access.

## 4. Technologies and Tools Used

| Technology | Purpose |
|---|---|
| Java | Android application development |
| XML | User interface design |
| Android Studio | Development environment |
| Python | Model training |
| TensorFlow | Deep Learning model development |
| CNN | Image classification architecture |
| TensorFlow Lite | Mobile model deployment |
| Google Colab | Model training environment |
| Kaggle | Dataset source |

## 5. Deep Learning Model – CNN

Leafora uses a Convolutional Neural Network (CNN), a Deep Learning architecture commonly used for image recognition and classification tasks.

CNN automatically learns important visual features from images, such as:
- Leaf shapes
- Patterns and textures
- Spots and lesions
- Color variations
- Disease-related visual symptoms

The trained CNN model analyzes the input leaf image and predicts its corresponding class.

### Model Training
- **Model Type:** Convolutional Neural Network (CNN)
- **Framework:** TensorFlow
- **Dataset:** New Plant Diseases Dataset (Augmented)
- **Number of Classes:** 38
- **Training Environment:** Google Colab
- **Deployment Format:** TensorFlow Lite (.tflite)

The model was trained and evaluated using training and validation datasets. After training, it was converted into TensorFlow Lite format for integration into the Android application.

## 6. TensorFlow Lite Integration

TensorFlow Lite is used to deploy the trained CNN model on Android devices.

The model and its class labels are stored in the application's assets folder.

**Model Files:**
- `leafora_model.tflite` – Trained CNN model.
- `leafora_labels.json` – Class labels used to interpret predictions.

TensorFlow Lite Interpreter loads the model and performs inference on the selected leaf image.

This approach provides:
- Offline prediction
- Reduced network dependency
- Faster on-device inference
- Efficient mobile deployment

## 7. Application Workflow

1. The user launches Leafora.
2. The home screen provides access to image selection.
3. The user selects a plant leaf image from the gallery.
4. The selected image is displayed on the preview screen.
5. The image is prepared for model input.
6. The TensorFlow Lite Interpreter processes the image using the trained CNN model.
7. The model generates prediction scores for the supported classes.
8. The application identifies the predicted class.
9. The result screen displays the plant name, disease name, and confidence percentage.

## 8. Project Structure

```text
Leafora/
│
├── app/
│   └── src/
│       └── main/
│           ├── assets/
│           │   ├── leafora_model.tflite
│           │   └── leafora_labels.json
│           │
│           ├── java/
│           │   └── com/example/leafora/
│           │       ├── MainActivity.java
│           │       ├── SplashActivity.java
│           │       ├── PreviewActivity.java
│           │       ├── ProcessingActivity.java
│           │       ├── ResultsActivity.java
│           │       └── PlantCareActivity.java
│           │
│           ├── res/
│           │   ├── drawable/
│           │   ├── layout/
│           │   └── values/
│           │
│           └── AndroidManifest.xml
│
├── gradle/
├── build.gradle
├── settings.gradle
└── README.md
```

## 9. Dataset

The model was trained using the **New Plant Diseases Dataset (Augmented)** available on Kaggle.

Dataset Link:
https://www.kaggle.com/datasets/vipoooool/new-plant-diseases-dataset

The dataset contains images of healthy and diseased plant leaves across 38 different classes.

## 10. Installation and Execution

1. Clone or download this repository.
2. Open the project in Android Studio.
3. Allow Gradle synchronization to complete.
4. Connect an Android device or start an emulator.
5. Build and run the application.

## 11. Future Enhancements

- Add camera-based image capture.
- Improve prediction accuracy with further model training.
- Provide detailed disease treatment recommendations.
- Support additional plant species.
- Improve image analysis under different lighting conditions.

## 12. Conclusion

Leafora demonstrates the integration of Deep Learning and mobile application development. By combining a CNN-based plant disease classification model with TensorFlow Lite, the application provides an offline solution for identifying plant diseases through leaf images.

The project highlights how AI can be deployed on Android devices to solve practical agricultural problems.

---

**Project Name:** Leafora  
**Tagline:** Because Every Leaf Matters.  
**Platform:** Android  
**Programming Language:** Java  
**Model Architecture:** CNN  
**Deployment Framework:** TensorFlow Lite  
**Course:** Mobile Application Development
