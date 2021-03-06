package seedu.address.commons.util;

import java.util.Arrays;

/**
 * Utility methods related to Enums
 */
public class EnumUtil {

    // Taken from https://stackoverflow.com/a/13783744

    /**
     * Returns all enum values of a provided enum as a String array
     * @param e Enum to get string values of
     * @return String array containing the enum values
     */
    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
