package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class PolicyCommand extends Command {

    public static final String COMMAND_WORD = "policy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all policies associated a particular client.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    public PolicyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personPoliciesToDisplay = lastShownList.get(targetIndex.getZeroBased());

        if (!personPoliciesToDisplay.hasPolicies()) {
            return new CommandResult("This contact has no policies now!", false, true, false);
        }

        String policiesAndUrls = personPoliciesToDisplay.getAllPoliciesAndUrls();
        return new CommandResult(policiesAndUrls, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PolicyCommand // instanceof handles nulls
                && targetIndex.equals(((PolicyCommand) other).targetIndex)); // state check
    }
}
