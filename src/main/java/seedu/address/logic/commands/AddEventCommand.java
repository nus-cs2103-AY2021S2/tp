package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPEATABLE_DATE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.project.EventList;
import seedu.address.model.task.repeatable.Event;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "addEto";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the event list of the project identified "
            + "by the index number used in the displayed project list. "
            + "Existing list will be appended with the input event.\n"
            + "Parameters: PROJECT_INDEX (must be a positive integer) "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_INTERVAL + "INTERVAL] "
            + "[" + PREFIX_REPEATABLE_DATE + "REPEATABLE_DATE] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Project meeting"
            + PREFIX_INTERVAL + "DAILY"
            + PREFIX_REPEATABLE_DATE + "24-04-2021";

    public static final String MESSAGE_EVENT_ADDED_SUCCESS = "New event added: %1$s";

    private final Index index;
    private final Event eventToAdd;

    public AddEventCommand(Index index, Event event) {
        requireNonNull(index);
        requireNonNull(event);

        this.index = index;
        this.eventToAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(index.getZeroBased());
        Project editedProject = createEditedProject(projectToEdit, eventToAdd);

        model.setProject(projectToEdit, editedProject);
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_EVENT_ADDED_SUCCESS, editedProject));
    }

    private Project createEditedProject(Project projectToEdit, Event event) {
        assert projectToEdit != null;

        EventList events = projectToEdit.getEvents().addEvent(event);
        return new Project(projectToEdit.getProjectName(), events,
                projectToEdit.getCompletableTasks(), projectToEdit.getParticipants());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                && eventToAdd.equals(((AddEventCommand) other).eventToAdd));
    }
}
