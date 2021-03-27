package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPEATABLE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPEATABLE_INTERVAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UPDATE_INDEX;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.EventList;
import seedu.address.model.project.Project;
import seedu.address.model.task.Interval;
import seedu.address.model.task.repeatable.Event;

/**
 * Updates an event inside a project.
 */
public class UpdateEventCommand extends Command {

    public static final String COMMAND_WORD = "updateE";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the event "
            + "identified by it's index number within the displayed project.\n"
            + "Parameters: PROJECT_INDEX (must be a positive integer) "
            + PREFIX_UPDATE_INDEX + "EVENT_INDEX "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_REPEATABLE_INTERVAL + "INTERVAL] "
            + "[" + PREFIX_REPEATABLE_DATE + "REPEATABLE_DATE]\n"
            + "Example:\n" + COMMAND_WORD + " 1 "
            + PREFIX_UPDATE_INDEX + "1 "
            + PREFIX_DESCRIPTION + "Project meeting "
            + PREFIX_REPEATABLE_DATE + "24-04-2021";

    public static final String MESSAGE_UPDATE_EVENT_SUCCESS = "Edited event: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in this project.";

    private final Index projectIndex;
    private final Index targetEventIndex;
    private final UpdateEventDescriptor updateEventDescriptor;

    /**
     * Constructs an {@code updateEventCommand} with a {@code projectIndex}.
     * {@code targetEventIndex} and an {@code Event}.
     *
     * @param projectIndex     index of the project in the filtered project list.
     * @param targetEventIndex index of the {@code Event} in the {@code EventList} to update.
     * @param updateEventDescriptor details to edit {@code Event} with.
     */
    public UpdateEventCommand(Index projectIndex, Index targetEventIndex, UpdateEventDescriptor updateEventDescriptor) {
        requireAllNonNull(projectIndex, targetEventIndex, updateEventDescriptor);

        this.projectIndex = projectIndex;
        this.targetEventIndex = targetEventIndex;
        this.updateEventDescriptor = new UpdateEventDescriptor(updateEventDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (projectIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }
        Project projectToUpdate = lastShownList.get(projectIndex.getZeroBased());
        EventList events = projectToUpdate.getEvents();

        if (targetEventIndex.getZeroBased() >= events.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToUpdate = events.getEvent(targetEventIndex.getZeroBased());
        Event updatedEvent = createUpdatedEvent(eventToUpdate, updateEventDescriptor);

        if (events.hasEvent(updatedEvent) && !eventToUpdate.equals(updatedEvent)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        projectToUpdate.setEvent(targetEventIndex.getZeroBased(), updatedEvent);
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_UPDATE_EVENT_SUCCESS, updatedEvent));
    }

    /**
     * Creates and returns a {@code Event} with the details of {@code eventToUpdate}
     * edited with {@code updatedEventDescriptor}.
     */
    private static Event createUpdatedEvent(Event eventToEdit, UpdateEventDescriptor updateEventDescriptor) {
        assert eventToEdit != null;

        String updatedDescription = updateEventDescriptor.getDescription().orElse(eventToEdit.getDescription());
        Interval updatedInterval = updateEventDescriptor.getInterval().orElse(eventToEdit.getRecurrence());
        LocalDate updatedDate = updateEventDescriptor.getDate().orElse(eventToEdit.getAt());

        return new Event(updatedDescription, updatedInterval, updatedDate);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateEventCommand)) {
            return false;
        }

        // state check
        UpdateEventCommand e = (UpdateEventCommand) other;
        return projectIndex.equals(e.projectIndex)
                && targetEventIndex.equals(e.targetEventIndex)
                && updateEventDescriptor.equals(e.updateEventDescriptor);
    }

    /**
     * Stores the details to update the {@code Event}. Each non-empty field value will replace the
     * corresponding field value of the {@code Event}.
     */
    public static class UpdateEventDescriptor {
        private String description;
        private Interval interval;
        private LocalDate date;

        public UpdateEventDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public UpdateEventDescriptor(UpdateEventDescriptor toCopy) {
            setDescription(toCopy.description);
            setInterval(toCopy.interval);
            setDate(toCopy.date);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, interval, date);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setInterval(Interval interval) {
            this.interval = interval;
        }

        public Optional<Interval> getInterval() {
            return Optional.ofNullable(interval);
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public Optional<LocalDate> getDate() {
            return Optional.ofNullable(date);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdateEventDescriptor)) {
                return false;
            }

            // state check
            UpdateEventDescriptor e = (UpdateEventDescriptor) other;

            return getDescription().equals(e.getDescription())
                    && getInterval().equals(e.getInterval())
                    && getDate().equals(e.getDate());
        }
    }
}
