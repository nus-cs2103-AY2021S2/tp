package seedu.address.commons.exportutils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import seedu.address.commons.exportutils.exceptions.ExportException;
import seedu.address.model.subject.TutorSubject;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutor.Tutor;

/**
 * Export utilities to help export tutor details
 */
public class ExportUtils {

    private static final File directory = new File("export");

    /**
     * Generates and exports tutor details into a text file and saves it
     */
    public static String exportTutor(Tutor tutor) throws ExportException {

        if (!directory.exists()) {
            if (!directory.mkdir()) {
                throw new ExportException("Unable to create directory");
            }
        }

        String fileName = tutor.getName().toString().replaceAll(" ", "_") + ".txt";
        String feedback = "";

        File exportFile = new File(directory + "/" + fileName);
        try {
            if (exportFile.createNewFile()) {
                feedback += "New file created. ";
            } else {
                feedback += "File already exists, overriding file. ";
            }
        } catch (IOException e) {
            throw new ExportException("Unable to create file");
        }

        try {
            FileWriter myWriter = new FileWriter(exportFile);
            myWriter.write(generateText(tutor));
            myWriter.close();
            feedback += "Successfully wrote to the file at: "
                    + System.getProperty("user.dir") + "/" + exportFile.toString();
        } catch (IOException e) {
            throw new ExportException("Unable to write to file");
        }

        return feedback;
    }

    /**
     * Converts a Person object into human readable text
     *
     * @param tutor to be converted
     */
    private static String generateText(Tutor tutor) {

        StringBuilder text = new StringBuilder();
        text.append("Name:     ").append(tutor.getName().toString()).append("\n");
        text.append("Gender:   ").append(tutor.getGender().toString()).append("\n");
        text.append("Phone:    ").append(tutor.getPhone().toString()).append("\n");
        text.append("Email:    ").append(tutor.getEmail().toString()).append("\n");
        text.append("Address:  ").append(tutor.getAddress().toString()).append("\n");

        if (tutor.hasNotes()) {
            text.append("Notes:    ").append(tutor.getNotes().toString()).append("\n");
        }

        if (tutor.getTags().size() > 0) {
            text.append("Tags:     ");

            for (Tag tag: tutor.getTags()) {
                text.append("[").append(tag.toString()).append("] ");
            }
            text.append("\n");
        }

        text.append("\n");
        text.append("===== SUBJECT LIST =====" + "\n\n");
        for (TutorSubject subject: tutor.getSubjectList()) {
            text.append(subject.getName().toString().toUpperCase()).append("\n");
            text.append("  Level:          ").append(subject.getLevel().toString()).append("\n");
            text.append("  Rate:           ").append(subject.getRate().toString()).append("\n");
            text.append("  Experience:     ").append(subject.getExperience().toString()).append("\n");
            text.append("  Qualification:  ").append(subject.getQualification().toString()).append("\n");
            text.append("\n");
        }
        return text.toString();
    }

}
