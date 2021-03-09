package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Schedules a meeting with a person identified using it's displayed index from the address book.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Schedules a meeting with the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "[" + PREFIX_MEETING + "MEETING_DESCRIPTION @ MEETING_DATE_TIME(yyyy-mm-dd HH:MM)] "
            + "Example: " + COMMAND_WORD + " 1 /m Insurance Plan @ 2021-03-05 14:50";

    public static final String MESSAGE_MEETING_PERSON_SUCCESS = "Scheduled Meeting with Person: %1$s %2$s";

    private final Index targetIndex;

    private final Meeting meeting;

    public ScheduleCommand(Index targetIndex, Meeting meeting) {
        this.targetIndex = targetIndex;
        this.meeting = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToSchedule = lastShownList.get(targetIndex.getZeroBased());
        Person updatedPerson = personToSchedule.addMeeting(meeting);
        model.setPerson(personToSchedule, updatedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_MEETING_PERSON_SUCCESS, updatedPerson, meeting));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleCommand // instanceof handles nulls
                && targetIndex.equals(((ScheduleCommand) other).targetIndex) // state check
                && meeting.equals(((ScheduleCommand) other).meeting));
    }
}
