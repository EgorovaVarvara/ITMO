package BaseClasses;

public class Coordinates {
    private Long x; //not null
    private float y;
    public Coordinates(Long x, float y) throws IllegalArgumentException{
        if (x == null) throw new IllegalArgumentException("Invalid coordinates");
        this.x = x;
        this.y = y;
    }
    public Coordinates(){
        this.x = 0L;
        this.y = (float)(Math.random() * 1000 - 2000);
    }
    public Long getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    public void setX(Long x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
    public boolean equals(Coordinates c){
        return c.getX() == this.x && c.getY() == this.y;
    }
    @Override
    public String toString(){
        return "(" + x + "; " + y + ")";
    }
}
