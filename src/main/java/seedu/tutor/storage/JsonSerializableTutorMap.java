package seedu.tutor.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.tutor.commons.exceptions.IllegalValueException;
import seedu.tutor.model.TutorMap;
import seedu.tutor.model.ReadOnlyTutorMap;
import seedu.tutor.model.person.Person;

/**
 * An Immutable TutorMap that is serializable to JSON format.
 */
@JsonRootName(value = "tutormap")
class JsonSerializableTutorMap {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTutorMap} with the given persons.
     */
    @JsonCreator
    public JsonSerializableTutorMap(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTutorMap}.
     */
    public JsonSerializableTutorMap(ReadOnlyTutorMap source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TutorMap toModelType() throws IllegalValueException {
        TutorMap tutorMap = new TutorMap();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (tutorMap.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            tutorMap.addPerson(person);
        }
        return tutorMap;
    }

}
