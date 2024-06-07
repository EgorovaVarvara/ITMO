package GUI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

public class Localizer {
    private ResourceBundle rb;

    public Localizer(ResourceBundle rb) {
        this.rb = rb;
    }

    public void setBundle(ResourceBundle rb) {
        this.rb = rb;
    }
    public ResourceBundle getBundle() {
        return rb;
    }

    public String getKeyString(String key) {
        return rb.getString(key);
    }

    public String getDate(LocalDate date) {
        if (date == null) return "null";
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(rb.getLocale());
        return date.format(formatter);
    }

    public String getDate(LocalDateTime date) {
        if (date == null) return "null";
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(rb.getLocale());
        return date.format(formatter);
    }
}
