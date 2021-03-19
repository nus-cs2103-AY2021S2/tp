package seedu.address.model.plan;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Plan in the description book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Plan {
    // Data fields
    private List<Semester> semesters;
    private final Description description;
    private final Set<Tag> tags = new HashSet<>();
    private boolean isMasterPlan;
    private boolean isValid;

    /**
     * Every field must be present and not null.
     * Semesters MUST have at least 1 semester within it
     */
    public Plan(Description description, Set<Tag> tags) {
        requireAllNonNull(description, tags);
        this.description = description;
        this.tags.addAll(tags);
        this.semesters = new ArrayList<>();
        isMasterPlan = false;
    }

    /**
     * Every field must be present and not null.
     * Semesters MUST have at least 1 semester within it
     */
    public Plan(Description description, Set<Tag> tags, List<Semester> semesters) {
        requireAllNonNull(description, tags, semesters);
        this.semesters = semesters;
        this.description = description;
        this.tags.addAll(tags);
    }

    /**
     * Adds a semester to the Plan.
     */
    public Plan addSemester(Semester semester) {
        semesters.add(semester);
        return this;
    }

    /**
     * Deletes a semester from the Plan.
     */
    public Plan removeSemester(Semester semester) {
        for (int i = 0; i < semesters.size(); i++) {
            if (semesters.get(i).getSemNumber() == semester.getSemNumber()) {
                semesters.remove(i);
                break;
            }
        }
        return this;
    }

    /**
     * Change a semester in a plan
     */
    public Plan changePlan(Semester oldSemester, Semester newSemester) {
        for (int i = 0; i < semesters.size(); i++) {
            if (semesters.get(i).getSemNumber() == newSemester.getSemNumber()) {
                semesters.set(i, newSemester);
            }
        }
        return new Plan(this.description, this.tags, semesters);
    }

    /**
     * Command to get number of modules in entire plan.
     * @return number of modules in entire plan.
     */
    public int getNumModules() {
        int numModules = 0;

        for (Semester s : semesters) {
            numModules += s.getNumModules();
        }

        return numModules;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * Returns a boolean indicating whether this Plan object is a master plan.
     *
     * @return A boolean indicating whether this Plan object is a master plan.
     */
    public boolean isMasterPlan() {
        return isMasterPlan;
    }

    /**
     * Sets the isMasterPlan boolean flag for this Plan object.
     *
     * @param b The boolean value to be set for the isMasterPlan flag.
     */
    public void setMasterPlan(boolean b) {
        isMasterPlan = b;
    }

    /**
     * Returns List of Semesters.
     * @return List of Semesters
     */
    public List<Semester> getSemesters() {
        if (semesters == null) {
            return Collections.emptyList();
        }
        return List.copyOf(semesters);
    }


    /**
     * Returns Semester matching semester number provided.
     * @return Semester
     */
    public Semester getSemester(int semNumber) {
        for (Semester semester : semesters) {
            if (semester.getSemNumber() == semNumber) {
                return semester;
            }
        }
        return null;
    }

    /**
     * Check whether Semester exists.
     * @return if Semester exists, true, else false
     */
    public boolean hasSemester(int semNumber) {
        return semesters.stream().anyMatch(semester ->
            semester.getSemNumber() == semNumber
        );
    }

    /**
     * Returns Description of Plan.
     */
    public Description getDescription() {
        return description;
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Plan)) {
            return false;
        }

        Plan otherPlan = (Plan) other;
        return otherPlan.getDescription().equals(getDescription())
                && otherPlan.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("; Description: ")
                .append(getDescription());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    public double getCurrentCap() {
        int totalMcs = 0;
        double undividedCap = 0;
        for (Semester semester : getSemesters()) {
            for (Module module : semester.getModules()) {
                if (!module.isDone()) {
                    continue;
                }
                int moduleMC = module.getMCs();
                double multipliedCap = moduleMC * module.convertGradeToCap();
                undividedCap += multipliedCap;
                totalMcs += moduleMC;
            }
        }

        // Guard clause for if the user has not done any modules
        if (totalMcs == 0) {
            return 0;
        }

        double cap = undividedCap / totalMcs;
        return cap;
    }
}
