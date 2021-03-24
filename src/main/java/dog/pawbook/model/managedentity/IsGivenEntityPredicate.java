package dog.pawbook.model.managedentity;

import java.util.function.Predicate;

import javafx.util.Pair;

public class IsGivenEntityPredicate implements Predicate<Pair<Integer, Entity>> {
    private final Class<? extends Entity> cls;

    public IsGivenEntityPredicate(Class<? extends Entity> cls) {
        this.cls = cls;
    }

    @Override
    public boolean test(Pair<Integer, Entity> integerEntityPair) {
        return cls.isInstance(integerEntityPair.getValue());
    }
}
