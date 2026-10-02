# EventFlow — Android Event Scheduling & Agenda App

EventFlow is a modern native Android application built with **Kotlin**, **Jetpack Compose (Material 3)**, and **Room Database**. It provides an intuitive, high-performance interface for planning, organizing, and tracking events, exams, lectures, meetings, and personal tasks.

---

## 🌟 Key Features

1. **Interactive Date Carousel**:
   - Smooth horizontal date strip allowing one-tap switching between days.
   - Real-time indicator dots displaying days with scheduled events.
   - Quick "Jump to Today" shortcut button.

2. **Categorization & Priority Tags**:
   - Built-in categories: *Study & Academic*, *Exam & Deadlines*, *Work & Projects*, *Personal Life*, *Social & Gatherings*, and *General*.
   - Priority indicators (*High*, *Medium*, *Low*) with distinctive visual badges.

3. **Smart Scheduling & Reminders**:
   - Precise start and end time management with duration calculation.
   - Push notifications scheduled via Android's `AlarmManager` and `BroadcastReceiver`.
   - Customizable notification offsets (5m, 15m, 30m, 1h, 1d before event).

4. **Multi-Factor Search & Filtering**:
   - Instant search across event titles, descriptions, and locations.
   - Filter chips to view by status (*All*, *Upcoming*, *Completed*) or specific category.

5. **Local Offline-First Storage**:
   - Backed by Android Jetpack Room (SQLite) with type converters for modern Java 8+ `java.time` (`LocalDate`, `LocalTime`).
   - Reactive UI updates powered by Kotlin Coroutine `Flow` and MVVM architecture.

---

## 🛠 Tech Stack & Architecture

- **UI**: Jetpack Compose + Material Design 3 (Dynamic Color, Light/Dark theme support)
- **Architecture**: MVVM (Model-View-ViewModel) + Repository Pattern
- **Persistence**: Room Database (Entity, DAO, TypeConverters)
- **Concurrency**: Kotlin Coroutines & StateFlow / Flow
- **Notifications**: NotificationManager, NotificationCompat, AlarmManager
- **Build System**: Gradle Kotlin DSL (`build.gradle.kts`) with Version Catalog (`libs.versions.toml`)

---

## 📂 Project Structure

```
EventFlow/
├── app/
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/alwin/eventflow/
│       │   ├── EventFlowApplication.kt
│       │   ├── MainActivity.kt
│       │   ├── data/
│       │   │   ├── local/
│       │   │   │   ├── Converters.kt
│       │   │   │   ├── EventDao.kt
│       │   │   │   ├── EventDatabase.kt
│       │   │   │   └── EventEntity.kt
│       │   │   ├── model/
│       │   │   │   └── Event.kt
│       │   │   └── repository/
│       │   │       └── EventRepository.kt
│       │   ├── notification/
│       │   │   ├── AlarmReceiver.kt
│       │   │   └── NotificationHelper.kt
│       │   └── ui/
│       │       ├── components/
│       │       │   ├── DateSelectorRow.kt
│       │       │   └── EventCard.kt
│       │       ├── screens/
│       │       │   ├── AddEditEventDialog.kt
│       │       │   └── HomeScreen.kt
│       │       ├── theme/
│       │       │   ├── Color.kt
│       │       │   ├── Theme.kt
│       │       │   └── Type.kt
│       │       └── viewmodel/
│       │           └── EventViewModel.kt
│       └── res/
│           └── values/
│               ├── colors.xml
│               ├── strings.xml
│               └── themes.xml
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/
│       └── gradle-wrapper.properties
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── README.md
```

---

## 🚀 How to Run the App

### Option A: Using Android Studio (Recommended)

1. Open **Android Studio** (Hedgehog, Iguana, Koala, or Ladybug recommended).
2. Select **File > Open...** and navigate to the extracted `EventFlow` folder.
3. Wait for Gradle to finish syncing the dependencies.
4. Select an Android device or emulator running **Android 8.0 (API 26) or higher** (Android 14/15 recommended).
5. Click the green **Run (▶)** button or press `Shift + F10`.

### Option B: Building via Terminal

From the root of the project directory:

```bash
# On Linux / macOS:
./gradlew assembleDebug

# On Windows:
gradlew.bat assembleDebug
```

The compiled APK will be generated at:
`app/build/outputs/apk/debug/app-debug.apk`

---

## 🔒 Permissions Used

- `android.permission.POST_NOTIFICATIONS`: Enables notification delivery on Android 13+ (API 33+).
- `android.permission.SCHEDULE_EXACT_ALARM`: Ensures event reminders trigger precisely at the scheduled time.
- `android.permission.VIBRATE`: Provides haptic feedback when alerts fire.
