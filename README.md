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
    - **PMD**：静态代码分析工具，帮助提升代码质量 🛠
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

- maven-template-1.0.1.jar
- maven-template-1.0.1-jar-with-dependencies.jar

使用

```bash
java -jar ./target/maven-template-1.0.1-jar-with-dependencies.jar
```

来运行构建好的 jar 包

### Docker 作法

构建镜像

```bash
docker build -t maven-template .
```

在交互模式下运行容器

```bash
docker run -it maven-template /bin/bash
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

### 静态代码分析（PMD）

本项目集成了 PMD 插件，用于执行静态代码分析，帮助提升代码质量和可维护性。默认情况下，PMD 会在构建时检查代码并生成报告。

#### 执行 PMD 静态分析

```bash
mvn pmd:pmd
```

该命令会执行 PMD 静态代码分析，并生成报告，报告位于 target/reports/pmd.html。你可以打开 HTML 报告来查看代码问题和改进建议。

#### 配置 PMD 规则集

PMD 插件使用的规则集定义了哪些检查会被执行。当前配置的规则集为 rulesets/java/basic.xml，适用于常规项目。如果你需要调整检查规则或添加自定义规则集，可以编辑
pom.xml 中的 PMD 配置部分。

#### 报告格式和输出目录

PMD 插件默认生成 HTML 格式的报告，并将报告文件存储在 target/reports 目录中。你可以根据需要调整报告格式（如 xml 或
text）或输出路径。

#### 排除文件

如果你不希望某些文件（如测试代码）参与 PMD 检查，可以在 pom.xml 中配置 excludes 部分进行排除。例如，当前配置排除了所有以 Test
开头的类和 generated 目录中的代码。

```xml

<excludes>
    <exclude>**/Test*.java</exclude>
    <exclude>**/generated/**</exclude>
</excludes>
```

## LICENSE

[MIT](LICENSE)
