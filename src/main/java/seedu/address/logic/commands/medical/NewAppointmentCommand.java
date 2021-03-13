package seedu.address.logic.commands.medical;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medical.Appointment;
import seedu.address.model.person.Person;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class NewAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DATE + "dd mm yyyy hh mm] ";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";

    private final Index index;
    private final LocalDateTime date;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public NewAppointmentCommand(Index index, LocalDateTime date) {
        requireNonNull(index);
        requireNonNull(date);
        this.index = index;
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(index.getZeroBased());
        Appointment toAdd = new Appointment(person, date);
        model.addAppointment(toAdd);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS); // todo: what is this line for?????
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
