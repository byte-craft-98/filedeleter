
# FileDeleter

## Description
The FileDeleter Tool is an application designed to delete files from multiple folders simultaneously. It is particularly useful for clearing application-generated files and cache folders from domains on application servers like GlassFish.

## Features

The FileDeleter Tool GUI allows the user to select directories that need to be cleaned up and delete files from those directories. The GUI provides the following functionalities:

- Displaying the selected directories in a tree view window.
- Enabling or disabling the cleanup of directories by checking or unchecking the corresponding checkboxes.
- Adding new directories to be cleaned up.
- Removing directories from the list of directories to be cleaned up.
- Deleting files from the selected directories.


# Configuration File

A `config.txt` file is stored next to the program.

Example:

```txt
false,H:\test1\test11
true,H:\test1\test12
true,H:\test1\test13\test131
true,H:\test1\test14\test141
```

## Format

```txt
<enabled>,<directory-path>
```

- `true` → directory is checked/selected in the UI
- `false` → directory is unchecked in the UI

---

# Application Behavior

When the application starts:

- All folders from `config.txt` are loaded automatically
- The application builds a tree structure from the folder paths
- Checked state is restored from the configuration
- Non-existing folders are displayed with a yellow warning symbol

Example:

- `true,H:\test1\test12`
  → folder is selected in the UI

- `false,H:\test1\test11`
  → folder is not selected

If a configured path does not exist anymore, the application still displays it with a warning icon ⚠.

---

# Buttons

## Add Directory

Add a new folder manually to the configuration and tree.

## Remove Directory

Remove a folder from the configuration and tree.

This does NOT delete files or folders from the filesystem.

## Delete Files

Delete the contents of all selected directories.

Only checked directories are processed.

---

# Requirements

- Java 8+
- Maven
- JavaFX

---

# Build

```bash
mvn clean package
```
# Run

```bash
mvn javafx:run
```

# Run JAR File

After building the project, the `target` folder contains two JAR files:

```txt
FileDeleter-{version}.jar
FileDeleter-{version}-fat.jar
```

The recommended file to run is the **fat JAR**:

```bash
target/FileDeleter-{version}-fat.jar
```

The fat JAR contains the required dependencies and should run normally without problems.

As an alternative, the `start.bat` file in the main project folder can also be used.

Before using `start.bat`, make sure that:

- the JAR file name is correct
- the JAR version matches the generated file in the `target` folder
- the JavaFX SDK path is correct
- the JavaFX SDK path is adjusted for your local system
## Note
- Make sure to back up important files before deleting files, as the deleted files cannot be recovered.

- The GUI uses the de.deloma.tools.filedeleter.Controler class as the controller to manage user interactions. Data access operations are performed by the de.deloma.tools.filedeleter.OrdnerDaoImpl class, which uses the "config.txt" file to store the list of selected directories and their status (enabled/disabled).

## Disclaimer
Use this tool at your own risk. Always ensure you have backups of important files before performing cleanup operations.

### Contribution
Contributions are welcome! If you find issues or have suggestions for improvement, please create a pull request or open an issue.
