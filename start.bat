@echo off
start "" javaw --module-path "C:\Programme\Java\javafx-sdk-21.0.11\lib" --add-modules javafx.controls,javafx.fxml -jar "%~dp0target\FileDeleter-1.0.0-SNAPSHOT.jar"
exit
