package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonIdPredicate;
import seedu.address.model.session.SessionId;
import seedu.address.model.session.SessionStudentPredicate;

public class ViewPersonCommand extends Command {
    public static final String COMMAND_WORD = "view_person";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View a specific person by person ID.\n"
            + "Parameters: PERSON_ID\n"
            + "Example: " + COMMAND_WORD + " s/1";

    public static final String MESSAGE_SUCCESS = "Displayed the relevant person.\n"
                                                + "Left Panel shows the student/tutor information.\n"
                                                + "Right Panel shows all the sessions the student/tutor currently has.";
    private final PersonIdPredicate predicate;

    public ViewPersonCommand(PersonIdPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getUnfilteredPersonList();
        Optional<Person> personToView = lastShownList.stream()
                .filter(x-> x.getPersonId().equals(predicate.getPersonId())).findAny();

        if (personToView.isPresent()) {
            List<SessionId> sessionList = personToView.get().getSessions();
            SessionStudentPredicate sessionStudentPredicate = new SessionStudentPredicate(sessionList);
            model.updateFilteredPersonList(predicate);
            model.updateFilteredSessionList(sessionStudentPredicate);
            model.updateFilteredPersonList(predicate);
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewPersonCommand // instanceof handles nulls
                && predicate.equals(((ViewPersonCommand) other).predicate)); // state check
    }
}
