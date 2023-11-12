package ru.itmo.lab2.moves.Groudon;

import ru.ifmo.se.pokemon.*;


public class Thunder extends SpecialMove {
    public Thunder(double pow, double acc) {
        super(Type.ELECTRIC, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect e = new Effect().chance(0.3).condition(Status.PARALYZE).turns(1);
        p.setCondition(e);
        }

    @Override
    protected String describe() {
        return "атакует мощнейшим ударом молнии";
    }
}
