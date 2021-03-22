package seedu.address.logic.commands.editcommand;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_EVENT;

import java.time.LocalDateTime;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Description;

public class EditEventCommand extends EditCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a event in RemindMe."
            + "Parameters: "
            + "EVENT INDEX "
            + "[" + PREFIX_GENERAL_EVENT + "NEW EVENT DESCRIPTION] "
            + "[" + PREFIX_DATE + "NEW EVENT DATE]\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_GENERAL_EVENT + "Different description OR"
            + PREFIX_DATE + "29/10/2021 1000";

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
        } else if (isNull(eventEdit) && isNull(dateEdit)) {
            throw new CommandException(MESSAGE_EDIT_BOTH_PARTS);
        } else {
            throw new CommandException(MESSAGE_NULL_EDIT);
        }

        GeneralEvent edited = model.getEvent(toEditIndex);

        return new CommandResult(String.format(MESSAGE_SUCCESS, edited));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EditEventCommand)
                && eventEdit.equals(((EditEventCommand) other).eventEdit)
                && dateEdit.equals(((EditEventCommand) other).dateEdit)
                && toEditIndex == ((EditEventCommand) other).toEditIndex;
    }
}
