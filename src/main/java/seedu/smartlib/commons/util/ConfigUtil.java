package seedu.smartlib.commons.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.smartlib.commons.core.Config;
import seedu.smartlib.commons.exceptions.DataConversionException;

/**
 * A class for accessing the Config file.
 */
public class ConfigUtil {

    /**
     * Reads the Config file from the specified file path.
     *
     * @param configFilePath file path leading to the Config file
     * @return an Optional containing a Config object
     * @throws DataConversionException if the file is not a Config file
     */
    public static Optional<Config> readConfig(Path configFilePath) throws DataConversionException {
        return JsonUtil.readJsonFile(configFilePath, Config.class);
    }

    /**
     * Saves the current configurations to the Config file.
     *
     * @param config a Config object storing the current configurations
     * @param configFilePath file path leading to the Config file
     * @throws IOException if an error occurs during the saving process
     */
    public static void saveConfig(Config config, Path configFilePath) throws IOException {
        JsonUtil.saveJsonFile(config, configFilePath);
    }

}
