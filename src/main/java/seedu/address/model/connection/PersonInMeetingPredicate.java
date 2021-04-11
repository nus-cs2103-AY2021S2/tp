package seedu.address.model.connection;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.group.Group;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

public class PersonInMeetingPredicate implements Predicate<Person> {
    private final Meeting meeting;

    public PersonInMeetingPredicate(Meeting meeting) {
        this.meeting = meeting;
    }

    @Override
    public boolean test(Person person) {
        Set<Person> personsInMeeting = this.meeting.getConnectionToPerson();
        return personsInMeeting.stream()
                .anyMatch(p -> p.equals(person));

        /* The code below is to be used only if the people in the meeting groups are to be displayed too (i.e. in v1.5) */
        /*Set<Group> groupsInMeeting = this.meeting.getGroups();
        Set<Group> groupsForPerson = person.getGroups();
        return groupsInMeeting.stream()
                .anyMatch(mGroup ->
                        groupsForPerson.stream().anyMatch(pGroup ->
                                pGroup.equals(mGroup))) ||
                personsInMeeting.stream().anyMatch(p -> p.equals(person));*/
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.connection.PersonInMeetingPredicate // instanceof handles null
                && meeting.equals(((seedu.address.model.connection.PersonInMeetingPredicate) other).meeting));
        // state check
    }
}
