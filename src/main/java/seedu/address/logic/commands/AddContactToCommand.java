package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

/**
 * Clears the address book.
 */
public class AddContactToCommand extends Command {

    public static final String COMMAND_WORD = "addCto";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an existing contact to an existing project. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_INDEX + "INDEX]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "New contact %1$s added to project %2$s";
    public static final String MESSAGE_DUPLICATE_CONTACT = "This participant already exists under project %1$s";

    private final Index projectToAddToIndex;
    private final Index personToAddIndex;

    /**
     * Creates an AddContactToCommand to add the specified {@code Person} to the specified {@code Project}
     */
    public AddContactToCommand(Index projectIndex, Index contactIndex) {
        projectToAddToIndex = projectIndex;
        personToAddIndex = contactIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Project> lastShownProjectList = model.getFilteredProjectList();

        if (projectToAddToIndex.getZeroBased() >= lastShownProjectList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToAddTo = lastShownProjectList.get(personToAddIndex.getZeroBased());

        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (personToAddIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAdd = lastShownPersonList.get(personToAddIndex.getZeroBased());

        if (projectToAddTo.hasParticipant(personToAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_CONTACT, projectToAddTo.getProjectName()));
        }

        // logic goes here
        Project editedProject = createEditedProject(projectToAddTo, personToAdd);
        model.setProject(projectToAddTo, editedProject);
        model.updateFilteredProjectList(model.PREDICATE_SHOW_ALL_PROJECTS);

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, personToAdd.getName(), projectToAddTo.getProjectName())
        );
    }

    private Project createEditedProject(Project projectToEdit, Person personToAdd) {
        requireNonNull(projectToEdit);

        return projectToEdit.addParticipant(personToAdd);
    }
}
