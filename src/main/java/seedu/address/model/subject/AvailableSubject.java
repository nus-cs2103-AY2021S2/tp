package seedu.address.model.subject;

/**
 * Represents a list of subjects available in TutorsPet.
 * Both abbreviated and full name are included for a subject.
 */
public enum AvailableSubject {
    bio("bio", "biology"),
    biology("bio", "biology"),

    chem("chem", "chemistry"),
    chemistry("chem", "chemistry"),

    cn("cn", "chinese"),
    chinese("cn", "chinese"),

    econ("econ", "economics"),
    economics("econ", "economics"),

    eng("eng", "english"),
    english("eng", "english"),

    geo("geo", "geography"),
    geography("geo", "geography"),

    hist("hist", "history"),
    history("hist","history"),

    math("math","mathematics"),
    mathematics("math","mathematics"),

    phys("phys","physics"),
    physics("phys", "physics");

    private final String abbr;
    private final String full;
    private AvailableSubject(String abbr, String full) {
        this.abbr = abbr;
        this.full = full;
    }
    @Override
    public String toString() {
        return abbr;
    }
}



