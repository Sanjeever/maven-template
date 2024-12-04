package top.sanjeev.core;

import cn.hutool.core.io.resource.ResourceUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 简单的 HTTP 服务器，用于根据预定义的路由提供静态文件服务。
 * <p>
 * 该服务器在指定的端口监听并根据路由配置提供静态资源。
 * <p>
 * 示例用法：
 * HttpServer.start(8080, Map.of("/index", "index.html"));
 *
 * @author Sanjeev
 * @version 1.1.0
 * @since 2024/12/4
 */
@Slf4j
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpServer {

    private static final String HTTP_200 = "HTTP/1.1 200 OK\r\n";
    private static final String HTTP_404 = "HTTP/1.1 404 Not Found\r\n";
    private static final String CONTENT_TYPE_HTML = "Content-Type: text/html; charset=UTF-8\r\n";
    private static final String LINE_SEPARATOR = "\r\n";

    private final int port;

    /**
     * 启动 HTTP 服务器，监听指定端口并使用路由配置提供服务。
     *
     * @param port   监听的端口号。
     * @param router 路由配置，包含路径与对应的资源文件。
     */
    public static void start(int port, Map<String, String> router) {
        new HttpServer(port).start(router);
    }

    /**
     * 启动 HTTP 服务器并开始监听客户端请求。
     *
     * @param router 路由配置，包含路径与对应的资源文件。
     */
    public void start(Map<String, String> router) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("Server started, listening on port {}", port);
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    handleClientRequest(clientSocket, router);
                } catch (IOException e) {
                    log.error("Error handling client request: ", e);
                }
            }
        } catch (IOException e) {
            log.error("Failed to start server: ", e);
        }
    }

    /**
     * 处理客户端请求。
     *
     * @param clientSocket 客户端的 socket 连接。
     * @param router       路由配置，包含路径与对应的资源文件。
     * @throws IOException 如果在读取请求或发送响应时发生 I/O 错误。
     */
    private void handleClientRequest(Socket clientSocket, Map<String, String> router) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream outputStream = clientSocket.getOutputStream()) {
            // 读取请求行（例如："GET / HTTP/1.1"）
            String requestLine = reader.readLine();
            if (requestLine != null && !requestLine.isEmpty()) {
                log.info("Received request: {}", requestLine);
                String requestPath = extractRequestPath(requestLine);

                // 忽略 HTTP 请求头（这里只打印并跳过）
                logRequestHeaders(reader);

                // 查找资源路径并返回相应的内容
                byte[] response = createResponse(router, requestPath);

                // 发送响应给客户端
                outputStream.write(response);
                outputStream.flush();
            } else {
                outputStream.write(makeNotFoundResponse());
                outputStream.flush();
            }
        }
    }

    /**
     * 从请求行中提取请求路径。
     *
     * @param requestLine 请求的第一行（例如："GET /index HTTP/1.1"）。
     * @return 提取的请求路径（例如："/index"）。
     */
    private String extractRequestPath(String requestLine) {
        String[] parts = requestLine.split(" ");
        return (parts.length > 1) ? parts[1] : "/";
    }

    /**
     * 打印 HTTP 请求头部。
     *
     * @param reader BufferedReader，用于读取请求头部内容。
     * @throws IOException 如果读取过程中发生错误。
     */
    private void logRequestHeaders(BufferedReader reader) throws IOException {
        StringBuilder header = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            header.append("\t").append(line).append(LINE_SEPARATOR);
        }
        log.info("Request Headers:\n{}", header);
    }

    /**
     * 从 "static" 目录中读取静态资源。
     *
     * @param resource 资源文件名。
     * @return 资源文件的内容。
     */
    private String readStatic(String resource) {
        return ResourceUtil.readUtf8Str("static/" + resource);
    }

    /**
     * 根据请求路径和路由配置生成响应。
     *
     * @param router      路由配置，包含路径与对应的资源文件。
     * @param requestPath 请求的路径。
     * @return HTTP 响应的字节数组。
     */
    private byte[] createResponse(Map<String, String> router, String requestPath) {
        String resourcePath = router.get(requestPath);
        if (resourcePath != null) {
            return makeOkResponse(resourcePath);
        } else {
            return makeNotFoundResponse();
        }
    }

    /**
     * 生成一个 200 OK 的 HTTP 响应。
     *
     * @param resource 路由路径对应的资源文件。
     * @return HTTP 响应的字节数组。
     */
    private byte[] makeOkResponse(String resource) {
        String html = readStatic(resource);
        String response = HTTP_200 + CONTENT_TYPE_HTML + "Content-Length: " + html
            .length() + LINE_SEPARATOR + LINE_SEPARATOR + html;
        return response.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 生成一个 404 Not Found 的 HTTP 响应。
     *
     * @return HTTP 响应的字节数组。
     */
    private byte[] makeNotFoundResponse() {
        String html = readStatic("404.html");
        String response = HTTP_404 + CONTENT_TYPE_HTML + "Content-Length: " + html
            .length() + LINE_SEPARATOR + LINE_SEPARATOR + html;
        return response.getBytes(StandardCharsets.UTF_8);
    }
}
