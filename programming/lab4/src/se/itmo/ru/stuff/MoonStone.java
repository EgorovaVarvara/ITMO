package se.itmo.ru.stuff;

public class MoonStone {
    private final String name = "Лунный " + Types.STONE.getType();
    private boolean degreeOfKnowledge;
    private boolean flyable;
    private String energyInteraction;
    public String getName(){
        return this.name;
    }
    public boolean getDegreeOfKnowledge(){
        return this.degreeOfKnowledge;
    }
    public boolean getFlyable(){
        return this.flyable;
    }
    public String getEnergyInteraction(){
        return this.energyInteraction;
    }
    public void setStats(){
        class statSetters{
            public static boolean isLearned(){
                return (Math.random() >= 0.2);
            }
            public static boolean isFly(){
                return (Math.random() >= 0.3);
            }
            public static String interaction(String name){
                return ("Энергия " + name + " взаимодействует с магнитной энергией.");
            }
        }
        this.degreeOfKnowledge = statSetters.isLearned();
        this.flyable = statSetters.isFly();
        this.energyInteraction = statSetters.interaction(this.name);
    }
}
