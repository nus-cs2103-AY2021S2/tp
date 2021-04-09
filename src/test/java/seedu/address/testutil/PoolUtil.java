package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMUTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPTIME;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PoolCommand;
import seedu.address.model.TripDay;
import seedu.address.model.TripTime;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Pool.
 */
public class PoolUtil {

    /**
     * Returns a pool command string for adding the {@code driver} and passengers at {@code indices}.
     */
    public static String getPoolCommandString(Driver driver, Set<Index> indices, TripDay tripDay,
                                              TripTime tripTime, Set<Tag> tags) {

        StringBuilder sb = new StringBuilder();
        sb.append(PoolCommand.COMMAND_WORD + " ");
        sb.append(DriverUtil.getDriverDetails(driver) + " ");
        sb.append(PREFIX_TRIPDAY + tripDay.toString() + " ");
        sb.append(PREFIX_TRIPTIME + tripTime.toString() + " ");

        for (Index idx : indices) {
            sb.append(PREFIX_COMMUTER + String.valueOf(idx.getOneBased()) + " ");
        }

        tags.forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );

        return sb.toString();
    }
}
