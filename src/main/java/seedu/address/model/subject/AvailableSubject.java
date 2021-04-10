package seedu.address.model.subject;

/**
 * Represents a list of subjects available in TutorsPet.
 * Both abbreviated and full name are included for a subject.
 */
public enum AvailableSubject {
    bio("biology"),
    chem("chemistry"),
    cn("chinese"),
    econ("economics"),
    eng("english"),
    geo("geography"),
    hist("history"),
    math("mathematics"),
    phys("physics"),
    sci("science");


    private final String full;
    private AvailableSubject(String full) {
        this.full = full;
    }
    @Override
    public String toString() {
        return full;
    }
}



