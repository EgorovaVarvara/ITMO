package baseClasses;

import commands.*;

/**
 * The {@code CommandType} enum is a list of possible commands
 *
 * @author Egorova Varvara
 */

public enum CommandType {
    ADD(AddCommand.class, "add {element}: добавить новый элемент в коллекцию"),
    ADD_IF_MAX(AddIfMaxCommand.class, "add_if_max {element}: добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции"),
    ADD_IF_MIN(AddIfMinCommand.class, "add_if_min {element}: добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции"),
    CLEAR(ClearCommand.class, "clear: очистить коллекцию"),
    EXECUTE_SCRIPT(ExecuteScriptCommand.class, "execute_script file_name: считать и исполнить скрипт из указанного файла"),
    EXIT(null, "exit: завершить программу (без сохранения в файл)"),
    FILTER_LESS_THAN_NUMBER_OF_PARTICIPANTS(FilterLessThanNumberOfParticipantsCommand.class, "filter_less_than_number_of_participants numberOfParticipants: вывести элементы, значение поля numberOfParticipants которых меньше заданного"),
    HELP(HelpCommand.class, "help: вывести справку по доступным командам"),
    INFO(InfoCommand.class, "info: вывести информацию о коллекции"),
    PRINT_DESCENDING(PrintDescendingCommand.class, "print_descending: вывести все элементы коллекции в порядке убывания"),
    REMOVE_BY_ID(RemoveByIdCommand.class, "remove_by_id id: удалить элемент из коллекции по его id"),
    REMOVE_LOWER(RemoveLowerCommand.class, "remove_lower {element}: удалить из коллекции все элементы, меньшие, чем заданный"),
    SHOW(ShowCommand.class, "show: вывести все элементы коллекции"),
    SUM_OF_NUMBER_OF_PARTICIPANTS(SumOfNumberOfParticipantsCommand.class, "sum_of_number_of_participants: вывести сумму значений поля numberOfParticipants для всех элементов коллекции"),
    UPDATE(UpdateIdCommand.class, "update id {element}: обновить значение элемента коллекции, id которого равен заданному"),
    DEFAULT(DefaultCommand.class, ""),
    REGISTRATION(RegistrationCommand.class, ""),
    SERVICE(ServiceCommand.class, "");

    private final Class<? extends Command> executableClass;
    private final String description;

    CommandType(Class<? extends Command> executableClass, String description) {
        this.executableClass = executableClass;
        this.description = description;
    }

    public Class<? extends Command> getExecutableClass() {
        return executableClass;
    }
    public String getDescription(){
        return description;
    }
}
