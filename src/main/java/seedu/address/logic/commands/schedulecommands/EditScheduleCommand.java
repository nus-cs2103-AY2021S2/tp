package seedu.address.logic.commands.schedulecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.commons.core.Messages.MESSAGE_TIME_FROM_GREATER_THAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULE;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.common.Description;
import seedu.address.model.common.Title;
import seedu.address.model.schedule.Schedule;

/**
 * Edits the details of an existing schedule in the schedule list.
 */
public class EditScheduleCommand extends Command {

    public static final String COMMAND_WORD = "edit_schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the schedule identified "
            + "by the index number used in the displayed schedule list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TIME_FROM + "TIME FROM] "
            + "[" + PREFIX_TIME_TO + "TIME TO] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TITLE + "Science Tuition Homework "
            + PREFIX_DATE + "2021-3-20";

    public static final String MESSAGE_EDIT_SCHEDULE_SUCCESS = "Edited Schedule: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SCHEDULE = "This Schedule already exists.";

    private final Index index;
    private final EditScheduleDescriptor editScheduleDescriptor;

    /**
     * @param index of the schedule in the filtered schedule list to edit
     */
    public EditScheduleCommand(Index index, EditScheduleDescriptor editScheduleDescriptor) {
        requireNonNull(index);
        requireNonNull(editScheduleDescriptor);

        this.index = index;
        this.editScheduleDescriptor = editScheduleDescriptor;
    }

    /**
     * Creates and returns a {@code Schedule} with the details of {@code scheduleToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Schedule createEditedSchedule(Schedule scheduleToEdit,
                                                 EditScheduleDescriptor editScheduleDescriptor) {
        assert scheduleToEdit != null;

        Title updatedTitle =
                editScheduleDescriptor.getTitle().orElse(scheduleToEdit.getTitle());

        AppointmentDateTime updatedTimeFrom = null, updatedTimeTo = null;

        Optional<AppointmentDateTime> optionalUpdatedTimeFrom = editScheduleDescriptor.getTimeFrom();
        Optional<AppointmentDateTime> optionalUpdatedTimeTo = editScheduleDescriptor.getTimeTo();

        if (optionalUpdatedTimeFrom.isPresent() && optionalUpdatedTimeTo.isPresent()) {
            updatedTimeFrom = optionalUpdatedTimeFrom.get();
            updatedTimeTo = optionalUpdatedTimeTo.get();
        }

        if (optionalUpdatedTimeFrom.isPresent() && optionalUpdatedTimeTo.isEmpty()) {
            updatedTimeFrom = optionalUpdatedTimeFrom.get();
            LocalDate newDate = updatedTimeFrom.value.toLocalDate();
            updatedTimeTo = new AppointmentDateTime(newDate, scheduleToEdit.getTimeTo().value.toLocalTime());
        }

        if (optionalUpdatedTimeTo.isPresent() && optionalUpdatedTimeFrom.isEmpty()) {
            updatedTimeTo = optionalUpdatedTimeTo.get();
            LocalDate newDate = updatedTimeTo.value.toLocalDate();
            updatedTimeFrom = new AppointmentDateTime(newDate, scheduleToEdit.getTimeFrom().value.toLocalTime());
        }

        if (editScheduleDescriptor.getDateOnly().isPresent()) {
            LocalDate newDate = editScheduleDescriptor.getDateOnly().get();
            updatedTimeFrom = new AppointmentDateTime(newDate, scheduleToEdit.getTimeFrom().value.toLocalTime());
            updatedTimeTo = new AppointmentDateTime(newDate, scheduleToEdit.getTimeTo().value.toLocalTime());
        }

        if (editScheduleDescriptor.getTimeFromOnly().isPresent()) {
            updatedTimeFrom = new AppointmentDateTime(scheduleToEdit.getTimeFrom().value.toLocalDate(),
                    editScheduleDescriptor.getTimeFromOnly().get());
        }

        if (editScheduleDescriptor.getTimeToOnly().isPresent()) {
            updatedTimeTo = new AppointmentDateTime(scheduleToEdit.getTimeFrom().value.toLocalDate(),
                    editScheduleDescriptor.getTimeToOnly().get());
        }

        Description updatedDescription =
                editScheduleDescriptor.getDescription().orElse(scheduleToEdit.getDescription());

        assert updatedTimeTo != null && updatedTimeFrom != null;
        return new Schedule(updatedTitle, updatedTimeFrom, updatedTimeTo, updatedDescription);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Schedule> lastShownList = model.getFilteredScheduleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
        }

        Schedule scheduleToEdit = lastShownList.get(index.getZeroBased());
        Schedule editedSchedule = createEditedSchedule(scheduleToEdit, editScheduleDescriptor);

        if (editedSchedule.getTimeFrom().value.isBefore(LocalDateTime.now())) {
            throw new CommandException(MESSAGE_INVALID_DATE);
        }

        if (!editedSchedule.getTimeFrom().isTimeFromValid(editedSchedule.getTimeTo())) {
            throw new CommandException(MESSAGE_TIME_FROM_GREATER_THAN);
        }

        if (!scheduleToEdit.equals(editedSchedule) && model.hasSchedule(editedSchedule)) {
            throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
        }

        model.setSchedule(scheduleToEdit, editedSchedule);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULE);
        return new CommandResult(String.format(MESSAGE_EDIT_SCHEDULE_SUCCESS, editedSchedule));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditScheduleCommand)) {
            return false;
        }

        // state check
        EditScheduleCommand e = (EditScheduleCommand) other;
        return index.equals(e.index) && editScheduleDescriptor.equals(e.editScheduleDescriptor);
    }

    /**
     * Stores the details to edit the schedule with. Each non-empty field value will replace the
     * corresponding field value of the schedule.
     */
    public static class EditScheduleDescriptor {
        private Title title;
        private AppointmentDateTime timeFrom;
        private AppointmentDateTime timeTo;
        private Description description;

        private LocalDate dateOnly;
        private LocalTime timeFromOnly;
        private LocalTime timeToOnly;

        public EditScheduleDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditScheduleDescriptor(EditScheduleDescriptor toCopy) {
            setTitle(toCopy.title);
            setTimeFrom(toCopy.timeFrom);
            setTimeTo(toCopy.timeTo);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, timeFrom, timeTo, description,
                    timeFromOnly, timeToOnly, dateOnly);
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<LocalDate> getDateOnly() {
            return Optional.ofNullable(this.dateOnly);
        }

        public void setDateOnly(LocalDate dateOnly) {
            this.dateOnly = dateOnly;
        }

        public Optional<AppointmentDateTime> getTimeFrom() {
            return Optional.ofNullable(this.timeFrom);
        }

        public void setTimeFrom(AppointmentDateTime timeFrom) {
            this.timeFrom = timeFrom;
        }

        public Optional<LocalTime> getTimeFromOnly() {
            return Optional.ofNullable(timeFromOnly);
        }

        public void setTimeFromOnly(LocalTime timeFromOnly) {
            this.timeFromOnly = timeFromOnly;
        }

        public Optional<AppointmentDateTime> getTimeTo() {
            return Optional.ofNullable(this.timeTo);
        }

        public void setTimeTo(AppointmentDateTime timeTo) {
            this.timeTo = timeTo;
        }

        public Optional<LocalTime> getTimeToOnly() {
            return Optional.ofNullable(timeToOnly);
        }

        public void setTimeToOnly(LocalTime timeToOnly) {
            this.timeToOnly = timeToOnly;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditScheduleDescriptor)) {
                return false;
            }

            // state check
            EditScheduleDescriptor e = (EditScheduleDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getTimeFrom().equals(e.getTimeFrom())
                    && getTimeTo().equals(e.getTimeTo())
                    && getDescription().equals(e.getDescription());
        }
    }
}
