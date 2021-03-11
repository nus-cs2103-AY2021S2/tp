package seedu.address.storage;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;

import seedu.address.commons.core.LogsCenter;

/**
 * Handles the encryption and decryption of the data .json file.
 */
public class Authentication {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    /** Path of the .json file containing the serialized address book */
    private Path filePath;
    private Optional<String> password;

    public Authentication(Path filePath) {
        this.filePath = filePath;
        this.password = Optional.empty();
    }

    /**
     * Initiates a feedback loop to get the user's password to unlock the encrypted zip file.
     */
    public void unlock() throws ZipException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter your password: ");
            String attemptPassword = scanner.nextLine();
            if (attemptUnzip(attemptPassword)) {
                this.password = Optional.of(attemptPassword);
                break;
            } else {
                System.out.println("Password is incorrect, please try again.");
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
        if (this.password.isPresent()) {
            dataZip = new ZipFile(this.getZipPath(), this.password.get().toCharArray());
        } else {
            dataZip = new ZipFile(this.getZipPath());
        }

        ZipParameters parameters = new ZipParameters();
        parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        parameters.setCompressionLevel(CompressionLevel.NORMAL);
        parameters.setCompressionMethod(CompressionMethod.DEFLATE);

        try {
            dataZip.addFile(this.filePath.toString(), parameters);
        } catch (ZipException e) {
            logger.info("Failed to lock data file into a zip file");
            throw e;
        }
    }

    private boolean attemptUnzip(String password) throws ZipException {
        ZipFile dataZip = new ZipFile(this.getZipPath());
        //First check if zip file encrypted and set the password to be used. If not encrypted, return true.
        try {
            if (dataZip.isEncrypted()) {
                dataZip.setPassword(password.toCharArray());
            } else {
                return true;
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
     * Gets the path of the .zip file containing the data .json file.
     */
    private String getZipPath() {
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

    public String getPassword() {
        return this.password.orElse("");
    }

}
