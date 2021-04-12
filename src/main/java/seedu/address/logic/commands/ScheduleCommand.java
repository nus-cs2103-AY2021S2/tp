package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Schedules a meeting with a client identified using its displayed index from Link.me.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Schedules a meeting with the client identified by the "
            + "index number used in the displayed client list.\n"
            + "Scheduled meeting must not be in the past."
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_MEETING + "MEETING_DESCRIPTION @ yyyy-mm-dd HH:MM\n"
            + "Example: " + COMMAND_WORD + " 1 m/ Insurance Plan @ 2021-03-05 14:50";

    public static final String MESSAGE_SCHEDULE_PERSON_SUCCESS = "Scheduled meeting with client %1$s at %2$s";
    public static final String MESSAGE_SCHEDULE_CONFLICT_FAILURE =
            "Scheduling conflict found at this meeting %1$s with %2$s";
    public static final String MESSAGE_SCHEDULE_PAST_FAILURE =
            "Scheduled meeting cannot be in the past. The current time is %1$s.";

    private final Index targetIndex;

    private final Meeting meeting;

    /**
     * Constructor for Schedule Command
     */
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

        if (meeting.dateTime.isBefore(LocalDateTime.now())) {
            throw new CommandException(String.format(MESSAGE_SCHEDULE_PAST_FAILURE,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a"))));
        }

        Person personToSchedule = lastShownList.get(targetIndex.getZeroBased());
        Person updatedPerson = personToSchedule.setMeeting(Optional.of(meeting));
        if (!personToSchedule.getMeeting().equals(meeting)) {
            Optional<String> errorMsg = model
                    .clash(updatedPerson)
                    .map(person ->
                            String.format(MESSAGE_SCHEDULE_CONFLICT_FAILURE,
                                    person.getMeeting().get().original, person.getName().fullName));
            if (errorMsg.isPresent()) {
                throw new CommandException(errorMsg.get());
            }
        }
        model.setPerson(personToSchedule, updatedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SCHEDULE_PERSON_SUCCESS, updatedPerson.getName(), meeting));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleCommand // instanceof handles nulls
                && targetIndex.equals(((ScheduleCommand) other).targetIndex) // state check
                && meeting.equals(((ScheduleCommand) other).meeting));
    }
}
