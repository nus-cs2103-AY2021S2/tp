package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class Rfc822UtilTest {

    private static final List<String> VALID_EMAIL_LIST = Arrays.stream(new String[] {
            "abcdefg@example.org",
            "email@example.com",
            "firstname.lastname@example.com",
            "email@subdomain.example.com",
            "firstname+lastname@example.com",
            "email@123.123.123.123",
            "email@[123.123.123.123]",
            "email@example.com",
            "234567890@example.com",
            "email@example-one.com",
            "_______@example.com",
            "email@example.name",
            "email@example.museum",
            "email@example.co.jp",
            "firstname-lastname@example.com"}).collect(Collectors.toList());

    private static final List<String> INVALID_EMAIL_LIST = Arrays.stream(new String[] {
            "plainaddress",
            "#@%^%#$@#$@#.com",
            "@example.com",
            "Joe Smith <email@example.com>",
            "email.example.com",
            "email@example@example.com",
            ".email@example.com",
            "email.@example.com",
            "email..email@example.com",
            "あいうえお@example.com",
            "email@example.com (Joe Smith)",
            // "email@example",
            "email@-example.com",
            "email@example.web",
            "email@111.222.333.44444",
            "email@example..com",
            "Abc..123@example.com"}).collect(Collectors.toList());

    @Test
    public void validate_validEmail_true() {
        VALID_EMAIL_LIST.forEach(x -> assertTrue(Rfc6530Util.EMAIL_PATTERN.matcher(x).find()));
    }

    @Test
    public void validate_invalidEmail_false() {
        INVALID_EMAIL_LIST.forEach(x -> {
            System.out.println(x);
            assertFalse(Rfc6530Util.EMAIL_PATTERN.matcher(x).find());
        });
    }

}
