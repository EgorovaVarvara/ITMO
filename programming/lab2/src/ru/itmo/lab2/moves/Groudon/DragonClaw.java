package ru.itmo.lab2.moves.Groudon;

import ru.ifmo.se.pokemon.*;

public class DragonClaw extends PhysicalMove{
    public DragonClaw(double pow, double acc){
        super(Type.DRAGON, pow, acc);
    }
    @Override
    protected String describe(){
        return "наносит удар мощными драконьими когтями";
    }
}
