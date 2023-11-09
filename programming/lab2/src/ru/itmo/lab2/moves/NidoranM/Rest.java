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
        Effect.sleep(p);
        Effect EffectObject = new Effect().stat(Stat.HP, health - 46);
        p.addEffect(EffectObject);
    }

    @Override
    protected String describe() {
        return "отдыхает";
    }
}