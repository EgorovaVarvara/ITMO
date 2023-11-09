package ru.itmo.lab2.pokemons;

import ru.itmo.lab2.moves.Groudon.*;
import ru.ifmo.se.pokemon.*;


public class Groudon extends Pokemon {
    public Groudon(String name, int level) {
        super(name, level);
        super.setType(Type.GROUND);
        super.setStats(100, 150, 140, 100, 90, 90);
        super.setMove(new LavaPlume(80, 100), new FocusBlast(120, 70), new Thunder(110, 70), new DragonClaw(80, 100));
    }
}