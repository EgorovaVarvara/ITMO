package ru.itmo.lab2.moves.Groudon;

import ru.ifmo.se.pokemon.*;

public class FocusBlast extends SpecialMove {
    public FocusBlast(double pow, double acc) {
        super(Type.FIGHTING, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect EffectObject = new Effect().chance(0.1);
        EffectObject.stat(Stat.SPECIAL_DEFENSE, -1);
    }

    @Override
    protected String describe() {
        return "фокусирует боевую энергию и запускает шар";
    }
}
