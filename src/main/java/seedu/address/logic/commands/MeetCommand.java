package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.insurancepolicy.InsurancePolicy;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class MeetCommand extends Command {

    public static final String COMMAND_WORD = "meet";

    public static final String CHECK_CLASHES = "-check";
    public static final String IGNORE_CLASHES = "-ignore";
    public static final String DELETE_MEETING = "-delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Schedule a meeting with a client.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "ACTION (-check, -ignore, -delete) "
            + "PLACE;DATE(DD:MM:YYYY);TIME(HH:MM)\n"
            + "Example: " + COMMAND_WORD + " 3 -check MRT;18:05:2021;16:30";

    public static final String MESSAGE_MEET_PERSON_SUCCESS = "Meet client at %1$s";
    public static final String MESSAGE_CLASHING_MEETING = "The meeting clashes with another meeting in the ClientBook.";
    public static final String MESSAGE_DELETE_MEETING = "The meeting is deleted from the client in the ClientBook.";

    private final Index index;
    private final String action;
    private final String place;
    private final String date;
    private final String time;

    /**
     * Create a MeetCommand to change the meeting to the specified {@code Place} {@code Date} {@code Time}
     *
     * @param index of the client in the list
     * @param action of the command
     * @param place of the meeting
     * @param date of the meeting
     * @param time of the meeting
     */
    public MeetCommand(Index index, String action, String place, String date, String time) {
        this.index = index;
        this.action = action;
        this.place = place;
        this.date = date;
        this.time = time;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToMeet = lastShownList.get(index.getZeroBased());

        if (action.equals(DELETE_MEETING)) {
            Person meetPerson = deleteMeeting(personToMeet);
            model.setPerson(personToMeet, meetPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_DELETE_MEETING);
        }

        Person meetPerson = createMeeting(personToMeet, place, date, time);

        if (action.equals(IGNORE_CLASHES)) {
            model.setPerson(personToMeet, meetPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_MEET_PERSON_SUCCESS, meetPerson.getMeeting().get(0)));
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> wholeList = model.getFilteredPersonList();
        String checkDate;
        String checkTime;

        for (Person person : wholeList) {
            if (!person.getMeeting().isEmpty()) {
                checkDate = person.getMeeting().get(0).getDate();
                checkTime = person.getMeeting().get(0).getTime();
            } else {
                checkDate = null;
                checkTime = null;
            }

            if (date.equals(checkDate) && time.equals(checkTime)) {
                throw new CommandException(MESSAGE_CLASHING_MEETING);
            }
        }

        model.setPerson(personToMeet, meetPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_MEET_PERSON_SUCCESS, meetPerson.getMeeting().get(0)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetCommand // instanceof handles nulls
                && index.equals(((MeetCommand) other).index) // state check
                && action.equals(((MeetCommand) other).action) // state check
                && place.equals(((MeetCommand) other).place) // state check
                && date.equals(((MeetCommand) other).date) // state check
                && time.equals(((MeetCommand) other).time)); // state check
    }

    /**
     * Change the meeting of the client.
     *
     * @param person of the meeting
     * @param place of the meeting
     * @param date of the meeting
     * @param time of the meeting
     * @return Person with new meeting
     */
    public static Person createMeeting(Person person, String place, String date, String time) {
        Name updatedName = person.getName();
        Phone updatedPhone = person.getPhone().get();
        Email updatedEmail = person.getEmail().get();
        Address updatedAddress = person.getAddress().get();
        Set<Tag> updatedTags = person.getTags();
        List<InsurancePolicy> updatedPolicies = person.getPolicies();
        List<Meeting> updatedMeeting = new ArrayList<>();
        updatedMeeting.add(new Meeting(place, date, time));

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedTags, updatedPolicies, updatedMeeting);
    }

    /**
     * Delete the meeting of the client.
     *
     * @param person of the meeting
     * @return Person without any meeting
     */
    public static Person deleteMeeting(Person person) {
        Name updatedName = person.getName();
        Phone updatedPhone = person.getPhone().get();
        Email updatedEmail = person.getEmail().get();
        Address updatedAddress = person.getAddress().get();
        Set<Tag> updatedTags = person.getTags();
        List<InsurancePolicy> updatedPolicies = person.getPolicies();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedTags, updatedPolicies);
    }
}
