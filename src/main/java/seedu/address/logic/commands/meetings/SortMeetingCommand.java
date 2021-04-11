package seedu.address.logic.commands.meetings;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_DIRECTION;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingSortDirection;
import seedu.address.model.meeting.MeetingSortOption;


public class SortMeetingCommand extends Command {

    public static final List<String> ALL_OPTIONS = Arrays.stream(MeetingSortOption.values())
            .map(meetingSortOption -> meetingSortOption.getValue()).collect(Collectors.toList());
    public static final List<String> ALL_DIRECTIONS = Arrays.stream(MeetingSortDirection.values())
            .map(meetingSortDirection -> meetingSortDirection.getValue()).collect(Collectors.toList());

    public static final String COMMAND_WORD = "sortm";

    public static final String MESSAGE_USAGE = "Please do: " + COMMAND_WORD + " "
            + PREFIX_SORT_BY + ALL_OPTIONS.toString() + " "
            + PREFIX_SORT_DIRECTION + ALL_DIRECTIONS.toString();
    private Comparator<Meeting> meetingComparator;

    private MeetingSortOption meetingSortOption;
    private MeetingSortDirection meetingSortDirection;
    /**
     * The constructor of sort meeting command.
     */
    public SortMeetingCommand(MeetingSortOption sortOption, MeetingSortDirection sortDirection) {
        switch (sortOption) {
        case NAME:
            meetingComparator = Comparator.comparing(meeting -> meeting.getName().toString(),
                    String.CASE_INSENSITIVE_ORDER);
            break;
        case START:
            meetingComparator = Comparator.comparing(meeting -> meeting.getStart());
            break;
        case END:
            meetingComparator = Comparator.comparing(meeting -> meeting.getTerminate());
            break;
        case PRIORITY:
            meetingComparator = Comparator.comparing(meeting -> meeting.getPriority().toString(),
                    String.CASE_INSENSITIVE_ORDER);
            break;
        case DESCRIPTION:
            meetingComparator = Comparator.comparing(meeting -> meeting.getDescription().toString(),
                    String.CASE_INSENSITIVE_ORDER);
            break;
        default:
            break;
        }
        if (sortDirection == MeetingSortDirection.DESC) {
            meetingComparator = meetingComparator.reversed();
        }

    }


    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortFilteredMeetingList(this.meetingComparator);
        return new CommandResult("Sorted meetings");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SortMeetingCommand that = (SortMeetingCommand) o;
        return meetingSortOption == that.meetingSortOption && meetingSortDirection == that.meetingSortDirection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingSortOption, meetingSortDirection);
    }
}
