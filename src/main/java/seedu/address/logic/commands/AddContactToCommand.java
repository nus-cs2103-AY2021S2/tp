package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.project.Project;

/**
 * Adds a contact to a project.
 */
public class AddContactToCommand extends Command {

    public static final String COMMAND_WORD = "addCto";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an existing contact to an existing project. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New contact %1$s added to project %2$s";
    public static final String MESSAGE_DUPLICATE_CONTACT = "This participant already exists under project %1$s";

    private final Index projectToAddToIndex;
    private final Contact contactToAdd;

    /**
     * Creates an AddContactToCommand to add the specified {@code Person} to the specified {@code Project}
     */
    public AddContactToCommand(Index projectIndex, Contact contact) {
        requireAllNonNull(projectIndex, contact);
        projectToAddToIndex = projectIndex;
        contactToAdd = contact;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Project> lastShownProjectList = model.getFilteredProjectList();

        if (projectToAddToIndex.getZeroBased() >= lastShownProjectList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToAddTo = requireNonNull(lastShownProjectList.get(projectToAddToIndex.getZeroBased()));

        if (projectToAddTo.hasParticipant(contactToAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_CONTACT, projectToAddTo.getProjectName()));
        }

        // logic goes here
        projectToAddTo.addParticipant(contactToAdd);
        model.updateFilteredProjectList(model.PREDICATE_SHOW_ALL_PROJECTS);

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, contactToAdd.getName(), projectToAddTo.getProjectName())
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddContactToCommand // instanceof handles nulls
                && projectToAddToIndex.equals(((AddContactToCommand) other).projectToAddToIndex)
                && contactToAdd.equals(((AddContactToCommand) other).contactToAdd)
            );
    }
}
