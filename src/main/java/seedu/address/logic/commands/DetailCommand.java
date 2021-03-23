package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing student in TutorsPet.
 */
public class DetailCommand extends Command {

    public static final String COMMAND_WORD = "detail";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display the details of the student identified ";
    public static final String MESSAGE_DISPLAY_PERSON_SUCCESS = "Displayed Student: %1$s";


    private final Index index;

    /**
     * @param index                of the student in the filtered student list to display details
     */
    public DetailCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDisplay = lastShownList.get(index.getZeroBased());

        model.setSelectedPerson(personToDisplay);

        return new CommandResult(String.format(MESSAGE_DISPLAY_PERSON_SUCCESS, personToDisplay));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailCommand // instanceof handles nulls
                && index.equals(((DetailCommand) other).index)); // state check
    }
}