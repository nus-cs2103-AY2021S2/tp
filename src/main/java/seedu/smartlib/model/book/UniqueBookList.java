package seedu.smartlib.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.smartlib.model.book.exceptions.BookNotFoundException;
import seedu.smartlib.model.book.exceptions.DuplicateBookException;

/**
 * A list of books that enforces uniqueness between its elements and does not allow nulls.
 * A book is considered unique by comparing using {@code Book#isSameBook(Book)}. As such, adding and updating of
 * books uses Book#isSameBook(Book) for equality so as to ensure that the book being added or updated is
 * unique in terms of identity in the UniqueBookList. However, the removal of a book uses Book#equals(Object) so
 * as to ensure that the book with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Book#isSameBook(Book)
 */
public class UniqueBookList implements Iterable<Book> {

    private final ObservableList<Book> internalList = FXCollections.observableArrayList();
    private final ObservableList<Book> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent book as the given argument.
     *
     * @param toCheck the book to be checked.
     * @return true if the list contains an equivalent book as the given argument, and false otherwise.
     */
    public boolean contains(Book toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameBook);
    }

    /**
     * Adds a book to the list.
     * The book must not already exist in the list.
     *
     * @param toAdd the book to be added.
     */
    public void addBook(Book toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateBookException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent book from the list.
     * The book must exist in the list.
     *
     * @param toRemove the book to be removed.
     */
    public void remove(Book toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BookNotFoundException();
        }
    }

    /**
     * Updates the books in the unique book list.
     *
     * @param replacement the new list of books.
     */
    public void setBooks(UniqueBookList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code books}.
     * {@code books} must not contain duplicate books.
     *
     * @param books the new list of books.
     */
    public void setBooks(List<Book> books) {
        requireAllNonNull(books);
        if (!booksAreUnique(books)) {
            throw new DuplicateBookException();
        }

        internalList.setAll(books);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @return the backing list of books.
     */
    public ObservableList<Book> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns an iterator for the unique book list.
     *
     * @return an iterator for the unique book list.
     */
    @Override
    public Iterator<Book> iterator() {
        return internalList.iterator();
    }

    /**
     * Checks if this UniqueBookList is equal to another UniqueBookList.
     *
     * @param other the other UniqueBookList to be compared.
     * @return true if this UniqueBookList is equal to the other UniqueBookList, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBookList // instanceof handles nulls
                && internalList.equals(((UniqueBookList) other).internalList));
    }

    /**
     * Generates a hashcode for this UniqueBookList.
     *
     * @return the hashcode for this UniqueBookList.
     */
    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code books} contains only unique books.
     *
     * @param books the list of books to be checked.
     * @return true if {@code books} contains only unique books, and false otherwise.
     */
    private boolean booksAreUnique(List<Book> books) {
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = i + 1; j < books.size(); j++) {
                if (books.get(i).isSameBook(books.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Updates a book in the book list with a new book.
     *
     * @param target the book in the book list that is to be updated.
     * @param editedBook the new book.
     */
    public void setBook(Book target, Book editedBook) {
        requireAllNonNull(target, editedBook);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BookNotFoundException();
        }

        if (!target.isSameBook(editedBook) && contains(editedBook)) {
            throw new DuplicateBookException();
        }

        internalList.set(index, editedBook);
    }

}
