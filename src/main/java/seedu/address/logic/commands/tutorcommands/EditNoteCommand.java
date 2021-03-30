package seedu.address.logic.commands.tutorcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.tutorcommands.EditCommand.EditPersonDescriptor;
import static seedu.address.logic.commands.tutorcommands.EditCommand.createEditedPerson;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Edits a note of a Tutor in the TutorBook
 */
public class EditNoteCommand extends Command {

    public static final String COMMAND_WORD = "edit_note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + "INDEX" + " " + "NOTE";

    public static final String MESSAGE_SUCCESS = "Successfully edited note to Tutor: %s";

    public static final String MESSAGE_INVALID_INDEX = "Invalid index %d";

    public static final String MESSAGE_DOES_NOTE_HAVE_NOTES = "Tutor: %s does not have notes, try add_note command";

    private final Index targetIndex;

    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param targetIndex of the Tutor
     * @param editPersonDescriptor with a descriptor of the edited note
     */
    public EditNoteCommand (Index targetIndex, EditPersonDescriptor editPersonDescriptor) {
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
        if (!person.hasNotes()) {
            throw new CommandException(String.format(MESSAGE_DOES_NOTE_HAVE_NOTES, person.getName().toString()));
        }

        Person editedPerson = createEditedPerson(person, editPersonDescriptor);

        model.setPerson(person, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, person.getName().toString()));

    }
}
