package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.plan.Plan;

/**
 * A utility class for Plan.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code plan}.
     */
    public static String getAddCommand(Plan plan) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(plan);
    }

    /**
     * Returns the part of command string for the given {@code plan}'s details.
     */
    public static String getPersonDetails(Plan plan) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ADDRESS + plan.getDescription().value + " ");
        plan.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
}
