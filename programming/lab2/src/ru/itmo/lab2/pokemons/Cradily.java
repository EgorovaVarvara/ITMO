package ru.itmo.lab2.pokemons;

import ru.itmo.lab2.moves.Lileep.*;
import ru.itmo.lab2.moves.Cradily.*;
import ru.ifmo.se.pokemon.*;

public class Cradily extends Lileep {
    public Cradily(String name, int level) {
        super(name, level);
        super.setType(Type.ROCK, Type.GRASS);
        super.setStats(86, 81, 97, 81, 107, 43);
        super.setMove(new GigaDrain(75, 100), new RockPolish(0, 100), new Rest(0, 100), new Bulldoze(60, 100));
    }
}
