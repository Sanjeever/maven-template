# Maven 模板项目

欢迎使用 **Maven 模板** 项目！🚀

这是一个简单的 Maven 模板项目，帮助你快速开始 Java 开发。它包含了常用依赖库，如日志、测试和代码格式化等，并提供了一个开箱即用的
Maven 项目结构。

## 特性

- 支持 JDK 8 ☕️
- 预配置的常用依赖：
    - **Hutool**：一款流行的 Java 工具类库 🌟
    - **SLF4J** 和 **Logback**：用于日志记录 📝
    - **Lombok**：减少 Java 代码中的样板代码 ✂️
    - **JUnit 5**：单元测试框架 ⚙️
    - **Spotless**：保持代码风格一致性 💅
- 使用镜像仓库快速获取依赖 🌐

## 快速开始

### 前置条件

确保你已经安装了以下软件：

- [JDK 8](https://bell-sw.com/pages/downloads/#jdk-8-lts)
- [Maven](https://maven.apache.org/install.html)

### 打包项目

```bash
mvn package
```

执行后，会在 target 目录下生成两个 jar 包，一个是不带依赖的 jar 包，一个是后缀有 jar-with-dependencies 带有依赖的jar包，如

- maven-template-1.0.2.jar
- maven-template-1.0.2-jar-with-dependencies.jar

使用

```bash
java -jar ./target/maven-template-1.0.2-jar-with-dependencies.jar
```

来运行构建好的 jar 包

### Docker 作法

构建镜像

```bash
docker build -t maven-template .
```

运行容器

```bash
docker run --name maven-template -p 8080:8080 -d maven-template
```

### 代码格式化

使用 Spotless 插件来确保代码风格的一致性

#### 检查代码格式

```bash
mvn spotless:check
```

#### 应用代码格式

```bash
mvn spotless:apply
```

## LICENSE

[MIT](LICENSE)
