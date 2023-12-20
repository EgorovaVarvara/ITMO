package se.itmo.ru.stuff;

public enum Sciences {
    GEOLOGY ("геология"),
    MINERALOGY ("минералогия"),
    CRYSTALLOGRAPHY ("кристаллография");

    private String science;
    Sciences(String science){
        this.science = science;
    }
    public String getScience(){return science;}
}
