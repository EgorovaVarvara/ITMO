package BaseClasses;

public class Label {
    private long bands;
    public long getBands(){
        return this.bands;
    }
    public void setBands(long bands){
        this.bands = bands;
    }
    public Label(long bands){
        this.bands = bands;
    }
    public Label(){
        this.bands = (long)(Math.random() * 50 + 1);
    }
    @Override
    public String toString(){
        return String.valueOf(this.bands);
    }
    public boolean equals(Label label){
        return (label.getBands() == this.bands);
    }
}
