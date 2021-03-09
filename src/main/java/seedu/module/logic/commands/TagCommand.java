package seedu.module.logic.commands;

import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.tag.Tag;

import static seedu.module.commons.util.CollectionUtil.requireAllNonNull;

public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a tag to task identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "t/[TAG]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "t/Midterm";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Tag command not implemented yet";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";

    private final Index index;
    private final Tag tag;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param tag of the person to be added
     */
    public TagCommand(Index index, String tag) {
        requireAllNonNull(index, tag);

        this.index = index;
        this.tag = new Tag(tag);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCommand)) {
            return false;
        }

        // state check
        TagCommand e = (TagCommand) other;
        return index.equals(e.index)
                && tag.equals(e.tag);
    }
}
