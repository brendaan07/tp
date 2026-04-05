package seedu.tutor.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_RELATION = new Prefix("r/");
    public static final Prefix PREFIX_SUBJECT = new Prefix("s/");

    // sub-RelateCommand prefixes
    public static final Prefix PREFIX_RELATE_ADD = new Prefix("a\\");
    public static final Prefix PREFIX_RELATE_DELETE = new Prefix("d\\");

    // sub-SubjectCommand prefixes
    public static final Prefix PREFIX_SUBJECT_CHANGE = new Prefix("c\\");
    public static final Prefix PREFIX_SUBJECT_DELETE = new Prefix("d\\");
    public static final Prefix PREFIX_SUBJECT_EDIT = new Prefix("e\\");
}
