package seedu.partyplanet.logic.commands;

import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMOVE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Edits the details of an existing person in PartyPlanet.
 */
public abstract class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "[" + PREFIX_NAME + " NAME] "
            + "[" + PREFIX_PHONE + " PHONE] "
            + "[" + PREFIX_EMAIL + " EMAIL] "
            + "[" + PREFIX_ADDRESS + " ADDRESS] "
            + "[" + PREFIX_TAG + " TAG]... "
            + "[" + PREFIX_BIRTHDAY + " BIRTHDAY] "
            + "[" + PREFIX_REMARK + " REMARK]\n"
            + "OR " + COMMAND_WORD + " " + PREFIX_REMOVE + ": Removes specified tag from all persons.\n"
            + "Parameters: {INDEX (must be a positive integer) | -t TAG} \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + " 91234567 "
            + PREFIX_EMAIL + " johndoe@example.com\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_REMOVE + " " + PREFIX_TAG + " vegetarian";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
}
