package seedu.address.model.budget;

import java.util.HashMap;
import java.util.Map;

import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.subject.SubjectRate;
import seedu.address.model.subject.TutorSubject;



public class NameSubjectRate {

    private Map<Name, HashMap<SubjectName, SubjectRate>> nameRateHash;

    /**
     * Primary Constructor.
     */
    public NameSubjectRate(String filePath) {
        GetAddressBook getAddressBook = new GetAddressBook(filePath);
        this.nameRateHash = createNameSubjectMap(getAddressBook.getAddressBook());
    }

    /**
     * Getter method that retrieves the name rate hash map.
     * @return Map representing the tutor subjects of each Tutor with name as key
     */
    public Map<Name, HashMap<SubjectName, SubjectRate>> getNameRateHash() {
        return nameRateHash;
    }

    /**
     * Accessory method to create map from {@code ReadOnlyAddressBook}
     */
    private static Map<Name, HashMap<SubjectName, SubjectRate>> createNameSubjectMap(
            ReadOnlyAddressBook readOnlyAddressBook) {

        Map<Name, HashMap<SubjectName, SubjectRate>> nameSubjectMap = new HashMap<>();
        for (Person person : readOnlyAddressBook.getPersonList()) {
            nameSubjectMap.put(person.getName(), createNameRateMap(person));
        }
        return nameSubjectMap;
    }

    /**
     * Acessory method to create HashMap for each {@code Person}
     * @param person Each person in address book
     */
    private static HashMap<SubjectName, SubjectRate> createNameRateMap(Person person) {
        HashMap<SubjectName, SubjectRate> subjectRateHashMap = new HashMap<>();
        for (TutorSubject tutorSubject : person.getSubjectList()) {
            subjectRateHashMap.put(tutorSubject.getName(), tutorSubject.getRate());
        }
        return subjectRateHashMap;
    }


}
