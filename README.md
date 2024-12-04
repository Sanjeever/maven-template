# Maven Template Project

Welcome to the **Maven Template** project! 🚀

This is a simple Maven template project designed to help you quickly get started with Java development. It includes commonly used dependencies such as logging, testing, and code formatting tools, and provides an out-of-the-box Maven project structure.

## README.md
- en [English](README.md)
- zh_CN [简体中文](README.zh_CN.md)

## Features

- Supports JDK 8 ☕️
- Pre-configured common dependencies:
    - **Hutool**: A popular Java utility library 🌟
    - **SLF4J** and **Logback**: For logging 📝
    - **Lombok**: To reduce boilerplate code in Java ✂️
    - **JUnit 5**: Unit testing framework ⚙️
    - **Spotless**: Ensures code style consistency 💅
- Fast dependency retrieval using mirror repositories 🌐

## Quick Start

### Prerequisites

Ensure that you have the following software installed:

- [JDK 8](https://bell-sw.com/pages/downloads/#jdk-8-lts)
- [Maven](https://maven.apache.org/install.html)

### Build the Project

```bash
mvn package
```

After running this command, two JAR files will be generated in the target directory: one is a JAR without dependencies, and the other is a JAR with dependencies, e.g.,

- maven-template-1.0.3.jar
- maven-template-1.0.3-jar-with-dependencies.jar

To run the built JAR file, use:

```bash
java -jar ./target/maven-template-1.0.3-jar-with-dependencies.jar
```
### Docker Usage

Build the Docker image:

```bash
docker build -t maven-template .
```

Run the Docker container:

```bash
docker run --name maven-template -p 8080:8080 -d maven-template
```

### Code Formatting

Use the Spotless plugin to ensure consistent code style.

#### Check Code Format

```bash
mvn spotless:check
```

#### Apply Code Format

```bash
mvn spotless:apply
```

## LICENSE

[MIT](LICENSE)
