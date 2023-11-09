package ru.itmo.lab2.pokemons;

import ru.itmo.lab2.moves.NidoranM.*;
import ru.ifmo.se.pokemon.*;

public class NidoranM extends Pokemon {
    public NidoranM(String name, int level) {
        super(name, level);
        super.setType(Type.POISON);
        super.setStats(46, 57, 40, 40, 40, 50);
        super.setMove(new Rest(0, 100), new IceBeam(90, 100));
    }
}