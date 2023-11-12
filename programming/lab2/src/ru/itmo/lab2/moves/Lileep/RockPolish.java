package ru.itmo.lab2.moves.Lileep;

import ru.ifmo.se.pokemon.*;

public class RockPolish extends StatusMove{
    public RockPolish(double pow, double acc){
        super(Type.ROCK, pow, acc);
    }
    @Override
    protected void applySelfEffects(Pokemon p){
        super.applySelfEffects(p);
        Effect EffectObject = new Effect().stat(Stat.SPEED, 2).turns(1);
        p.addEffect(EffectObject);
    }
    @Override
    protected String describe(){
        return "полирует своё тело";
    }
}
