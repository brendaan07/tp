package seedu.tutor.model;

import static java.util.Objects.requireNonNull;
import static seedu.tutor.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.tutor.commons.core.GuiSettings;
import seedu.tutor.commons.core.LogsCenter;
import seedu.tutor.model.person.Person;

/**
 * Represents the in-memory model of the tutor map data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TutorMap tutorMap;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given tutorMap and userPrefs.
     */
    public ModelManager(ReadOnlyTutorMap tutorMap, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(tutorMap, userPrefs);

        logger.fine("Initializing with tutormap: " + tutorMap + " and user prefs " + userPrefs);

        this.tutorMap = new TutorMap(tutorMap);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.tutorMap.getPersonList());
    }

    public ModelManager() {
        this(new TutorMap(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTutorMapFilePath() {
        return userPrefs.getTutorMapFilePath();
    }

    @Override
    public void setTutorMapFilePath(Path tutorMapFilePath) {
        requireNonNull(tutorMapFilePath);
        userPrefs.setTutorMapFilePath(tutorMapFilePath);
    }

    //=========== TutorMap ================================================================================

    @Override
    public void setTutorMap(ReadOnlyTutorMap tutorMap) {
        this.tutorMap.resetData(tutorMap);
    }

    @Override
    public ReadOnlyTutorMap getTutorMap() {
        return tutorMap;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return tutorMap.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        tutorMap.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        tutorMap.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        tutorMap.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedTutorMap}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return tutorMap.equals(otherModelManager.tutorMap)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

}
