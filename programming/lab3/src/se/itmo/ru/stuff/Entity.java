package se.itmo.ru.stuff;

public abstract class Entity {
    private int friendliness;
    private int dna;
    private String name;
    private int lucky;
    private String favQuote;
    private String interest;

    public Entity(){
        this.friendliness = 0;
        this.dna = 10;
        this.name = "Объект класса Сущность";
        this.lucky = 0;
        this.interest = "";
        this.favQuote = "";
    }
    public final String getFavQuote(){
        return this.favQuote;
    }
    public final void setFavQuote(String f){
        this.favQuote = f;
    }
    public final String getInterest(){
        return this.interest;
    }
    public final void setInterest(String i){
        this.interest = i;
    }
    public final void setLucky(int l){
        this.lucky = l;
    }
    public final int getLucky(){
        return this.lucky;
    }
    public final void setFriendliness(int f){
        this.friendliness = f;
    }
    public final void setDna(int d){
        this.dna = d;
    }
    public final void setName(String n){
        this.name = n;
    }
    public final String getName(){
        return name;
    }

    public final void changeFriendliness(int x){
        this.friendliness += x;
    }

    public final int getFriendliness(){
        return friendliness;
    }

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
