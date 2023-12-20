package se.itmo.ru.stuff;

public enum Types {
    PROFESSOR ("Профессор"),
    SIMP ("Простофиля"),
    STONE ("Камень");
    private String type;

    Types(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }
}
