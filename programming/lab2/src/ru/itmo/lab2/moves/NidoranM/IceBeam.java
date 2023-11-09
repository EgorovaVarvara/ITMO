package ru.itmo.lab2.moves.NidoranM;

import ru.ifmo.se.pokemon.*;


public class IceBeam extends SpecialMove {
    public IceBeam(double pow, double acc) {
        super(Type.ICE, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect e = new Effect().chance(0.1);
        e.freeze(p);
        }

    @Override
    protected String describe() {
        return "выпускает леденящий луч";
    }
}
