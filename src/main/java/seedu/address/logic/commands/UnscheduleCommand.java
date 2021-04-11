package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class UnscheduleCommand extends Command {
    public static final String COMMAND_WORD = "unschedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unschedules a meeting with the client identified by the "
            + "index number used in the displayed meeting list.\n"
            + "Parameter to removes all meetings: " + COMMAND_WORD + " all\n"
            + "Parameter to removes all expired meetings: " + COMMAND_WORD + " expired\n"
            + "Parameter to removes a specific meetings: " + COMMAND_WORD + " INDEX -- removes the specified meeting\n";

    public static final String MESSAGE_UNSCHEDULE_ALL_SUCCESS = "All meetings unscheduled.";
    public static final String MESSAGE_UNSCHEDULE_EXPIRED_SUCCESS = "All expired meetings removed";
    public static final String MESSAGE_UNSCHEDULE_ONE_SUCCESS = "Meeting with %1$s removed";

    private final Index targetIndex;
    private final boolean all;
    private final boolean expired;

    /**
     * Constructor for Unschedule Command
     */
    public UnscheduleCommand(Index targetIndex, boolean all, boolean expired) {
        this.targetIndex = targetIndex;
        this.all = all;
        this.expired = expired;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> meetingList = new ArrayList<>(model.getAddressBook().getMeetingList());
        if (all) {
            meetingList.forEach(person -> model.setPerson(person, person.setMeeting(Optional.empty())));
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_UNSCHEDULE_ALL_SUCCESS);
        }
        if (expired) {
            LocalDateTime now = LocalDateTime.now();
            meetingList.stream().filter(person -> person.getMeeting().orElseThrow().dateTime.isBefore(now))
                    .forEach(person -> model.setPerson(person, person.setMeeting(Optional.empty())));
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_UNSCHEDULE_EXPIRED_SUCCESS);
        }
        if (targetIndex.getZeroBased() >= meetingList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }
        Person personToSchedule = meetingList.get(targetIndex.getZeroBased());
        Person updatedPerson = personToSchedule.setMeeting(Optional.empty());
        model.setPerson(personToSchedule, updatedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_UNSCHEDULE_ONE_SUCCESS, personToSchedule.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnscheduleCommand // instanceof handles nulls
                && Optional.ofNullable(targetIndex).equals(Optional.ofNullable(((UnscheduleCommand) other).targetIndex))
                && all == ((UnscheduleCommand) other).all
                && expired == ((UnscheduleCommand) other).expired); // state check
    }

}
