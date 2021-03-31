package dog.pawbook.commons.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import dog.pawbook.commons.core.Config;
import dog.pawbook.commons.exceptions.DataConversionException;

/**
 * A class for accessing the Config File.
 */
public class ConfigUtil {

    public static Optional<Config> readConfig(Path configFilePath) throws DataConversionException {
        return JsonUtil.readJsonFile(configFilePath, Config.class);
    }

    public static void saveConfig(Config config, Path configFilePath) throws IOException {
        JsonUtil.saveJsonFile(config, configFilePath);
    }

}
