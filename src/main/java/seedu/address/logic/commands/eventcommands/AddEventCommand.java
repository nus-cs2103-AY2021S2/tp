package seedu.address.logic.commands.eventcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DATE_CLASH_ADD;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_END_TIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_HOURS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_START_TIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TIME_MINUTES;
import static seedu.address.commons.core.Messages.MESSAGE_TIME_FROM_GREATER_THAN;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.event.Event;

/**
 * An abstract AddEventCommand to handle datetime exceptions.
 */
public abstract class AddEventCommand extends Command {

    protected final Event toAdd;

    /**
     * Primary constructor to accept an appointment and add it to appointment list.
     *
     * @param event Appointment to add
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    public void validateDateTime(Model model) throws CommandException {
        requireNonNull(model);

        AppointmentDateTime timeFrom = toAdd.getTimeFrom();
        AppointmentDateTime timeTo = toAdd.getTimeTo();

        assert timeFrom != null && timeTo != null;

        /* Start Time must be 6:00 AM onwards */
        if (timeFrom.isInvalidStartTime()) {
            throw new CommandException(MESSAGE_INVALID_START_TIME);
        }

        /* End Time must not be after 11:00 PM */
        if (timeTo.isInvalidEndTime()) {
            throw new CommandException(MESSAGE_INVALID_END_TIME);
        }

        /* End Time must not be after 11:00 PM */
        if (timeTo.toTime().getHour() - timeFrom.toTime().getHour() > 8) {
            throw new CommandException(MESSAGE_INVALID_HOURS);
        }

        /* Time must be in 30 minutes or 60 minutes block */
        if (!timeFrom.isValidMinutes() || !timeTo.isValidMinutes()) {
            throw new CommandException(MESSAGE_INVALID_TIME_MINUTES);
        }

        /* TIMEFROM must be before TIMETO */
        if (toAdd.isInvalidTimeRange()) {
            throw new CommandException(MESSAGE_TIME_FROM_GREATER_THAN);
        }

        /* TIMEFROM and TIMENOW must be in the future */
        if (toAdd.getTimeFrom().isBeforeNow() && toAdd.getTimeTo().isBeforeNow()) {
            throw new CommandException(MESSAGE_INVALID_DATE);
        }

        /* The new Event cannot clash with existing appointments & schedules */
        if (model.hasClashingDateTime(toAdd)) {
            throw new CommandException(MESSAGE_DATE_CLASH_ADD);
        }
    }
}
