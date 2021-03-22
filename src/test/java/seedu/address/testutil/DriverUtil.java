package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMUTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PoolCommand;
import seedu.address.logic.commands.UnpoolCommand;
import seedu.address.model.person.driver.Driver;

/**
 * A utility class for Driver.
 */
public class DriverUtil {
    /**
     * Returns a pool command string for adding the {@code }.
     */
    public static String getPoolCommand(Driver driver, Set<Index> indices) {
        return PoolCommand.COMMAND_WORD + " " + getDriverDetails(driver) + getCommuterDetails(indices);
    }

    /**
     * Returns an unpool command string for adding the {@code }.
     */
    public static String getUnpoolCommand(Driver driver) {
        return UnpoolCommand.COMMAND_WORD + " " + getDriverDetails(driver);
    }

    public static String getDriverDetails(Driver driver) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + driver.getName().fullName + " ");
        sb.append(PREFIX_PHONE + driver.getPhone().value + " ");
        return sb.toString();
    }

    public static String getCommuterDetails(Set<Index> indices) {
        StringBuilder sb = new StringBuilder();
        for (Index idx : indices) {
            sb.append(PREFIX_COMMUTER + String.valueOf(idx.getOneBased()) + " ");
        }
        return sb.toString();
    }
}
