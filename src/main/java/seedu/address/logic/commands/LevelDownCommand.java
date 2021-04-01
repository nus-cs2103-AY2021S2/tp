package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.functions.PersonLevelDownFunction;
import seedu.address.model.person.functions.PersonLevelUpFunction;

import java.util.List;

public class LevelDownCommand extends Command {
    public static final String COMMAND_WORD = "leveldown";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Demotes all students by one level."
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Demoted all students by one level.";
    public String alternativeMessage = "Demoted all students by one level except exclusions: ";

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
            updateAlternativeMessage(model);
            return new CommandResult(alternativeMessage);
        }
    }

    private void updateAlternativeMessage(Model model) {
        for (int i = 0; i < indices.size(); i++) {
            Index index = indices.get(i);
            alternativeMessage = alternativeMessage + "\n"
                    + model.getTransformedPersonList().get(index.getZeroBased());
        }
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


