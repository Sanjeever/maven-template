package top.sanjeev;

import cn.hutool.setting.dialect.Props;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;

/**
 * @author Sanjeev
 * @version 1.0.0
 * @since 2024/12/3 11:03
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        Props props = new Props("application.properties");
        String name = props.getStr("application.name");
        String version = props.getStr("application.version");
        log.info("Application Name: {}", name);
        log.info("Application Version: {}", version);
        logInfo();
        startHttpServer(props.getInt("server.port"));
    }

    /**
     * 日志记录环境信息方法
     * 该方法用于收集并记录当前运行环境的详细信息，包括操作系统、Java运行时环境、用户信息、文件系统分隔符以及区域设置等
     * 这有助于在日志中提供更丰富的上下文信息，便于问题排查和性能分析
     */
    private static void logInfo() {
        // 操作系统相关信息
        String os = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String osArch = System.getProperty("os.arch");

        // Java 相关信息
        String javaVersions = System.getProperty("java.version");
        String javaVendor = System.getProperty("java.vendor");
        String javaHome = System.getProperty("java.home");
        String javaVmName = System.getProperty("java.vm.name");
        String javaVmVersion = System.getProperty("java.vm.version");

        // 用户相关信息
        String userName = System.getProperty("user.name");
        String userHome = System.getProperty("user.home");
        String userDir = System.getProperty("user.dir");

        // 文件路径分隔符
        String fileSeparator = FileSystems.getDefault().getSeparator();
        String pathSeparator = File.pathSeparator;
        String lineSeparator = System.lineSeparator();

        // 时区和语言
        String userTimeZone = System.getProperty("user.timezone");
        String userLanguage = System.getProperty("user.language");
        String userCountry = System.getProperty("user.country");

        // 输出所有信息
        log.info("Operating System: [{}]", os);
        log.info("OS Version: [{}]", osVersion);
        log.info("OS Architecture: [{}]", osArch);

        log.info("Java Version: [{}]", javaVersions);
        log.info("Java Vendor: [{}]", javaVendor);
        log.info("Java Home: [{}]", javaHome);
        log.info("JVM Name: [{}]", javaVmName);
        log.info("JVM Version: [{}]", javaVmVersion);

        log.info("User Name: [{}]", userName);
        log.info("User Home: [{}]", userHome);
        log.info("User Directory: [{}]", userDir);

        log.info("File Separator: [{}]", fileSeparator);
        log.info("Path Separator: [{}]", pathSeparator);
        log.info("Line Separator: [{}]", lineSeparator);

        log.info("User Timezone: [{}]", userTimeZone);
        log.info("User Language: [{}]", userLanguage);
        log.info("User Country: [{}]", userCountry);
    }

    /**
     * 启动一个HTTP服务器，监听指定端口
     * 该服务器无限循环等待客户端连接，处理HTTP请求，并返回一个固定的HTML页面
     *
     * @param port 服务器监听的端口号
     */
    private static void startHttpServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("服务器启动，监听端口 {}", port);
            while (true) {
                // 接受客户端请求
                try (Socket clientSocket = serverSocket.accept()) {
                    // 获取客户端输入流
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    // 获取客户端输出流
                    OutputStream outputStream = clientSocket.getOutputStream();

                    // 读取请求的第一行（例如：GET / HTTP/1.1）
                    String requestLine = reader.readLine();
                    log.info("收到请求: {}", requestLine);

                    // 忽略 HTTP 请求头部（这里只处理请求的第一行，实际应用中应完整解析头部）
                    String line;
                    StringBuilder header = new StringBuilder();
                    while ((line = reader.readLine()) != null && !line.isEmpty()) {
                        header.append(line).append("\r\n");
                    }
                    log.info("请求头: \n{}", header);

                    // 返回一个 HTML 页面
                    String response = indexResponse();

                    // 向客户端发送响应
                    outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                } catch (IOException e) {
                    log.error("处理请求时发生错误: ", e);
                }
            }
        } catch (IOException e) {
            log.error("服务器启动失败: ", e);
        }
    }

    /**
     * 生成首页的 HTTP 响应
     * <p>
     * 此方法构建一个简单的 HTTP 响应，其中包含一个 HTML 页面作为响应体
     * 响应包括 HTTP 状态行、头部信息和一个简单的欢迎页面
     *
     * @return 返回一个包含 HTTP 响应的字符串
     */
    private static String indexResponse() {
        // 定义一个简单的 HTML 页面作为 HTTP 响应的正文
        String htmlResponse = "<html>" +
                "<head><title>My Simple HTTP Server</title></head>" +
                "<body>" +
                "<h1>Hello, World!</h1>" +
                "<p>Welcome to my simple HTTP server!</p>" +
                "</body>" +
                "</html>";

        // 构造 HTTP 响应
        // 包含状态行、内容类型、内容长度和空行作为响应头部
        // 然后附加 HTML 正文
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html; charset=UTF-8\r\n" +
                "Content-Length: " + htmlResponse.length() + "\r\n" +
                "\r\n" +
                htmlResponse;
        return response;
    }

}