package seedu.address.logic.commands.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESTAMP;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.issue.Issue;

/**
 * Adds a issue to the address book.
 */
public class AddIssueCommand extends Command {

    public static final String COMMAND_WORD = "iadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an issue to SunRez. "
            + "Parameters: "
            + PREFIX_ROOM_NUMBER + "ROOM_NO "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_TIMESTAMP + "TIMESTAMP] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_CATEGORY + "CATEGORY] "
            + "[" + PREFIX_TAG + "TAG] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROOM_NUMBER + "10-100 "
            + PREFIX_DESCRIPTION + "Broken light "
            + PREFIX_TIMESTAMP + "2020/01/12 03:30pm "
            + PREFIX_STATUS + "pending "
            + PREFIX_CATEGORY + "furniture "
            + PREFIX_TAG + "HIGH";

    public static final String MESSAGE_SUCCESS = "New issue added: %1$s";
    public static final String MESSAGE_NO_SUCH_ROOM = "There is no such room";
    public static final String MESSAGE_DUPLICATE_ISSUE = "This issue already exists in SunRez";

    private final Logger logger = LogsCenter.getLogger(AddIssueCommand.class);

    private final Issue toAdd;

    /**
     * Creates an AddIssueCommand to add the specified {@code issue}.
     *
     * @param issue The issue to add.
     * @throws NullPointerException If {@code issue} is null.
     */
    public AddIssueCommand(Issue issue) {
        requireNonNull(issue);
        toAdd = issue;
    }

    /**
     * Executes an AddIssuecommand to add an issue.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of command execution.
     * @throws CommandException     If {@code model} is invalid.
     * @throws NullPointerException If the {@code model} is null.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasRoom(new seedu.address.model.room.RoomNumber(toAdd.getRoomNumber().value))) {
            logger.warning("Non existent room given to iadd command");
            throw new CommandException(MESSAGE_NO_SUCH_ROOM);
        }

        if (model.hasIssue(toAdd)) {
            logger.warning("Duplicate issue given to iadd command");
            throw new CommandException(MESSAGE_DUPLICATE_ISSUE);
        }

        model.addIssue(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddIssueCommand // instanceof handles nulls
                        && toAdd.equals(((AddIssueCommand) other).toAdd));
    }

}
