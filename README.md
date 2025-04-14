# 🚗 MatricApp

This is a native Android showcase app developed as part of a recruitment process. The app allows users to purchase digital highway vignettes for convenient and quick access to road usage permissions across the country.

## 📲 Features

- Purchase vignettes for **the whole country**:
  - Daily
  - Weekly
  - Monthly
  - Yearly
- Purchase **yearly** vignettes for **individual counties**
- Simple and clean UI for a smooth user experience
- Designed with scalability and maintainability in mind

---

## 🧪 Purpose

This application serves as a technical assignment for developer candidates. It demonstrates proficiency in Android development, architecture design, and clean code practices.

---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **Platform:** Native Android
- **UI Toolkit:** Jetpack Compose
- **Architecture:** MVVM (Model-View-ViewModel)
- **Asynchronous Programming:** Kotlin Coroutines with Flows
- **Dependency Injection:** Hilt
- **Networking:** Retrofit

---

## 🖥️ Backend Setup (Local Development)

During development and testing, the app connects to a locally running backend service.

### 🔧 Run the backend with Docker Compose

Make sure Docker is installed and running on your machine.

1. Navigate to the directory containing the `docker-compose.yml` file.
2. Run the following command:

   ```bash
   docker-compose up
   ```

3. The backend should now be running locally (e.g., at `http://localhost:8080`).

### 📱 Accessing the backend from a physical Android device

If you're testing on a physical device, you need to forward the port from your computer to your device using ADB:

```bash
adb reverse tcp:<PORT_NUMBER> tcp:<PORT_NUMBER>
```

> Replace `<PORT_NUMBER>` with the actual backend port, e.g., `8080`.

### ⚠️ Cleartext Traffic Notice

The backend uses `http` instead of `https`, so you must enable **cleartext traffic** in your Android project.  
Make sure your `AndroidManifest.xml` contains the following inside the `<application>` tag:

```xml
<application
    android:usesCleartextTraffic="true"
    ... >
```

---

## 📦 Installation

To build and run the app locally:

1. Clone the repository:

   ```bash
   git clone https://github.com/attilabacsaHifly/matric-app.git
   cd matric-app
   ```

2. Open the project in Android Studio.
3. Sync Gradle and run the app on an emulator or a physical device.
