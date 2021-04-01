package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.functions.PersonLevelDownFunction;

/**
 * Demotes all students by one level.
 * Students to be excluded can be added by specifying their indices.
 */
public class LevelDownCommand extends Command {
    public static final String COMMAND_WORD = "leveldown";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Demotes all students by one level."
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Demoted all students by one level.";
    private static final String ALTERNATIVE_SUCCESS_MESSAGE = "Demoted all students by one level except exclusions: ";

    public final List<Index> indices;
    private final boolean hasIndices;

    /**
     * Creates a LevelDownCommand object.
     */
    public LevelDownCommand(List<Index> indices) {
        this.indices = indices;
        hasIndices = !indices.isEmpty();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (hasIndices) {
            checkIndexWithinBounds(model);
        }
        model.filterIndicesThenTransformPersonList(indices, new PersonLevelDownFunction());
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
            if (index.getZeroBased() >= model.getTransformedPersonList().size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LevelDownCommand // instanceof handles nulls
                && indices.equals(((LevelDownCommand) other).indices));
    }

}


