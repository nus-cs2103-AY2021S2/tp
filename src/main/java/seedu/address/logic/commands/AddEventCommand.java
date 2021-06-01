package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_WEEKLY;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ViewProjectAndOverviewUiCommand;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.exceptions.DuplicateEventException;
import seedu.address.model.task.repeatable.Event;

/**
 * Adds an event to a specified project in CoLAB.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "addE";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to a specified project.\n"
            + "Parameters: PROJECT_INDEX "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_EVENT_DATE + "DATE "
            + PREFIX_EVENT_TIME + "TIME "
            + PREFIX_EVENT_WEEKLY + "REPEATS_WEEKLY\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Project meeting "
            + PREFIX_EVENT_DATE + "24-04-2021 "
            + PREFIX_EVENT_TIME + "1730 "
            + PREFIX_EVENT_WEEKLY + "Y";

    private final Index index;
    private final Event toAdd;

    /**
     * Creates an AddEventCommand to add specified {@code Event} to {@code Project} with {@code Index}.
     * @param index index of {@code Project} to add event in the list.
     * @param event {@code Event} to add.
     */
    public AddEventCommand(Index index, Event event) {
        requireAllNonNull(index, event);

        this.index = index;
        this.toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(index.getZeroBased());
        assert projectToEdit != null;

        try {
            projectToEdit.addEvent(toAdd);
        } catch (DuplicateEventException e) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_EVENT);
        }

        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(Messages.MESSAGE_ADD_EVENT_SUCCESS, toAdd,
                projectToEdit.getProjectName()), new ViewProjectAndOverviewUiCommand(index));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                && toAdd.equals(((AddEventCommand) other).toAdd)
                && index.equals(((AddEventCommand) other).index));
    }

}
