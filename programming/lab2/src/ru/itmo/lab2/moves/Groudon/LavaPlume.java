package ru.itmo.lab2.moves.Groudon;

import ru.ifmo.se.pokemon.*;

public class LavaPlume extends SpecialMove {
    public LavaPlume(double pow, double acc) {
        super(Type.FIRE, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect e = new Effect().chance(0.3).condition(Status.BURN).turns(1);
        p.setCondition(e);
    }

    @Override
    protected String describe() {
        return "выплескивает поток лавы";
    }
}
