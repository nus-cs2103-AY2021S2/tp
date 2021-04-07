package seedu.address.logic.commands.editcommand;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Description;

public class EditEventCommand extends EditCommand {
    public static final String MESSAGE_USAGE =
            "Missing necessary prefixes: [g/ or on/]\n"
            + "One of g/ or on/ must be used.\n"
            + "Event: edit INDEX [d/DESCRIPTION OR on/EVENT DATE]\n"
            + "Example: edit 1 g/Meet up";

    public static final String MESSAGE_SUCCESS = "Event edited: %1$s";
    public static final String MESSAGE_NO_EVENT = "This event does not exists in RemindMe";
    public static final String MESSAGE_EDIT_BOTH_PARTS = "Please delete this event and add a new one instead!";
    public static final String MESSAGE_NULL_EDIT = "Please input a valid edit!";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in RemindMe.";

    private final int toEditIndex;
    private final Description eventEdit;
    private final LocalDateTime dateEdit;

    /**
     * Creates an EditModuleCommand to edit the specified {@code Module}.
     */
    public EditEventCommand(int index, Description description, LocalDateTime date) {
        toEditIndex = index;
        eventEdit = description;
        dateEdit = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasEvent(toEditIndex)) {
            throw new CommandException(MESSAGE_NO_EVENT);
        }

        GeneralEvent target = model.getEvent(toEditIndex);

        if (isNull(eventEdit) && !isNull(dateEdit)) {
            target = target.setDate(dateEdit);
            if (model.hasEvent(target)) {
                throw new CommandException(MESSAGE_DUPLICATE_EVENT);
            }
            model.editEvent(toEditIndex, dateEdit);
        } else if (isNull(dateEdit) && !isNull(eventEdit)) {
            target = target.setDescription(eventEdit);
            if (model.hasEvent(target)) {
                throw new CommandException(MESSAGE_DUPLICATE_EVENT);
            }
            model.editEvent(toEditIndex, eventEdit);
        } else if (!isNull(eventEdit) && !isNull(dateEdit)) {
            throw new CommandException(MESSAGE_EDIT_BOTH_PARTS);
        } else {
            throw new CommandException(MESSAGE_NULL_EDIT);
        }

        GeneralEvent edited = model.getEvent(toEditIndex);
        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, edited));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EditEventCommand)) {
            return false;
        }
        boolean dateIsNull = isNull(dateEdit);
        boolean descriptionIsNull = isNull(eventEdit);
        boolean sameDate;
        boolean sameDescription;
        if (dateIsNull) {
            sameDate = isNull(((EditEventCommand) other).dateEdit);
        } else {
            sameDate = dateEdit.equals(((EditEventCommand) other).dateEdit);
        }
        if (descriptionIsNull) {
            sameDescription = isNull(((EditEventCommand) other).eventEdit);
        } else {
            sameDescription = eventEdit.equals(((EditEventCommand) other).eventEdit);
        }

        return other == this
                || (other instanceof EditEventCommand)
                && toEditIndex == ((EditEventCommand) other).toEditIndex
                && sameDate
                && sameDescription;
    }
}
