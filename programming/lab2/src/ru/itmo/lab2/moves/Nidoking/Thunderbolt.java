package ru.itmo.lab2.moves.Nidoking;

import ru.ifmo.se.pokemon.*;


public class Thunderbolt extends SpecialMove {
    public Thunderbolt(double pow, double acc) {
        super(Type.ELECTRIC, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect e = new Effect().chance(0.1);
        e.paralyze(p);
        }

    @Override
    protected String describe() {
        return "атакует точным ударом молнии";
    }
}