package seedu.address.storage;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.FileUtil;

/**
 * Handles the encryption and decryption of the data .json file.
 */
public class Authentication {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);
    private static final String ENCRYPTION_KEY = "wQhgIpxA2KAds5PF2bJc32";
    private static final String PASSWORD_FILE_NAME = "keystore";

    /** Path of the .json file containing the serialized address book */
    private final Path filePath;
    private Optional<String> password = Optional.empty();


    /**
     * Instantiates an Authentication object with the path of the data .json file.
     * @param filePath path of the data .json file
     */
    public Authentication(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Instantiates Authentication object with the default path "/data/addressbook.json"
     */
    public Authentication() {
        this.filePath = Paths.get("/data/addressbook.json");
        this.password = Optional.empty();
    }

    /**
     * Initiates a feedback loop to get the user's password to unlock the encrypted zip file.
     */
    public void unlock() throws ZipException {
        assert isExistsZip() : "Zip must exist to call the unlock() method.";

        //If there is zip but it's not locked, just unzip it.
        if (!isExistsLockedZip()) {
            attemptUnzipUnprotected();
        } else {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Please enter your password: ");
                String attemptPassword = scanner.nextLine();
                if (attemptUnzip(attemptPassword)) {
                    this.password = Optional.of(attemptPassword);
                    System.out.println("Password is verified. Launching ClientBook.");
                    break;
                } else {
                    System.out.println("Password is incorrect, please try again.");
                }
            }
        }
    }

    /**
     * Checks if the locked zip containing the data file exists.
     * @return true if the locked zip exists, false otherwise.
     */
    public boolean isExistsZip() {
        return Files.exists(Paths.get(this.getZipPath()));
    }

    /**
     * Checks if the locked zip file containing the data .json file exists.
     * @return true if the zip file exists.
     * @throws ZipException if error occurs when checking if the zip file is encrypted.
     */
    public boolean isExistsLockedZip() throws ZipException {
        ZipFile dataZip = new ZipFile(this.getZipPath());
        return isExistsZip() && dataZip.isEncrypted();
    }

    /**
     * Locks the data .json file in a password protected zip file and delete the original data .json file.
     * Assumes: data .json file exists.
     */
    public void lock() throws ZipException {
        ZipFile dataZip;
        try {
            //If ClientBook is locked, encrypt the zip file using the password.
            if (this.password.isPresent()) {
                dataZip = new ZipFile(this.getZipPath(), this.password.get().toCharArray());
                ZipParameters parameters = new ZipParameters();
                parameters.setEncryptFiles(true);
                parameters.setEncryptionMethod(EncryptionMethod.AES);
                parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
                parameters.setCompressionLevel(CompressionLevel.NORMAL);
                parameters.setCompressionMethod(CompressionMethod.DEFLATE);
                dataZip.addFile(this.filePath.toString(), parameters);

            //ClientBook is not locked, zip the data .json file without a password.
            } else {
                dataZip = new ZipFile(this.getZipPath());
                dataZip.addFile(this.filePath.toString());
            }
        } catch (ZipException e) {
            logger.info("Failed to compress data .json file into a zip file");
            throw e;
        }
    }

    /**
     * Sets a hook to lock the data .json file in the zip file when the application closes.
     */
    public void setShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                this.lock();
                this.deleteJson();
            } catch (ZipException e) {
                e.printStackTrace();
            }
        }));
    }

    public String getPassword() {
        return this.password.orElse("");
    }

    public boolean isPasswordPresent() {
        return this.password.isPresent();
    }

    public void setPassword(Optional<String> password) throws NoSuchPaddingException,
            NoSuchAlgorithmException, IOException, BadPaddingException,
            IllegalBlockSizeException, InvalidKeyException {
        this.password = password;
        encryptAndStorePassword();
    }

    public void removePassword() {
        this.password = Optional.empty();
    }

    /**
     * Reads the password from the password file if any and set it to this.password.
     */
    public void readPasswordFileAndSetPassword() throws IOException, IllegalBlockSizeException,
            InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        //Decrypt the password file if it exists.
        File passwordFile = new File(this.getPasswordFilePath());
        if (!passwordFile.exists()) {
            this.password = Optional.empty();
        }
        byte[] encryptedPasswordBytes = Files.readAllBytes(passwordFile.toPath());
        this.password = Optional.of(decryptPassword(encryptedPasswordBytes));
        encryptAndStorePassword();
    }

    private void deleteJson() {
        File dataJson = new File(this.filePath.toString());
        dataJson.delete();
    }

    private boolean attemptUnzip(String password) throws ZipException {
        ZipFile dataZip = new ZipFile(this.getZipPath());
        //First check if zip file encrypted and set the password to be used.
        try {
            if (dataZip.isEncrypted()) {
                dataZip.setPassword(password.toCharArray());
            }
        } catch (ZipException e) {
            logger.info("Error attempting to check zip file at: " + this.getZipPath());
            throw e;
        }

        //Attempt to unzip the file using the provided password.
        try {
            dataZip.extractAll(this.getFolderPath());
            return true;
        } catch (ZipException e) {
            logger.info("Error when unzipping file at: " + this.getZipPath());
            return false;
        }
    }

    /**
     * Attempts to unzip the unprotected addressbook.zip file.
     * @return true if unzip is successful
     * @throws ZipException when trying to unzip the file.
     */
    private boolean attemptUnzipUnprotected() throws ZipException {
        return attemptUnzip("");
    }

    /**
     * Gets the path of the .zip file containing the data .json file.
     */
    String getZipPath() {
        String zipPath = filePath.toString().replaceAll("\\.json$", ".zip");
        return zipPath;
    }

    /**
     * Gets path of the folder containing the data .json file.
     */
    private String getFolderPath() {
        String folderPath = this.filePath.getParent().toString();
        return folderPath;
    }

    String getPasswordFilePath() {
        return this.getFolderPath() + "/" + PASSWORD_FILE_NAME;
    }

    private SecretKey getSecretKeyFromEncryptionKey() throws NoSuchAlgorithmException {
        byte[] encodedKey = ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8);
        //Hash the encodedkey
        byte[] encodedKeyDigest = MessageDigest.getInstance("SHA-1").digest(encodedKey);
        //Get first 16 byte = 128 bits to be used as key.
        encodedKeyDigest = Arrays.copyOf(encodedKeyDigest, 16);
        return new SecretKeySpec(encodedKeyDigest, "AES");
    }

    //@@authoer swayongshen-rused
    //Resused from https://howtodoinjava.com/java/java-security/java-aes-encryption-example/
    private void encryptAndStorePassword() throws IOException, NoSuchPaddingException,
            NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        //Encrypt this.password using DES into a byte[].
        SecretKey myKey = getSecretKeyFromEncryptionKey();
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, myKey);
        byte[] passwordBytes = this.getPassword().getBytes(StandardCharsets.UTF_8);
        byte[] passwordEncrypted = cipher.doFinal(passwordBytes);

        //Write the encrypted password byte[] into the password file.
        File passwordFile = new File(this.getPasswordFilePath());
        FileUtil.createParentDirsOfFile(passwordFile.toPath());
        FileOutputStream outputStream = new FileOutputStream(passwordFile);
        outputStream.write(passwordEncrypted);
    }

    //@@authoer swayongshen-rused
    //Resused from https://howtodoinjava.com/java/java-security/java-aes-encryption-example/
    /**
     * Decrypts the bytes of the encrypted password which was read from the password file.
     */
    private String decryptPassword(byte[] encryptedPasswordBytes) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        SecretKey myKey = this.getSecretKeyFromEncryptionKey();
        cipher.init(Cipher.DECRYPT_MODE, myKey);
        byte[] textDecrypted = cipher.doFinal(encryptedPasswordBytes);
        return new String(textDecrypted);
    }


}
