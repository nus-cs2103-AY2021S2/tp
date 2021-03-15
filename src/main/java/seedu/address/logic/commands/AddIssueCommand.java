package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESTAMP;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.issue.Issue;

/**
 * Adds a person to the address book.
 */
public class AddIssueCommand extends Command {

    public static final String COMMAND_WORD = "iadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an issue to SunRez. "
            + "Parameters: "
            + PREFIX_ROOM_NO + "ROOM_NO "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_TIMESTAMP + "TIMESTAMP] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_CATEGORY + "CATEGORY]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROOM_NO + "10-100 "
            + PREFIX_DESCRIPTION + "Broken light "
            + PREFIX_TIMESTAMP + "2020-01-01 15:30 "
            + PREFIX_STATUS + "pending "
            + PREFIX_CATEGORY + "furniture";

    public static final String MESSAGE_SUCCESS = "New issue added: %1$s";

    private final Issue toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddIssueCommand(Issue person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addIssue(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddIssueCommand // instanceof handles nulls
                        && toAdd.equals(((AddIssueCommand) other).toAdd));
    }

}
