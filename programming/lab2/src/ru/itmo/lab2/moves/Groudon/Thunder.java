package ru.itmo.lab2.moves.Groudon;

import ru.ifmo.se.pokemon.*;


public class Thunder extends SpecialMove {
    public Thunder(double pow, double acc) {
        super(Type.ELECTRIC, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect e = new Effect().chance(0.3);
        e.paralyze(p);
        }

    @Override
    protected String describe() {
        return "атакует мощнейшим ударом молнии";
    }
}