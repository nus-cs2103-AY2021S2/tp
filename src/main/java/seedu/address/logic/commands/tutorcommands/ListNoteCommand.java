package seedu.address.logic.commands.tutorcommands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutor.Tutor;
import seedu.address.model.tutor.ViewTutorPredicate;

/**
 * List all Tutors with notes
 */
public class ListNoteCommand extends Command {

    public static final String COMMAND_WORD = "list_note";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all tutors with notes";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Tutor> tutorWithNotesList = new ArrayList<>();

        List<Tutor> tutorList = model.getTutorBook().getTutorList();

        for (Tutor p: tutorList) {
            if (p.hasNotes()) {
                tutorWithNotesList.add(p);
            }
        }

        ViewTutorPredicate predicate = new ViewTutorPredicate(tutorWithNotesList);

        model.updateFilteredTutorList(predicate);

        return new CommandResult(MESSAGE_SUCCESS);

    }
}
