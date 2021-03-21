package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.iscam.commons.core.Messages;
import seedu.iscam.model.Model;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.meeting.MeetingContainsKeywordsPredicate;

/**
 * Finds and lists all clients in iscam book which details contain any of the argument keywords.
 * Keyword matching is case insensitive and can be partial-word.
 */
public class FindMeetingsCommand extends Command {

    public static final String COMMAND_WORD = "findmeet";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meetings which details contain any of the "
            + "specified keywords (whole word, case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Shawn 25-04-2023 10:00PM";

    private final MeetingContainsKeywordsPredicate predicate;

    public FindMeetingsCommand(MeetingContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    private String stringifyMeetings(ObservableList<Meeting> meetings) {
        String str = "";
        for (int i = 0; i < meetings.size(); i++) {
            str += meetings.get(i).toString() + "\n";
        }
        return str;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMeetingList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW,
                model.getFilteredMeetingList().size()) + "\n" + stringifyMeetings(model.getFilteredMeetingList()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof FindMeetingsCommand
                && predicate.equals(((FindMeetingsCommand) other).predicate));
    }
}
