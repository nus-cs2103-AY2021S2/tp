package dog.pawbook.model.managedentity;

import java.util.function.Predicate;

import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;
import javafx.util.Pair;

public class IsEntityPredicate implements Predicate<Pair<Integer, Entity>> {
    public static final Predicate<Pair<Integer, Entity>> IS_DOG_PREDICATE = new IsEntityPredicate(Dog.class);
    public static final Predicate<Pair<Integer, Entity>> IS_PROGRAM_PREDICATE = new IsEntityPredicate(Program.class);
    public static final Predicate<Pair<Integer, Entity>> IS_OWNER_PREDICATE = new IsEntityPredicate(Owner.class);

    private final Class<? extends Entity> cls;

    private IsEntityPredicate(Class<? extends Entity> cls) {
        this.cls = cls;
    }

    @Override
    public boolean test(Pair<Integer, Entity> integerEntityPair) {
        return cls.isInstance(integerEntityPair.getValue());
    }
}
