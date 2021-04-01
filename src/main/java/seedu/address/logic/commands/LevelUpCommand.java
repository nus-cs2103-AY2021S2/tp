package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.functions.PersonLevelUpFunction;

public class LevelUpCommand extends Command {
    public static final String COMMAND_WORD = "levelup";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Advance all students by one level."
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Advanced all students by one level";
    /**
     * Creates a LevelUpCommand object.
     */
    public LevelUpCommand() {
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateTransformedPersonList(new PersonLevelUpFunction());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LevelUpCommand); // instanceof handles nulls
    }
}

