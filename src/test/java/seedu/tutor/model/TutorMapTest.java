package seedu.tutor.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tutor.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.tutor.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.tutor.testutil.Assert.assertThrows;
import static seedu.tutor.testutil.TypicalPersons.ALICE;
import static seedu.tutor.testutil.TypicalPersons.getTypicalTutorMap;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.tutor.model.person.Person;
import seedu.tutor.model.person.exceptions.DuplicatePersonException;
import seedu.tutor.testutil.PersonBuilder;

public class TutorMapTest {

    private final TutorMap tutorMap = new TutorMap();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), tutorMap.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tutorMap.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTutorMap_replacesData() {
        TutorMap newData = getTypicalTutorMap();
        tutorMap.resetData(newData);
        assertEquals(newData, tutorMap);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        TutorMapStub newData = new TutorMapStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> tutorMap.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tutorMap.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInTutorMap_returnsFalse() {
        assertFalse(tutorMap.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInTutorMap_returnsTrue() {
        tutorMap.addPerson(ALICE);
        assertTrue(tutorMap.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInTutorMap_returnsTrue() {
        tutorMap.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(tutorMap.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> tutorMap.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = TutorMap.class.getCanonicalName() + "{persons=" + tutorMap.getPersonList() + "}";
        assertEquals(expected, tutorMap.toString());
    }

    /**
     * A stub ReadOnlyTutorMap whose persons list can violate interface constraints.
     */
    private static class TutorMapStub implements ReadOnlyTutorMap {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        TutorMapStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
