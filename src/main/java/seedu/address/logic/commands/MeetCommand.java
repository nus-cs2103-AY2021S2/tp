package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    public static final String ADD_MEETING = "-add";
    public static final String CLEAR_MEETING = "-clear";
    public static final String DELETE_MEETING = "-delete";
    public static final String MEETING_EMPTY = "";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Schedule a meeting with a client.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "ACTION (-add, -delete) "
            + "DATE (DD.MM.YYYY) START (HH:MM) END (HH:MM) PLACE\n"
            + "or INDEX ACTION (-clear) or INDEX DATE START END PLACE\n"
            + "END time must be after START time on the same DATE.\n"
            + "Example: " + COMMAND_WORD + " 3 -add 18.05.2021 16:30 17:30 MRT";

    public static final String MESSAGE_CLASHING_MEETING = "The meeting clashes with \n%1$s";
    public static final String MESSAGE_ADD_MEETING = "The meeting is added to the client %1$s";
    public static final String MESSAGE_DELETE_MEETING = "The meeting is deleted from the client %1$s";
    public static final String MESSAGE_CLEAR_MEETING = "All meetings are cleared from the client.";

    private final Index index;
    private final String action;
    private final String date;
    private final String start;
    private final String end;
    private final String place;

    /**
     * Create a MeetCommand to change the meeting to the specified {@code Place} {@code Date} {@code Time}
     *
     * @param index of the client in the list
     * @param action of the command
     * @param date of the meeting
     * @param start time of the meeting
     * @param end time of the meeting
     * @param place of the meeting
     */
    public MeetCommand(Index index, String action, String date, String start, String end, String place) {
        this.index = index;
        this.action = action;
        this.date = date;
        this.start = start;
        this.end = end;
        this.place = place;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToMeet = lastShownList.get(index.getZeroBased());
        if (action.equals(CLEAR_MEETING)) {
            Person meetPerson = clearMeeting(personToMeet);
            model.setPerson(personToMeet, meetPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_CLEAR_MEETING);
        }

        Meeting meeting = new Meeting(date, start, end, place);
        if (action.equals(DELETE_MEETING)) {
            Person meetPerson = deleteMeeting(personToMeet, meeting);
            model.setPerson(personToMeet, meetPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_DELETE_MEETING, meeting));
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> personList = model.getFilteredPersonList();
        List<Meeting> clashes = checkMeeting(personList, meeting);
        if (!clashes.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (Meeting clash : clashes) {
                builder.append(clash.toString()).append("\n");
            }
            builder.deleteCharAt(builder.length() - 1);
            return new CommandResult(String.format(MESSAGE_CLASHING_MEETING, builder.toString()));
        }

        Person meetPerson = addMeeting(personToMeet, meeting);
        model.setPerson(personToMeet, meetPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_MEETING, meeting));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetCommand // instanceof handles nulls
                && index.equals(((MeetCommand) other).index) // state check
                && action.equals(((MeetCommand) other).action) // state check
                && date.equals(((MeetCommand) other).date) // state check
                && start.equals(((MeetCommand) other).start) // state check
                && end.equals(((MeetCommand) other).end) // state check
                && place.equals(((MeetCommand) other).place)); // state check
    }

    /**
     * Add a meeting of the client.
     *
     * @param person of the meeting
     * @param meeting details of the meeting
     * @return Person with the new meeting
     */
    public static Person addMeeting(Person person, Meeting meeting) {
        Name updatedName = person.getName();
        Phone updatedPhone = person.getPhone().get();
        Email updatedEmail = person.getEmail().get();
        Address updatedAddress = person.getAddress().get();
        Set<Tag> updatedTags = person.getTags();
        List<InsurancePolicy> updatedPolicies = person.getPolicies();
        List<Meeting> updatedMeeting = new ArrayList<>(person.getMeetings());
        updatedMeeting.add(meeting);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedTags, updatedPolicies, updatedMeeting);
    }

    /**
     * Delete a meeting of the client.
     *
     * @param person of the meeting
     * @param meeting details of the meeting
     * @return Person without the meeting
     */
    public static Person deleteMeeting(Person person, Meeting meeting) {
        Name updatedName = person.getName();
        Phone updatedPhone = person.getPhone().get();
        Email updatedEmail = person.getEmail().get();
        Address updatedAddress = person.getAddress().get();
        Set<Tag> updatedTags = person.getTags();
        List<InsurancePolicy> updatedPolicies = person.getPolicies();
        List<Meeting> updatedMeeting = new ArrayList<>(person.getMeetings());
        updatedMeeting.remove(meeting);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedTags, updatedPolicies, updatedMeeting);
    }

    /**
     * Clear all the meetings of the client.
     *
     * @param person of the meeting
     * @return Person without any meeting
     */
    public static Person clearMeeting(Person person) {
        Name updatedName = person.getName();
        Phone updatedPhone = person.getPhone().get();
        Email updatedEmail = person.getEmail().get();
        Address updatedAddress = person.getAddress().get();
        Set<Tag> updatedTags = person.getTags();
        List<InsurancePolicy> updatedPolicies = person.getPolicies();
        List<Meeting> updatedMeeting = new ArrayList<>();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedTags, updatedPolicies, updatedMeeting);
    }

    /**
     * Check for clashes for the meeting.
     *
     * @param personList meetings of all clients
     * @param meeting details of the meeting
     * @return List of clashed meetings
     */
    public static List<Meeting> checkMeeting(List<Person> personList, Meeting meeting) {
        List<Meeting> clashes = new ArrayList<>();

        for (Person person : personList) {
            for (Meeting meet : person.getMeetings()) {
                LocalTime meetingStart = LocalTime.parse(meeting.start, DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime meetingEnd = LocalTime.parse(meeting.end, DateTimeFormatter.ofPattern("HH:mm"));

                LocalTime meetStart = LocalTime.parse(meet.start, DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime meetEnd = LocalTime.parse(meet.end, DateTimeFormatter.ofPattern("HH:mm"));

                boolean startClashed = (meetingStart.isAfter(meetStart) && meetingStart.isBefore(meetEnd))
                        || meetingStart.equals(meetStart) || meetingStart.equals(meetEnd);
                boolean endClashed = (meetingEnd.isAfter(meetStart) && meetingEnd.isBefore(meetEnd))
                        || meetingEnd.equals(meetStart) || meetingEnd.equals(meetEnd);

                if (meet.date.equals(meeting.date) && (startClashed || endClashed)) {
                    clashes.add(meet);
                }
            }
        }
        return clashes;
    }

}
