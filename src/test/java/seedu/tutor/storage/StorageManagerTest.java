package seedu.tutor.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.tutor.testutil.TypicalPersons.getTypicalTutorMap;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.tutor.commons.core.GuiSettings;
import seedu.tutor.model.TutorMap;
import seedu.tutor.model.ReadOnlyTutorMap;
import seedu.tutor.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonTutorMapStorage tutorMapStorage = new JsonTutorMapStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(tutorMapStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void tutorMapReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        TutorMap original = getTypicalTutorMap();
        storageManager.saveTutorMap(original);
        ReadOnlyTutorMap retrieved = storageManager.readTutorMap().get();
        assertEquals(original, new TutorMap(retrieved));
    }

    @Test
    public void getTutorMapFilePath() {
        assertNotNull(storageManager.getTutorMapFilePath());
    }

}
