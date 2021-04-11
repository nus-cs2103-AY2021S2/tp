package dog.pawbook.testutil;

import java.util.Set;

import dog.pawbook.logic.commands.EditProgramCommand.EditProgramDescriptor;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.util.SampleDataUtil;

/**
 * A utility class to help with building EditProgramDescriptor objects.
 */
public class EditProgramDescriptorBuilder
        extends EditEntityDescriptorBuilder<EditProgramDescriptorBuilder, EditProgramDescriptor> {

    public EditProgramDescriptorBuilder() {
        super(new EditProgramDescriptor());
    }

    public EditProgramDescriptorBuilder(EditProgramDescriptor descriptor) {
        super(new EditProgramDescriptor(descriptor));
    }

    /**
     * Returns an {@code EditProgramDescriptor} with fields containing {@code program}'s details
     */
    public EditProgramDescriptorBuilder(Program program) {
        super(new EditProgramDescriptor(), program);
        descriptor.setSessions(program.getSessions());
    }

    /**
     * Parses the {@code sessions} into a {@code Set<Session>} and set it to the {@code EditProgramDescriptor}
     * that we are building.
     */
    public EditProgramDescriptorBuilder withSessions(String... sessions) {
        Set<Session> sessionSet = SampleDataUtil.getSessionSet(sessions);
        descriptor.setSessions(sessionSet);
        return this;
    }

    @Override
    protected EditProgramDescriptorBuilder self() {
        return this;
    }
}
