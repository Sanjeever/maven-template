package top.sanjeev.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationUtilsTest {

    @Test
    @DisplayName("测试有效的电子邮件地址")
    public void testValidEmails() {
        assertTrue(ValidationUtils.isValidEmail("test@example.com"));
        assertTrue(ValidationUtils.isValidEmail("user.name+tag+sorting@example.com"));
        assertTrue(ValidationUtils.isValidEmail("x@y.z"));
        assertTrue(ValidationUtils.isValidEmail("user@subdomain.example.com"));
        assertTrue(ValidationUtils.isValidEmail("user123@domain.co"));
    }

    @Test
    @DisplayName("测试无效的电子邮件地址")
    public void testInvalidEmails() {
        assertFalse(ValidationUtils.isValidEmail("plainaddress"));
        assertFalse(ValidationUtils.isValidEmail("test@.com"));
        assertFalse(ValidationUtils.isValidEmail("test@com"));
        assertFalse(ValidationUtils.isValidEmail("@example.com"));
        assertFalse(ValidationUtils.isValidEmail("test@com."));
        assertFalse(ValidationUtils.isValidEmail("user@domain..com"));
    }

    @Test
    @DisplayName("测试空值电子邮件地址")
    public void testNullEmail() {
        assertFalse(ValidationUtils.isValidEmail(null));
    }

    @Test
    @DisplayName("测试空字符串电子邮件地址")
    public void testEmptyEmail() {
        assertFalse(ValidationUtils.isValidEmail(""));
    }

}
