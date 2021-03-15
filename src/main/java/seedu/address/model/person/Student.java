package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

public class Student extends Person {

    private static int studentCount = 0;

    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Student(PersonId personId, Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.setPersonType(new PersonType("student"));
        if (personId == null) {
            studentCount++;
            this.setPersonId(new PersonId("s/" + studentCount));
        } else {
            this.setPersonId(personId);
        }

    }

    public static void setStudentCount(String storedStudentCount) {
        studentCount = Integer.valueOf(storedStudentCount);
    }

    public static String getStudentCount() {
        return String.valueOf(studentCount);
    }

}
