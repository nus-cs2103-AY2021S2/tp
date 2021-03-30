package seedu.address.logic.commands.favouritecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.tutorcommands.EditCommand.EditPersonDescriptor;
import static seedu.address.logic.commands.tutorcommands.EditCommand.createEditedPerson;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.ViewTutorPredicate;

/**
 * Removes favourite from a Tutor
 */
public class UnfavouriteCommand extends Command {

    public static final String COMMAND_WORD = "unfavourite";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " ID";

    public static final String MESSAGE_SUCCESS = "Unfavourite tutor: %s";

    public static final String MESSAGE_INVALID_INDEX = "Invalid index %d";

    private static final String MESSAGE_AlREADY_UNFAVOURITE = "Tutor is already not a favourite";

    private final Index targetIndex;

    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param targetIndex of Tutor to be unfavourite
     * @param editPersonDescriptor with an unfavourite descriptor
     */
    public UnfavouriteCommand(Index targetIndex, EditPersonDescriptor editPersonDescriptor) {
        this.targetIndex = targetIndex;
        this.editPersonDescriptor = editPersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> tutorList = model.getFilteredPersonList();

        ArrayList<Person> tutorPredicateList = new ArrayList<>(tutorList);

        if (targetIndex.getZeroBased() >= tutorList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_INDEX, targetIndex.getZeroBased()));
        }

        Person person = tutorList.get(targetIndex.getZeroBased());
        if (!person.isFavourite()) {
            throw new CommandException(MESSAGE_AlREADY_UNFAVOURITE);
        }

        Person editedPerson = createEditedPerson(person, editPersonDescriptor);

        model.setPerson(person, editedPerson);
        model.updateFilteredPersonList(new ViewTutorPredicate(tutorPredicateList));

        return new CommandResult(String.format(MESSAGE_SUCCESS, person.getName()));
    }
}
