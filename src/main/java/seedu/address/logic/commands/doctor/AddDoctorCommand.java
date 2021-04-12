package seedu.address.logic.commands.doctor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_ADD_DOCTOR_SUCCESS;
import static seedu.address.commons.core.Messages.MESSAGE_ADD_DUPLICATE_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Doctor;

/**
 * Adds a person to the address book.
 */
public class AddDoctorCommand extends Command {

    public static final String COMMAND_WORD = "add-doctor";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a doctor to the doctor records. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John "
            + PREFIX_TAG + "psychologist "
            + PREFIX_TAG + "pharmacist";

    private final Doctor toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Doctor}
     */
    public AddDoctorCommand(Doctor doctor) {
        requireNonNull(doctor);
        toAdd = doctor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasDoctor(toAdd)) {
            throw new CommandException(MESSAGE_ADD_DUPLICATE_DOCTOR);
        }

        if (model.hasConflictingUuid(toAdd.getUuid())) {
            Doctor newUuidDoctor = toAdd;
            while (model.hasConflictingUuid(newUuidDoctor.getUuid())) {
                newUuidDoctor = new Doctor(toAdd.getName(), toAdd.getTags());
            }
            model.addDoctor(newUuidDoctor);
            return new CommandResult(String.format(MESSAGE_ADD_DOCTOR_SUCCESS, toAdd));
        }

        model.addDoctor(toAdd);
        return new CommandResult(String.format(MESSAGE_ADD_DOCTOR_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDoctorCommand // instanceof handles nulls
                && toAdd.equals(((AddDoctorCommand) other).toAdd));
    }
}
