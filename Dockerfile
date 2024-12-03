# 使用官方 Maven 镜像作为基础镜像，包含了 Maven 和 OpenJDK
FROM maven:3.8.4-openjdk-8-slim AS builder

# 设置工作目录
WORKDIR /app

# 将源码文件复制到容器中
COPY . /app/

# 运行 Maven 构建项目，生成带依赖的 JAR 包
RUN mvn clean package -DskipTests

# 使用较小的运行时镜像（只包含 JRE）来减小最终镜像的大小
FROM openjdk:8-jre-slim

# 设置时区为 Asia/Shanghai
RUN apt-get update && apt-get install -y tzdata && \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    dpkg-reconfigure --frontend noninteractive tzdata

# 设置工作目录
WORKDIR /app

# 将构建好的 JAR 文件从 builder 镜像中复制到当前镜像
COPY --from=builder /app/target/maven-template-1.0.1-jar-with-dependencies.jar /app/maven-template.jar

# 容器启动时运行该 JAR 文件
ENTRYPOINT ["java", "-jar", "maven-template.jar"]

# 暴露应用运行的端口
EXPOSE 8080

# 设置默认命令来启动容器
CMD ["java", "-jar", "maven-template.jar"]
