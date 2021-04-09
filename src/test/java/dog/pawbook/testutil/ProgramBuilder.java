package dog.pawbook.testutil;

import java.util.HashSet;
import java.util.Set;

import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.util.SampleDataUtil;

public class ProgramBuilder extends EntityBuilder<ProgramBuilder, Program> {

    public static final String DEFAULT_NAME = "Basic Obedience Training";

    protected Set<Session> sessionSet;
    protected Set<Integer> dogIdSet;

    /**
     * Creates a {@code ProgramBuilder} with the default details.
     */
    public ProgramBuilder() {
        super(DEFAULT_NAME);
        sessionSet = new HashSet<>();
        dogIdSet = new HashSet<>();
    }

    /**
     * Initializes the ProgramBuild with the data of {@code programToCopy}.
     */
    public ProgramBuilder(Program programToCopy) {
        super(programToCopy);
        sessionSet = new HashSet<>(programToCopy.getSessions());
        dogIdSet = new HashSet<>(programToCopy.getDogIdSet());
    }

    /**
     * Parses the {@code sessions} into a {@code Set<Session>} and set it to the {@code Program} we are building.
     */
    public final ProgramBuilder withSessions(String... sessions) {
        this.sessionSet = SampleDataUtil.getSessionSet(sessions);
        return self();
    }

    /**
     * Parses the {@code tags} into a {@code Set<Integer>} and set it to the {@code Program} that we are building.
     */
    public ProgramBuilder withDogs(Integer... dogIds) {
        this.dogIdSet = SampleDataUtil.getIdSet(dogIds);
        return self();
    }

    @Override
    public Program build() {
        return new Program(name, sessionSet, tags, dogIdSet);
    }

    @Override
    protected ProgramBuilder self() {
        return this;
    }
}
