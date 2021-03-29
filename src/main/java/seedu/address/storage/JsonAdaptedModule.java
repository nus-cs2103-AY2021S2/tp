package seedu.address.storage;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.AssignmentList;
import seedu.address.model.module.Exam;
import seedu.address.model.module.ExamList;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.person.Name;


/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";
    private String title;
    private ArrayList<JsonAdaptedAssignment> assignmentList = new ArrayList<>();
    private ArrayList<JsonAdaptedExam> examList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModule} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("title") String title,
                             @JsonProperty("assignmentList") ArrayList<JsonAdaptedAssignment> assignmentList,
                             @JsonProperty("examList") ArrayList<JsonAdaptedExam> examList) {
        this.title = title;
        if (assignmentList != null) {
            this.assignmentList.addAll(assignmentList);
        }
        if (examList != null) {
            this.examList = examList;
        }
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        title = source.getTitle().modTitle;
        assignmentList.addAll(source.getAssignments().getAssignments().stream()
                .map(JsonAdaptedAssignment::new)
                .collect(Collectors.toList()));
        examList.addAll(source.getExams().getExams().stream()
                .map(JsonAdaptedExam::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted Module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Module.
     */
    public Module toModelType() throws IllegalValueException {
        final ArrayList<Assignment> modelAssignments = new ArrayList<>();
        final ArrayList<Exam> modelExams = new ArrayList<>();
        if (assignmentList != null && !assignmentList.isEmpty()) {
            for (JsonAdaptedAssignment assignment : assignmentList) {
                modelAssignments.add(assignment.toModelType());
            }
        }
        if (examList != null && !examList.isEmpty()) {
            for (JsonAdaptedExam exam : examList) {
                modelExams.add(exam.toModelType());
            }
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        assert title != null;

        final Title modelTitle = new Title(title);
        final AssignmentList modelAssignmentLists = new AssignmentList(modelAssignments);
        final ExamList modelExamList = new ExamList(modelExams);
        return new Module(modelTitle, modelAssignmentLists, modelExamList);
    }

}
