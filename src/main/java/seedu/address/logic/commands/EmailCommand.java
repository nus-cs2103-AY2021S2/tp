package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

public class EmailCommand extends Command {
    public static final String COMMAND_WORD = "emails";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StringBuilder concatenatedEmails = new StringBuilder();
        List<Student> studentList = model.getFilteredStudentList();

        for (Student stu : studentList) {
            concatenatedEmails.append(stu.getEmail()).append(";");
        }

        return new CommandResult(concatenatedEmails.toString());
    }
}
