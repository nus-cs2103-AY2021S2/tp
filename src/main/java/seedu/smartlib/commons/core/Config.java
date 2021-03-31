package seedu.smartlib.commons.core;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Level;

/**
 * Config values used by the app.
 */
public class Config {

    /** Default file path of the config file. */
    public static final Path DEFAULT_CONFIG_FILE = Paths.get("config.json");

    // Config values customizable through config file
    private Level logLevel = Level.INFO;
    private Path userPrefsFilePath = Paths.get("preferences.json");

    /**
     * Returns the log level of the config file.
     *
     * @return the log level of the config file
     */
    public Level getLogLevel() {
        return logLevel;
    }

    /**
     * Sets the log level of the config file to the given log level.
     *
     * @param logLevel the specified log level that the config file is to be set to.
     */
    public void setLogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * Returns the file path of the file storing the user's preferences.
     *
     * @return the file path of the file storing the user's preferences.
     */
    public Path getUserPrefsFilePath() {
        return userPrefsFilePath;
    }

    /**
     * Updates the file path of the file storing the user's preferences.
     *
     * @param userPrefsFilePath the new file path to the file storing the user's preferences.
     */
    public void setUserPrefsFilePath(Path userPrefsFilePath) {
        this.userPrefsFilePath = userPrefsFilePath;
    }

    /**
     * Checks if this Config object is equal to another Config object.
     *
     * @param other the other Config object to be compared.
     * @return true if this Config object is equal to the other Config object, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Config)) { //this handles null as well.
            return false;
        }

        Config o = (Config) other;

        return Objects.equals(logLevel, o.logLevel)
                && Objects.equals(userPrefsFilePath, o.userPrefsFilePath);
    }

    /**
     * Generates a hashcode for this Config object.
     *
     * @return the hashcode for this Config object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(logLevel, userPrefsFilePath);
    }

    /**
     * Returns this Config object in String format.
     *
     * @return this Config object in String format.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current log level : " + logLevel);
        sb.append("\nPreference file Location : " + userPrefsFilePath);
        return sb.toString();
    }

}
