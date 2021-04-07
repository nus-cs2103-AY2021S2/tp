package seedu.address.logic.commands.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_MAIN_PATIENTS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medical.MedicalRecord;
import seedu.address.model.person.Patient;

public class SaveMedicalRecordCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Medical record added for: %s";
    private final Patient patient;
    private final MedicalRecord mrec;


    /**
     * @param patient in the filtered patient list to add medical record to
     * @param mrec MedicalRecord to be saved
     */
    public SaveMedicalRecordCommand(Patient patient, MedicalRecord mrec) {
        requireNonNull(patient);
        requireNonNull(mrec);
        this.patient = patient;
        this.mrec = mrec;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_MAIN_PATIENTS);
        this.patient.addMedicalRecord(mrec);
        model.setPerson(this.patient, this.patient);
        model.selectPatient(this.patient);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.patient.getName(), mrec.getDateDisplay()),
                false, false, patient, null, null, null, false);
    }
}
