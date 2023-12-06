package se.itmo.ru.stuff;

public abstract class Entity {
    public int friendliness = 0;
    public int dna = 10;
    public String name = "Объект класса Сущность";

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public int hashCode(){
        return this.dna;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return dna == entity.dna;
    }

}
