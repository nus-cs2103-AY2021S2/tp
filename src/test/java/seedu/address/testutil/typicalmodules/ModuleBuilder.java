package seedu.address.testutil.typicalmodules;

import seedu.address.model.module.AssignmentList;
import seedu.address.model.module.ExamList;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.util.SampleDataUtil;

/**
 * Helps with building Module Objects.
 */
public class ModuleBuilder {
    public static final String DEFAULT_TITLE = "CS2103";

    private Title title;
    private AssignmentList assignments;
    private ExamList exams;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        title = new Title(DEFAULT_TITLE);
        assignments = new AssignmentList();
        exams = new ExamList();
    }

    /**
     * Creates a ModuleBuilder wit the data of {@code modToCopy}.
     */
    public ModuleBuilder(Module modToCopy) {
        title = modToCopy.getTitle();
        assignments = new AssignmentList(modToCopy.getAssignments().getAssignments());
        exams = new ExamList(modToCopy.getExams().getExams());
    }

    /**
     * Sets the {@code Title} of the {@code Module} that is being built.
     */
    public ModuleBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Creates an {@code AssignmentList} and sets it to the {@code Module} that is being built.
     */
    public ModuleBuilder withAssignments(String ... assignments) {
        this.assignments = new AssignmentList(SampleDataUtil.getAssignments(assignments));
        return this;
    }

    /**
     * Creates an {@code ExamList} and sets ot tp the {@code Module} that is being built.
     */
    public ModuleBuilder withExams(String... exams) {
        this.exams = new ExamList(SampleDataUtil.getExams(exams));
        return this;
    }

    /**
     * Builds the {@code Module} with the {@code Title}, {@code AssignmentList}, and {@code
     * ExamList}.
     */
    public Module build() {
        return new Module(title, assignments, exams);
    }

    public Module emptyBuild() {
        return new Module(title);
    }
}
