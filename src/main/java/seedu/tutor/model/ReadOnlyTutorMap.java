package seedu.tutor.model;

import javafx.collections.ObservableList;
import seedu.tutor.model.person.Person;

/**
 * Unmodifiable view of a TutorMap
 */
public interface ReadOnlyTutorMap {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
