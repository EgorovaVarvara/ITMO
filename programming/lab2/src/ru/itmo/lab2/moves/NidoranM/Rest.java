package ru.itmo.lab2.moves.NidoranM;

import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {
    public Rest(double pow, double acc) {
        super(Type.PSYCHIC, pow, acc);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        int health = (int) Math.floor(p.getHP());
        super.applySelfEffects(p);
        Effect EffectObject = new Effect().condition(Status.SLEEP).stat(Stat.HP, health-46).turns(1);
        p.addEffect(EffectObject);
    }

    @Override
    protected String describe() {
        return "отдыхает";
    }
}
