package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Lists all emails in the address book to the user, delimited by semi colon.
 */
public class EmailCommand extends Command {
    public static final String COMMAND_WORD = "emails";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StringBuilder concatenatedEmails = new StringBuilder();
        List<Student> studentList = model.getFilteredStudentList();

        if (studentList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_NO_STUDENT);
        }

        for (Student student : studentList) {
            assert student.getEmail() != null && !student.getEmail().value.equals("");
            concatenatedEmails.append(student.getEmail()).append(";");
        }

        return new CommandResult(concatenatedEmails.toString());
    }
}
