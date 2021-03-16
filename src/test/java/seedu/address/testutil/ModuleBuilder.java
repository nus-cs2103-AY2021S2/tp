package seedu.address.testutil;

import seedu.address.model.module.Assignment;
import seedu.address.model.module.AssignmentList;
import seedu.address.model.module.Exam;
import seedu.address.model.module.ExamList;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

import java.util.ArrayList;

public class ModuleBuilder {
    public static final String DEFAULT_TITLE = "CS2103";

    private Title title;
    private AssignmentList assignments;
    private ExamList exams;

    public ModuleBuilder() {
        title = new Title(DEFAULT_TITLE);
        assignments = new AssignmentList();
        exams = new ExamList();
    }

    public ModuleBuilder(Module modToCopy) {
        title = modToCopy.getTitle();
        assignments = new AssignmentList(modToCopy.getAssignments().getAssignments());
        exams = new ExamList(modToCopy.getExams().getExams());
    }

    public ModuleBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    public ModuleBuilder withAssignments(ArrayList<Assignment> assignments) {
        this.assignments = new AssignmentList(assignments);
        return this;
    }

    public ModuleBuilder withExams(ArrayList<Exam> exams) {
        this.exams = new ExamList(exams);
        return this;
    }

    public Module build() {
        return new Module(title, assignments, exams);
    }
}
