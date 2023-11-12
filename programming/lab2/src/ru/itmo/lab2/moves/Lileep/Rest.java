package ru.itmo.lab2.moves.Lileep;

import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove{
    public Rest(double pow, double acc){
        super(Type.PSYCHIC, pow, acc);
    }
    @Override
    protected void applySelfEffects(Pokemon p){
        super.applySelfEffects(p);
        int health = (int) Math.floor(p.getHP());
        Effect EffectObject = new Effect().turns(1).stat(Stat.HP, health - 66).condition(Status.SLEEP);
        p.addEffect(EffectObject);
    }
    @Override
    protected String describe(){
        return "отдыхает";
    }
}
