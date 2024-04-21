package commands;

import baseClasses.CommandType;

import java.io.Serial;
import java.io.Serializable;

public interface Command extends Serializable {
    @Serial
    long serialVersionUID = 12345L;

}
