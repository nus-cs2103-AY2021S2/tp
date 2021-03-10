package seedu.address.model.module;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a ExamList in Module.
 */
public class ExamList {
    public static final String NO_EXAMS_OUTPUT = "You have no exams! Yay! :)";
    private static final String INDENT = "    ";
    private static final String OUTPUT_TITLE = "Exams:";

    // Identity fields.
    private ArrayList<Exam> exams;

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
    public ExamList(ArrayList<Exam> exams) {
        this.exams = exams;
    }

    public boolean contains(Exam exam) {
        boolean hasExam = false;
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
        exams.add(exam);
    }

    /**
     * Removes {@code Exam} at the index from the ExamList.
     *
     * @param index Index of the {@code Exam} to be removed.
     * @return Removed Exam.
     */
    public Exam deleteAt(int index) {
        Exam deletedExam = exams.remove(index);
        return deletedExam;
    }

    /**
     * Delete the {@code exam} from exam list.
     * {@code exam} must exist in the exam list.
     */
    public Exam delete(Exam exam) {
        int index = getIndex(exam);
        return deleteAt(index);
    }

    /**
     * Gets size of the {@code ExamList}.
     *
     * @return size of {@code ExamList}.
     */
    public int size() {
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
        ArrayList<Exam> examsFound = new ArrayList<>();
        for (Exam exam : exams) {
            if (exam.isAt(localDateTime)) {
                examsFound.add(exam);
            }
        }
        return new ExamList(examsFound);
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
     * Sorts the ExamList chronologically.
     */
    public void sort() {
        Collections.sort(exams);
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
        if (hasNoExam()) {
            return NO_EXAMS_OUTPUT;
        }
        String output = OUTPUT_TITLE;
        int index = 1;
        for (Exam exam : exams) {
            output += String.format("\n%s%d: %s", INDENT, index, exam);
            index++;
        }
        return output;
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

        ExamList otherExamList = (ExamList) other;
        return otherExamList.getExams().equals(exams);
    }
}
