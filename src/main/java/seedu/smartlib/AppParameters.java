package seedu.smartlib;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import javafx.application.Application;
import seedu.smartlib.commons.core.LogsCenter;
import seedu.smartlib.commons.util.FileUtil;

/**
 * Represents the parsed command-line parameters given to the application.
 */
public class AppParameters {

    private static final Logger logger = LogsCenter.getLogger(AppParameters.class);

    private Path configPath;

    /**
     * Returns the config path of the application.
     *
     * @return the config path of the application.
     */
    public Path getConfigPath() {
        return configPath;
    }

    /**
     * Updates the config path of the application.
     *
     * @param configPath the new config path of the application.
     */
    public void setConfigPath(Path configPath) {
        this.configPath = configPath;
    }

    /**
     * Parses the application command-line parameters.
     *
     * @param parameters the application command-line parameters.
     * @return the application command-line parameters parsed as an AppParameters object.
     */
    public static AppParameters parse(Application.Parameters parameters) {
        AppParameters appParameters = new AppParameters();
        Map<String, String> namedParameters = parameters.getNamed();

        String configPathParameter = namedParameters.get("config");
        if (configPathParameter != null && !FileUtil.isValidPath(configPathParameter)) {
            logger.warning("Invalid config path " + configPathParameter + ". Using default config path.");
            configPathParameter = null;
        }
        appParameters.setConfigPath(configPathParameter != null ? Paths.get(configPathParameter) : null);

        return appParameters;
    }

    /**
     * Checks if this AppParameters object is equal to another AppParameters object.
     *
     * @param other the other AppParameters object to be compared.
     * @return true if this AppParameters object is equal to the other AppParameters object, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AppParameters)) {
            return false;
        }

        AppParameters otherAppParameters = (AppParameters) other;
        return Objects.equals(getConfigPath(), otherAppParameters.getConfigPath());
    }

    /**
     * Generates a hashcode for this AppParameters object.
     *
     * @return the hashcode for this AppParameters object.
     */
    @Override
    public int hashCode() {
        return configPath.hashCode();
    }

}
