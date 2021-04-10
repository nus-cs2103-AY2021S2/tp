package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.model.person.driver.Driver;

/**
 * A utility class for Driver.
 */
public class DriverUtil {

    public static String getDriverDetails(Driver driver) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + driver.getName().fullName + " ");
        sb.append(PREFIX_PHONE + driver.getPhone().value + " ");
        return sb.toString();
    }
}
