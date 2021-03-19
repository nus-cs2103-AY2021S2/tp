package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Done;
import seedu.address.model.person.Person;


/**
 * Edits the details of an existing person in the address book.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task as done with a tick "
            + "by the index number used in the listing. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";


    public static final String MESSAGE_DONE_PERSON_SUCCESS = "The following Delivery Order has been checkmarked : %1$s";
    public static final String checkMark = "[✓]";
    private final Index targetIndex;
    private final Done done;

    /**
     * @param targetIndex of the person in the filtered person list to edit
     */
    public DoneCommand(Index targetIndex) {
        requireAllNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.done = new Done(checkMark);

    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());

        Person editedPerson;

        if (personToEdit.getDone().toString().equals("")) {
            editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(),
                    personToEdit.getEmail(), personToEdit.getAddress(),
                    personToEdit.getRemark(), personToEdit.getTags(), done);

        } else {
            editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(),
                    personToEdit.getEmail(), personToEdit.getAddress(),
                    personToEdit.getRemark(), personToEdit.getTags(), new Done(""));

        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DONE_PERSON_SUCCESS, personToEdit));
    }


}

