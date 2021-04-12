package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddGroupmateCommand;
import seedu.address.model.groupmate.Groupmate;

/**
 * A utility class for Groupmate.
 */
public class GroupmateUtil {

    /**
     * Returns an addGroupmate command string for adding the {@code Groupmate}.
     */
    public static String getAddGroupmateCommand(Index projectIndex, Groupmate groupmate) {
        return AddGroupmateCommand.COMMAND_WORD + " " + projectIndex.getOneBased() + " "
                + getGroupmateDetails(groupmate);
    }

    /**
     * Returns the part of command string for the given {@code Groupmate}'s details.
     */
    public static String getGroupmateDetails(Groupmate groupmate) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + groupmate.getName().fullName + " ");
        groupmate.getRoles().stream().forEach(s -> sb.append(PREFIX_ROLE + s.roleName + " ")
        );
        return sb.toString();
    }
}
