package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_WEEKLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ViewProjectAndOverviewUiCommand;
import seedu.address.model.Model;
import seedu.address.model.project.EventList;
import seedu.address.model.project.Project;
import seedu.address.model.task.repeatable.Event;

/**
 * Updates an event inside a project.
 */
public class UpdateEventCommand extends Command {

    public static final String COMMAND_WORD = "updateE";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the event of a project"
            + "identified by 2 index numbers: project index and event index.\n"
            + "Parameters: PROJECT_INDEX "
            + PREFIX_INDEX + "EVENT_INDEX "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_EVENT_DATE + "DATE] "
            + "[" + PREFIX_EVENT_TIME + "TIME]"
            + "[" + PREFIX_EVENT_WEEKLY + "REPEATS_WEEKLY]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INDEX + "1 "
            + PREFIX_DESCRIPTION + "Project meeting "
            + PREFIX_EVENT_DATE + "24-04-2021 "
            + PREFIX_EVENT_TIME + "1830 "
            + PREFIX_EVENT_WEEKLY + "N";

    public static final String MESSAGE_UPDATE_EVENT_SUCCESS = "Updated event: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in this project.";
    public static final String MESSAGE_UNCHANGED_EVENT = "This event already has this description, date,"
            + " time and repeat weekly.";

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

        if (eventToUpdate.equals(updatedEvent)) {
            throw new CommandException(MESSAGE_UNCHANGED_EVENT);
        }

        projectToUpdate.setEvent(targetEventIndex.getZeroBased(), updatedEvent);

        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_UPDATE_EVENT_SUCCESS, updatedEvent),
                new ViewProjectAndOverviewUiCommand(projectIndex));
    }

    /**
     * Creates and returns a {@code Event} with the details of {@code eventToUpdate}
     * edited with {@code updatedEventDescriptor}.
     */
    public static Event createUpdatedEvent(Event eventToEdit, UpdateEventDescriptor updateEventDescriptor) {
        assert eventToEdit != null;

        String updatedDescription = updateEventDescriptor.getDescription().orElse(eventToEdit.getDescription());
        LocalDate updatedDate = updateEventDescriptor.getDate().orElse(eventToEdit.getDate());
        LocalTime updatedTime = updateEventDescriptor.getTime().orElse(eventToEdit.getTime());
        Boolean updatedIsWeekly = updateEventDescriptor.getIsWeekly().orElse(eventToEdit.getIsWeekly());


        return new Event(updatedDescription, updatedDate, updatedTime, updatedIsWeekly);
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
        private LocalDate date;
        private LocalTime time;
        private Boolean isWeekly;

        public UpdateEventDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public UpdateEventDescriptor(UpdateEventDescriptor toCopy) {
            setDescription(toCopy.description);
            setDate(toCopy.date);
            setTime(toCopy.time);
            setIsWeekly(toCopy.isWeekly);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, date, time, isWeekly);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public Optional<LocalDate> getDate() {
            return Optional.ofNullable(date);
        }

        public void setTime(LocalTime time) {
            this.time = time;
        }

        public Optional<LocalTime> getTime() {
            return Optional.ofNullable(time);
        }

        public void setIsWeekly(Boolean isWeekly) {
            this.isWeekly = isWeekly;
        }

        public Optional<Boolean> getIsWeekly() {
            return Optional.ofNullable(isWeekly);
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
                    && getDate().equals(e.getDate())
                    && getTime().equals(e.getTime())
                    && getIsWeekly().equals(e.getIsWeekly());
        }
    }
}
