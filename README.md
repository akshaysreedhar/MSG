# MSF Training

MSF Training is an android application written in Java for learning purposes.

## Building the MSF Training App

First, checkout the repo:` https://subversion.assembla.com/svn/experimental/Akshay`

Building the app then depends on your build tools.

#### Android Studio (Recommended)

- Open Android Studio and select `File->Open...` and navigate to the root directory of your project.
- Select the directory or select the file `build.gradle` in the folder.
- Click `OK` to open the project in Android Studio.
- A Gradle sync should start, but you can force a sync and build the `app` module as needed.

#### Gradle (command line)

- Build the APK: `./gradlew build`

## Running the MSF Training App

Connect an Android device (either physical or virtual) to your development machine.

#### Android Studio (Recommended)

- Select `Run -> Run app` (or `Debug app`) from the menu bar
- Select the device you wish to run the app on and click `OK`

#### Gradle (command line)

- Install the debug APK on your device `./gradlew installDebug`
- Start the APK: `<path to Android SDK>/platform-tools/adb -d shell am start <path to Main activity>`

## Using the Sample App

The default login username and password are `akshay` and `123456` respectively. Some of the screens require the INTERNET in order to work.
