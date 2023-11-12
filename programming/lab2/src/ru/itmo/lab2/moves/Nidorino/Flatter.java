package ru.itmo.lab2.moves.Nidorino;

import ru.ifmo.se.pokemon.*;

public class Flatter extends SpecialMove {
    public Flatter(double pow, double acc) {
        super(Type.DARK, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect EffectObject = new Effect().stat(Stat.SPECIAL_ATTACK, 1).turns(1);
        p.addEffect(EffectObject);
        Effect.confuse(p);
    }

    @Override
    protected String describe() {
        return "говорит лестные слова";
    }
}
