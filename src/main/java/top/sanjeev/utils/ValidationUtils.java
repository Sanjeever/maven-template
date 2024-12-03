package top.sanjeev.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sanjeev
 * @version 1.0.0
 * @since 2024/12/3 11:22
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationUtils {

    /**
     * 检查给定的字符串是否为有效的电子邮件地址
     *
     * @param email 待验证的电子邮件地址字符串
     * @return 如果字符串是有效的电子邮件地址，则返回 true；否则返回 false
     */
    public static boolean isValidEmail(String email) {
        // 检查电子邮件地址是否为空
        if (email == null) {
            return false;
        }
        // 定义电子邮件的正则表达式
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{1,}$";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(emailRegex);
        // 匹配给定的电子邮件地址
        Matcher matcher = pattern.matcher(email);
        // 返回匹配结果
        return matcher.matches();
    }

}
