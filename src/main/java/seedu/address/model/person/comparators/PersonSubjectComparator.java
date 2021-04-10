package seedu.address.model.person.comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import seedu.address.model.person.Person;
import seedu.address.model.subject.Subject;

/**
 * Compares two {@code Person}s according to the order of their {@code Tag}.
 */
public class PersonSubjectComparator implements Comparator<Person> {
    public PersonSubjectComparator(){}
    @Override
    public int compare(Person p1, Person p2) {

        ArrayList<Subject> thisSubjectList = new ArrayList<>(p1.getSubjects());
        ArrayList<Subject> otherSubjectList = new ArrayList<>(p2.getSubjects());

        if (thisSubjectList.isEmpty() && !otherSubjectList.isEmpty()) {
            return 1;
        } else if (!thisSubjectList.isEmpty() && otherSubjectList.isEmpty()) {
            return -1;
        } else if (thisSubjectList.isEmpty() && otherSubjectList.isEmpty()) {
            return 0;
        }

        Collections.sort(thisSubjectList);
        Collections.sort(otherSubjectList);

        Subject thisSubject = thisSubjectList.get(0);
        Subject otherSubject = otherSubjectList.get(0);

        return thisSubject.compareTo(otherSubject);
    }
}
