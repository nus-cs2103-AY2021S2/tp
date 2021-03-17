package seedu.address.logic.commands.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medical.Appointment;
import seedu.address.model.medical.Section;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class OpenMedicalRecordCommand extends Command {

    public static final String COMMAND_WORD = "mrec";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens an editor for a medical report for a patient ";

    public static final String MESSAGE_SUCCESS = "Editor opened: %s";

    private final Index index;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(index.toString(), false, true, false);
    }

    /**
     * @param index of the patient in the filtered patient list to edit
     */
    public OpenMedicalRecordCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }
}
