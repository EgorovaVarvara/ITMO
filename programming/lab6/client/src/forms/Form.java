package forms;

import errors.InvalidFormException;

public abstract class Form<T> {
    public abstract T build() throws InvalidFormException;
}
