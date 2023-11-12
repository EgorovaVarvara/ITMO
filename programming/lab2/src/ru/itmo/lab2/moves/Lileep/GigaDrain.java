package ru.itmo.lab2.moves.Lileep;

import ru.ifmo.se.pokemon.*;

public class GigaDrain extends SpecialMove{
    public GigaDrain(double pow, double acc){
        super(Type.GRASS, pow, acc);
    }
    @Override
    protected void applySelfEffects(Pokemon p){
        super.applySelfEffects(p);
        Effect EffectObject = new Effect().stat(Stat.HP, -2).turns(1);
        p.addEffect(EffectObject);
    }
    @Override
    protected String describe(){
        return "высосал питательные вещества";
    }
}
