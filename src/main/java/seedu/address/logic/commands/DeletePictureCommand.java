package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Picture;

public class DeletePictureCommand extends Command {

    public static final String COMMAND_WORD = "del-picture";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the picture of the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PICTURE_SUCCESS = "Deleted picture for %1$s";

    public static final String MESSAGE_DELETE_PICTURE_NO_PICTURE_FOUND = "No picture found for %1$s";

    private final Index targetIndex;

    public DeletePictureCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());
        Name name = personToEdit.getName();

        Optional<Picture> pictureOptional = personToEdit.getPicture();
        if (pictureOptional.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_DELETE_PICTURE_NO_PICTURE_FOUND, name));
        }

        Person editedPerson = personToEdit.deletePicture();

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DELETE_PICTURE_SUCCESS, name));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePictureCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePictureCommand) other).targetIndex)); // state check
    }
}
