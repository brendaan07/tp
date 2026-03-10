package seedu.tutor.logic.parser;

import seedu.tutor.logic.commands.RelateCommand;

public class RelateCommandParser {

    public RelateCommand parse(String args) {
        String[] arguments = args.split("\\s+");
        char type = arguments[1].charAt(0);
        String details = arguments[1].substring(2);
        return RelateCommand.create(type, details);
    }
}
