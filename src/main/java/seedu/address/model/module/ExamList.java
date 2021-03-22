package seedu.address.model.module;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a ExamList in Module.
 */
public class ExamList {
    public static final String NO_EXAMS_OUTPUT = "You have no exams~";

    // Identity fields.
    private List<Exam> exams;

    /**
     * Constructs an {@code ExamList} to store {@code Exam}.
     */
    public ExamList() {
        exams = new ArrayList<>();
    }


    /**
     * Constucts an {@code ExamList} that stores existing {@code Exams} from input.
     *
     * @param exams exams to be contained in {@code ExamList}.
     */
    public ExamList(List<Exam> exams) {
        if (exams == null) {
            this.exams = new ArrayList<>();
        }
        this.exams = exams;
    }

    /**
     * Returns if {@code exam} exists inside the exam list.
     */
    public boolean contains(Exam exam) {
        boolean hasExam = false;
        assert exams != null;
        for (int i = 0; i < exams.size() && !hasExam; i++) {
            if (exams.contains(exam)) {
                hasExam = true;
            }
        }
        return hasExam;
    }

    /**
     * Gets the index of the {@code exam} in the exam list.
     * {@code exam} must exist in the exam list.
     */
    public int getIndex(Exam exam) {
        assert exams != null;
        int index = -1;
        for (int i = 0; i < exams.size(); i++) {
            if (exams.get(i).equals(exam)) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Adds an {@code Exam} into the ExamList.
     *
     * @param exam Exam to be added.
     */
    public void add(Exam exam) {
        assert exam != null;
        assert exams != null;
        exams.add(exam);
    }

    /**
     * Removes {@code Exam} at the index from the ExamList.
     *
     * @param index Index of the {@code Exam} to be removed.
     * @return Removed Exam.
     */
    public Exam deleteAt(int index) {
        assert index >= 0 && index < exams.size();
        Exam deletedExam = exams.remove(index);
        return deletedExam;
    }

    /**
     * Delete the {@code exam} from exam list.
     * {@code exam} must exist in the exam list.
     */
    public Exam delete(Exam exam) {
        assert exam != null;
        int index = getIndex(exam);
        return deleteAt(index);
    }

    /**
     * Gets size of the {@code ExamList}.
     *
     * @return size of {@code ExamList}.
     */
    public int size() {
        assert exams != null;
        return exams.size();
    }

    /**
     * Finds the exams of the specific {@code localDateTime}.
     *
     * @param localDateTime localDateTime used to find {@code Exam} of the same {@code
     *     localDateTime}.
     * @return {@code ExamList} with the {@code localDateTime}.
     */
    public ExamList find(LocalDateTime localDateTime) {
        assert exams != null;
        ArrayList<Exam> examsFound = new ArrayList<>();
        for (Exam exam : exams) {
            if (exam.isAt(localDateTime)) {
                examsFound.add(exam);
            }
        }
        return new ExamList(examsFound);
    }

    /**
     * Get the exam at the index from the list.
     *
     * @param index Index of the exam.
     * @return Exam at index.
     */
    public Exam get(int index) {
        return exams.get(index);
    }

    /**
     * Checks whether the {@code ExamList} is empty.
     *
     * @return true if {@code ExamList} is empty, else false.
     */
    public boolean hasNoExam() {
        return exams.isEmpty();
    }

    /**
     * Gets the List of {@code Exams}.
     *
     * @return List of {@code Exams}.
     */
    public ArrayList<Exam> getExams() {
        return new ArrayList<>(exams);
    }

    public Exam getExamAt(int index) {
        return exams.get(index);
    }

    /**
     * Returns String representation of {@code ExamList}.
     *
     * @return ExamList.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (hasNoExam()) {
            return NO_EXAMS_OUTPUT;
        }
        builder.append("Exams: \n");
        for (int i = 0; i < size(); i++) {
            builder.append(i + 1).append(". ")
                .append(get(i)).append("\n");
        }
        return builder.toString();
    }

    /**
     * Checks whether ExamList is the same as {@code input}. Checks based on the list of {@code
     * Exams}.
     *
     * @param other The other object to check with {@code ExamList}.
     * @return true if the {@code exams} is the same as {@code exams} of other.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ExamList)) {
            return false;
        }

        ExamList otherList = (ExamList) other;
        if (exams.size() != otherList.size()) {
            return false;
        } else {
            for (int i = 0; i < otherList.size(); i++) {
                if (!exams.get(i).equals(otherList.exams.get(i))) {
                    return false;
                }
            }
        }

        return true;
    }
}
