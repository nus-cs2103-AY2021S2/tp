package seedu.address.logic.commands.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medical.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment with a patient "
            + "by the index number used in the displayed person list. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DATE + "DATE] \n" + Appointment.MESSAGE_CONSTRAINTS_DATE_FORMAT
            + "\n Example: " + COMMAND_WORD + " 1 " + PREFIX_DATE + "24051800";

    public static final String MESSAGE_SUCCESS = "Appointment added: %s";

    private final Index index;
    private final LocalDateTime date;

    /**
     * @param index of the person in the filtered person list to edit
     * @param date  details to edit the person with
     */
    public AddAppointmentCommand(Index index, LocalDateTime date) {
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
        Appointment appointment = new Appointment(date);
        Person editedPerson = createPersonWithAppointment(person, appointment);

        model.setPerson(person, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, appointment));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createPersonWithAppointment(Person personToEdit, Appointment appt) {
        assert personToEdit != null;
        // copy everything
        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Set<Tag> updatedTags = personToEdit.getTags();
        List<Appointment> updatedAppointments = personToEdit.getAppointments();
        Person p = new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedAppointments);
        p.addAppointment(appt);
        return p;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAppointmentCommand)) {
            return false;
        }

        // state check
        AddAppointmentCommand c = (AddAppointmentCommand) other;
        return index.equals(c.index)
                && date.equals(c.date);
    }
}
