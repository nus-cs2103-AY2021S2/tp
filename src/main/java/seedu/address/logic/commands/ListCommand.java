package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SESSIONS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS_PERSONS = "Listed all persons";
    public static final String MESSAGE_SUCCESS_SESSIONS = "Listed all sessions";
    public static final String MESSAGE_EMPTY_PERSON_LIST = "The list of persons is empty!";
    public static final String MESSAGE_EMPTY_SESSION_LIST = "The list of sessions is empty!";

    private final String type;
    public ListCommand(String listType) {
        type = listType;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (type.equals("persons")) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

            return model.emptyPersonList()?
                    new CommandResult(MESSAGE_EMPTY_PERSON_LIST)
                    : new CommandResult(MESSAGE_SUCCESS_PERSONS);
        } else {
            model.updateFilteredSessionList(PREDICATE_SHOW_ALL_SESSIONS);
            return model.emptySessionList()?
                    new CommandResult(MESSAGE_EMPTY_SESSION_LIST)
                    : new CommandResult(MESSAGE_SUCCESS_SESSIONS);
        }
    }
}
