package seedu.address.logic.commands;

import java.nio.file.Path;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class AddPictureCommand extends Command {

    public static final String COMMAND_WORD = "add-picture";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a picture to the person identified "
            + "by the index number used in the last person listing. \n"
            + "Parameters: INDEX (must be a positive integer) PICTURE FILE \n"
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


        return new CommandResult(String.format(MESSAGE_ADD_PICTURE_SUCCESS, "fake name"));
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
