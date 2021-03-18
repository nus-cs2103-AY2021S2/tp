package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.Test;

import net.lingala.zip4j.exception.ZipException;

public class AuthenticationTest {
    private static final String DEFAULT_PASSWORD = "1234";
    private static final Path TEST_DATA_PATH = Paths.get("src", "test", "data", "LockTest",
            "addressbook.json");

    @Test
    void execute_setPassword_success() throws NoSuchPaddingException, NoSuchAlgorithmException,
            IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Authentication authentication = new Authentication(TEST_DATA_PATH);
        authentication.setPassword(Optional.of(DEFAULT_PASSWORD));
        File passwordFile = new File(authentication.getPasswordFilePath());
        assertTrue(passwordFile.exists());

        //Clean up
        passwordFile.delete();
    }

    @Test
    void execute_lockUnlock_success() throws IllegalAccessException, NoSuchFieldException, ZipException {
        Field password = Authentication.class.getDeclaredField("password");
        password.setAccessible(true);
        Authentication authentication = new Authentication(TEST_DATA_PATH);
        password.set(authentication, Optional.of(DEFAULT_PASSWORD));
        authentication.lock();
        File zipFile = new File(authentication.getZipPath());
        File dataFile = new File(TEST_DATA_PATH.toString());

        //Pass password into System.in stream for unlock() to read.
        System.setIn(new ByteArrayInputStream(DEFAULT_PASSWORD.getBytes()));
        authentication.unlock();
        assertTrue(dataFile.exists() && zipFile.exists());

        //Clean up
        zipFile.delete();
    }



}
