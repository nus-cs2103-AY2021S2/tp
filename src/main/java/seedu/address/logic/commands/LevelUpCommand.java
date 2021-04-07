package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXCLUDE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.functions.PersonLevelUpFunction;

/**
 * Advances all students by one level.
 * Students to be excluded can be added by specifying their indices.
 */
public class LevelUpCommand extends Command {
    public static final String COMMAND_WORD = "levelup";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Advance all students by one level by default. To exclude students, add their index numbers after "
            + "the ex/ prefix. The space after the ex/ prefix cannot be left blank.\n"
            + "Parameters: " + PREFIX_EXCLUDE + " INDEX... (optional, must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + "\n"
            + COMMAND_WORD + " " + PREFIX_EXCLUDE + "2\n"
            + COMMAND_WORD + " " + PREFIX_EXCLUDE + "1 4";

    public static final String MESSAGE_SUCCESS = "Advanced all students by one level.";
    private static final String ALTERNATIVE_SUCCESS_MESSAGE = "Advanced all students by one level except exclusions: ";

    public final List<Index> indices;
    private final boolean hasIndices;
    /**
     * Creates a LevelUpCommand object.
     */
    public LevelUpCommand(List<Index> indices) {
        this.indices = indices;
        hasIndices = !indices.isEmpty();

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (hasIndices) {
            checkIndexWithinBounds(model);
        }
        model.filterIndicesThenTransformPersonList(indices, new PersonLevelUpFunction());
        // model.updateTransformedPersonList(new PersonLevelUpFunction(indices));
        if (!hasIndices) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            String excludedPeople = getExcludedPeopleDescriptors(model);
            return new CommandResult(ALTERNATIVE_SUCCESS_MESSAGE + excludedPeople);
        }
    }

    private String getExcludedPeopleDescriptors(Model model) {
        String result = "";
        for (int i = 0; i < indices.size(); i++) {
            Index index = indices.get(i);
            result = result + "\n" + model.getTransformedPersonList().get(index.getZeroBased());
        }
        return result;
    }

    private void checkIndexWithinBounds(Model model) throws CommandException {
        for (int i = 0; i < indices.size(); i++) {
            Index index = indices.get(i);
            if (index.getZeroBased() < 1) {
                throw new CommandException(Messages.MESSAGE_NEGATIVE_INDEX);
            }
            if (index.getZeroBased() >= model.getTransformedPersonList().size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LevelUpCommand // instanceof handles nulls
                && indices.equals(((LevelUpCommand) other).indices));
    }
}

