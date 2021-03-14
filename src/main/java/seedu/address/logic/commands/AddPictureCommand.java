package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Picture;

public class AddPictureCommand extends Command {

    public static final String COMMAND_WORD = "add-picture";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a picture to the person identified "
            + "by the index number used in the last person listing. \n"
            + "Parameters: INDEX (must be a positive integer) PICTURE_FILE_PATH \n"
            + "Example: " + COMMAND_WORD + " 1 /Users/john/img_of_friend.jpg";

    public static final String MESSAGE_ADD_PICTURE_SUCCESS = "Added picture for %1$s";

    private final Index index;
    private final Path filePath;

    /**
     * @param index of the person in the filtered person list to add a picture to
     * @param filePath of the picture to add
     */
    public AddPictureCommand(Index index, Path filePath) {
        this.index = index;
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        ReadOnlyUserPrefs userPrefs = model.getUserPrefs();
        Path pictureDir = userPrefs.getPictureStorageDirPath();
        // TODO: Rename to person's name for easier editing
        Path newFilePath = pictureDir.toAbsolutePath().resolve(filePath.getFileName());

        try {
            FileUtil.copyFile(filePath, newFilePath);
        } catch (IOException e) {
            throw new CommandException("Error copying file to picture storage directory");
        }

        Picture picture = new Picture(newFilePath);
        Person editedPerson = personToEdit.setPicture(picture);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ADD_PICTURE_SUCCESS, editedPerson.getName()));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof AddPictureCommand)) {
            return false;
        }

        AddPictureCommand that = (AddPictureCommand) o;
        return index.equals(that.index) && filePath.equals(that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, filePath);
    }
}
