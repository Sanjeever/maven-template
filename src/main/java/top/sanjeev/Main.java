package top.sanjeev;

import cn.hutool.setting.dialect.Props;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
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
}