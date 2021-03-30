package seedu.address.logic.commands.tutorcommands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.tutorcommands.EditCommand.createEditedPerson;
import static seedu.address.logic.commands.tutorcommands.EditCommand.EditPersonDescriptor;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;


public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "add_note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + "INDEX" + " " + "NOTE";

    public static final String MESSAGE_SUCCESS = "Successfully added note to Tutor: %s";

    public static final String MESSAGE_INVALID_INDEX = "Invalid index %d";

    public static final String MESSAGE_ALREADY_HAVE_NOTES = "Tutor: %s already have notes, try edit_note command";

    private final Index targetIndex;

    private final EditPersonDescriptor editPersonDescriptor;

    public AddNoteCommand (Index targetIndex, EditPersonDescriptor editPersonDescriptor) {
        this.targetIndex = targetIndex;
        this.editPersonDescriptor = editPersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> tutorList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= tutorList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_INDEX, targetIndex.getZeroBased()));
        }

        Person person = tutorList.get(targetIndex.getZeroBased());
        if (person.hasNotes()) {
            throw new CommandException(String.format(MESSAGE_ALREADY_HAVE_NOTES, person.getName().toString()));
        }

        Person editedPerson = createEditedPerson(person, editPersonDescriptor);

        model.setPerson(person, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, person.getName().toString()));

    }
}
