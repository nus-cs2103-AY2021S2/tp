package seedu.address.storage;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import seedu.address.commons.core.LogsCenter;

/**
 * Handles the encryption and decryption of the data .json file.
 */
public class Authentication {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    /** Path of the .json file containing the serialized address book */
    private Path filePath;

    public Authentication(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Initiates a feedback loop to get the user's password to unlock the encrypted zip file.
     */
    public void unlock() throws ZipException {
        System.out.println("Please enter your password: ");
        Scanner scanner = new Scanner(System.in);
        boolean isSuccess = attemptUnzip(scanner.nextLine());
        while (!isSuccess) {
            System.out.println("Password is incorrect, please try again: ");
            isSuccess = attemptUnzip(scanner.nextLine());
        }
    }

    /**
     * Checks if the locked zip containing the data file exists.
     * @return true if the locked zip exists, false otherwise.
     */
    public boolean isExistsLockedZip() {
        String zipPath = filePath.toString().replaceAll("\\.json$", ".zip");
        return Files.exists(Paths.get(zipPath));
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

}
