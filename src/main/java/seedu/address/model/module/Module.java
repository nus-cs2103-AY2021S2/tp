package seedu.address.model.module;

import java.time.LocalDateTime;
import java.util.Objects;

public class Module implements Comparable<Module> {
    private Title title;
    private AssignmentList assignments;
    private ExamList exams;

    /**
     * Constructs a {@code Module} with {@code Title} as the input representing the module title.
     */
    public Module(Title title) {
        this.title = title;
        this.assignments = new AssignmentList();
        this.exams = new ExamList();
    }

    /**
     * Constructs a {@code Module} with {@code Title}, {@code AssignmentList} and {@code ExamList} as the inputs.
     */
    public Module(Title title, AssignmentList assignments, ExamList exams) {
        this.title = title;
        this.assignments = assignments;
        this.exams = exams;
    }

    /**
     * Gets the title of the module.
     *
     * @return Module title.
     */
    public Title getTitle() {
        return title;
    }

    /**
     * Changes the title of the module with the given {@code title}
     */
    public void editTitle(Title title) {
        this.title = title;
    }

    /**
     * Gets the assignment list in the module.
     *
     * @return List of module assignments.
     */
    public AssignmentList getAssignments() {
        return assignments;
    }

    /**
     * Gets the assignment at the specified index.
     *
     * @param index Index of assignment.
     * @return Assignment at index.
     */
    public Assignment getAssignment(int index) {
        return assignments.get(index);
    }

    /**
     * Gets the exams of the module.
     *
     * @return List of module exams.
     */
    public ExamList getExams() {
        return exams;
    }

    /**
     * Gets {@code exam} at specific index.
     *
     * @param index index of the examList.
     * @return {@code exam} at index.
     */
    public Exam getExam(int index) {
        return exams.getExamAt(index);
    }

    /**
     * Checks if the module has the given assignment in its assignment list.
     *
     * @param assignment Assignment to be checked.
     * @return Boolean
     */
    public boolean hasAssignment(Assignment assignment) {
        return assignments.contains(assignment);
    }

    /**
     * Returns true if the assignment list in {@code Module} contains the {@code index}.
     */
    public boolean hasAssignment(int index) {
        return index <= assignments.size() && index > 0;
    }

    /**
     * Checks if the module has the given exam in its exam list.
     *
     * @param exam Exam to be checked.
     * @return Boolean
     */
    public boolean hasExam(Exam exam) {
        return exams.contains(exam);
    }

    /**
     * Returns true if {@code index} is within the exam list of the module.
     */
    public boolean hasExam(int index) {
        return index > 0 && index <= exams.size();
    }

    /**
     * Adds an assignment to the assignment list of the module.
     *
     * @param assignment Assignment to be added.
     */
    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    /**
     * Adds an exam to the exam list of the module.
     *
     * @param exam Exam to be added.
     */
    public void addExam(Exam exam) {
        this.exams.add(exam);
    }

    /**
     * Delete {@code assignment} from {@code assignments}
     * {@code assignment} must exist in {@code assignments}
     */
    public void deleteAssignment(Assignment assignment) {
        this.assignments.delete(assignment);
    }

    /**
     * Delete {@code index} from {@code assignments}
     * {@code assignment} must exist in {@code assignments}
     */
    public void deleteAssignment(int index) {
        this.assignments.delete(index);
    }

    /**
     * Delete {@code exam} from {@code exams}
     * {@code assignment} must exist in {@code exams}
     */
    public void deleteExam(Exam exam) {
        this.exams.delete(exam);
    }

    /**
     * Delete {@code index} from {@code exams}
     * {@code index} must exist in {@code exams}
     */
    public void deleteExam(int index) {
        this.exams.deleteAt(index);
    }

    /**
     * Edits the description of the assignment at {@code index} with the given {@code edit}.
     */
    public void editAssignment(int index, Description edit) {
        Assignment target = getAssignment(index);
        target = target.setDescription(edit);
        assignments.set(index, target);
    }

    /**
     * Edits the deadline of the assignment at {@code index} with the given {@code edit}.
     */
    public void editAssignment(int index, LocalDateTime edit) {
        Assignment target = getAssignment(index);
        target = target.setDeadline(edit);
        assignments.set(index, target);
    }

    /**
     * Edits the date of the exam at {@code index} with the given {@code edit}.
     */
    public void editExam(int index, LocalDateTime edit) {
        Exam target = getExam(index);
        target = target.setDate(edit);
        exams.set(index, target);
    }

    /**
     * Returns true if both modules have the same title.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getTitle().equals(getTitle());
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

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getTitle().equals(getTitle())
                && otherModule.getAssignments().equals(getAssignments())
                && otherModule.getExams().equals(getExams());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, assignments, exams);
    }

    @Override
    public String toString() {
        return getTitle().modTitle;
    }

    @Override
    public int compareTo(Module o) {
        return title.compareTo(o.title);
    }
}
