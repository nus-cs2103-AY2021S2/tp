package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.project.exceptions.DuplicateTodoException;
import seedu.address.model.task.CompletableTodo;

/**
 * Represents a list of Todos.
 * Todo list ensures that there are no duplicates.
 * Also maintains an internal list of sorted todos.
 */
public class TodoList {

    private final ObservableList<CompletableTodo> todos = FXCollections.observableArrayList();
    private final SortedList<CompletableTodo> sortedTodos = new SortedList<>(todos,
            Comparator.comparing(CompletableTodo::getDescription, String::compareToIgnoreCase));

    /**
     * Constructs a empty {@code TodoList}.
     */
    public TodoList() {}

    /**
     * Constructs a {@code TodoList}.
     *
     * @param todos A list of {@code Todos}.
     */
    public TodoList(List<CompletableTodo> todos) {
        requireNonNull(todos);

        this.todos.addAll(todos);
    }

    /**
     * Adds a todo to this {@code TodoList}. The {@code CompletableTodo} must not already exist in the {@code TodoList}.
     *
     * @param todo {@code Todo} to add.
     */
    public void addTodo(CompletableTodo todo) {
        requireNonNull(todo);

        if (hasTodo(todo)) {
            throw new DuplicateTodoException();
        }

        this.todos.add(todo);
    }

    /**
     * Set the {@code Todo} specified by index in the sorted todo list with a new {@code Todo}.
     *
     * @param i index specifies the target {@code Todo} in the sorted todo list.
     * @param todo new {@code Todo} for this index.
     */
    public void setTodo(Integer i, CompletableTodo todo) {
        requireNonNull(todo);

        int todoIndex = sortedTodos.getSourceIndex(i);
        this.todos.set(todoIndex, todo);
    }

    /**
     * Get the {@code Todo} specified by index in the sorted list.
     *
     * @param i index specifies the target {@code Todo} in the sorted list.
     * @return {@code Todo} at this index.
     */
    public CompletableTodo getTodo(Integer i) {
        requireNonNull(i);

        return this.sortedTodos.get(i);
    }

    /**
     * Returns {@code TodoList} as an {@code SortedList<CompletableTodo>}.
     *
     * @return A {@code SortedList<CompletableTodo>}.
     */
    public SortedList<CompletableTodo> getSortedTodos() {
        return this.sortedTodos;
    }

    /**
     * Deletes a todo from this {@code TodoList}.
     *
     * @param i Index of {@code Todo} to be deleted in the sorted list.
     */
    public void deleteTodo(Integer i) {
        requireNonNull(i);

        int todoIndex = sortedTodos.getSourceIndex(i);
        this.todos.remove(todoIndex);
    }

    /**
     * Marks a todo from this {@code TodoList} as done.
     *
     * @param i Index of {@code Todo} in the sorted list to be marked as done.
     */
    public void markAsDone(Integer i) {
        requireNonNull(i);
        CompletableTodo todo = sortedTodos.get(i);
        todo.markAsDone();

        int todoIndex = sortedTodos.getSourceIndex(i);
        // Force observable list to update
        this.todos.set(todoIndex, todo);
    }

    /**
     * Returns a copy of this {@code TodoList}.
     *
     * @return A copy of this {@code TodoList}.
     */
    public TodoList getCopy() {
        return new TodoList(getSortedTodos());
    }

    /**
     * Returns a sequential stream with this {@code TodoList} as its source.
     *
     * @return a sequential Stream over the Todos in this {@code TodoList}.
     */
    public Stream<CompletableTodo> stream() {
        return this.todos.stream();
    }

    /**
     * Checks if the {@code TodoList} already contains the specified {@code todoToCheck}.
     *
     * @param todoToCheck The todo that is to be checked.
     * @return true if this project contains the specified todo, false otherwise.
     */
    public boolean hasTodo(CompletableTodo todoToCheck) {
        for (CompletableTodo todo: todos) {
            if (todoToCheck.equals(todo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a {@code Todo} in the {@code TodoList} done or not.
     *
     * @param index index of that {@code Todo}.
     * @return true if that {@code Todo} is done, false otherwise.
     */
    public boolean checkIsDone(Integer index) {
        return todos.get(index).getIsDone();
    }

    /**
     * Returns the size of the {@code TodoList}.
     *
     * @return size of the {@code TodoList}.
     */
    public int size() {
        return todos.size();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TodoList // instanceof handles nulls
                && this.todos.equals(((TodoList) other).todos)); // state check
    }

    @Override
    public int hashCode() {
        return todos.hashCode();
    }

}
