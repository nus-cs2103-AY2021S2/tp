package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.DatesBook;
import seedu.address.model.LessonBook;
import seedu.address.model.Model;

/**
 * Clears TutorsPet.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "TutorsPet has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        model.setDatesBook(new DatesBook());
        model.setLessonBook(new LessonBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
