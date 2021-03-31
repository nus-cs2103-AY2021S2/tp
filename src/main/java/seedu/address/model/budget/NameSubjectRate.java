package seedu.address.model.budget;

import java.util.HashMap;
import java.util.Map;

import seedu.address.model.ReadOnlyTutorBook;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.subject.SubjectRate;
import seedu.address.model.subject.TutorSubject;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Tutor;

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
            ReadOnlyTutorBook readOnlyTutorBook) {

        Map<Name, HashMap<SubjectName, SubjectRate>> nameSubjectMap = new HashMap<>();
        for (Tutor tutor : readOnlyTutorBook.getTutorList()) {
            nameSubjectMap.put(tutor.getName(), createNameRateMap(tutor));
        }
        return nameSubjectMap;
    }

    /**
     * Acessory method to create HashMap for each {@code Person}
     * @param tutor Each person in address book
     */
    private static HashMap<SubjectName, SubjectRate> createNameRateMap(Tutor tutor) {
        HashMap<SubjectName, SubjectRate> subjectRateHashMap = new HashMap<>();
        for (TutorSubject tutorSubject : tutor.getSubjectList()) {
            subjectRateHashMap.put(tutorSubject.getName(), tutorSubject.getRate());
        }
        return subjectRateHashMap;
    }


}
