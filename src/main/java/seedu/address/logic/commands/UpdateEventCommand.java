package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPEATABLE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPEATABLE_INTERVAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UPDATE_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.task.repeatable.Event;

/**
 * Updates an event inside a project.
 */
public class UpdateEventCommand extends Command {

    public static final String COMMAND_WORD = "updateE";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates an event of a project specified "
            + "by 2 index numbers: project index and target event index.\n"
            + "Parameters: PROJECT_INDEX "
            + PREFIX_UPDATE_INDEX + "TARGET_EVENT_INDEX "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_REPEATABLE_INTERVAL + "INTERVAL "
            + PREFIX_REPEATABLE_DATE + "REPEATABLE_DATE\n"
            + "Example:\n" + COMMAND_WORD + " 1 "
            + PREFIX_UPDATE_INDEX + "1 "
            + PREFIX_DESCRIPTION + "Project meeting "
            + PREFIX_REPEATABLE_INTERVAL + "DAILY "
            + PREFIX_REPEATABLE_DATE + "24-04-2021";

    public static final String MESSAGE_UPDATE_EVENT_SUCCESS = "Edited event: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in this project.";

    private final Index projectIndex;
    private final Index targetEventIndex;
    private final Event event;

    /**
     * Constructs an {@code updateEventCommand} with a {@code projectIndex}.
     * {@code targetEventIndex} and an {@code Event}.
     *
     * @param projectIndex     index of the project in the filtered project list.
     * @param targetEventIndex index of the event in the event list to update.
     * @param event            new event given for updating.
     */
    public UpdateEventCommand(Index projectIndex, Index targetEventIndex, Event event) {
        requireAllNonNull(projectIndex, targetEventIndex, event);

        this.projectIndex = projectIndex;
        this.targetEventIndex = targetEventIndex;
        this.event = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (projectIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }
        Project projectRelated = lastShownList.get(projectIndex.getZeroBased());

        if (targetEventIndex.getZeroBased() >= projectRelated.getEvents().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        if (projectRelated.getEvents().hasEvent(event)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        projectRelated.getEvents().setEvent(targetEventIndex.getZeroBased(), event);
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_UPDATE_EVENT_SUCCESS, event));
    }
}
