package ru.itmo.lab2.pokemons;

import ru.itmo.lab2.moves.Lileep.*;
import ru.ifmo.se.pokemon.*;

public class Lileep extends Pokemon {
    public Lileep(String name, int level) {
        super(name, level);
        super.setType(Type.ROCK, Type.GRASS);
        super.setStats(66, 41, 77, 61, 87, 23);
        super.setMove(new GigaDrain(75, 100), new RockPolish(0, 100), new Rest(0, 100));
    }
}