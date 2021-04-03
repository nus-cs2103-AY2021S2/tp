package seedu.booking.model;

@FunctionalInterface
public interface ModelPredicate<T> {
    boolean test(T t, Model model);
}
