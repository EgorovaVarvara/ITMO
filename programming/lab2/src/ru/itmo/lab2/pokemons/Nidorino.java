package ru.itmo.lab2.pokemons;

import ru.itmo.lab2.moves.NidoranM.*;
import ru.itmo.lab2.moves.Nidorino.*;
import ru.ifmo.se.pokemon.*;

public class Nidorino extends NidoranM {
    public Nidorino(String name, int level) {
        super(name, level);
        super.setType(Type.POISON);
        super.setStats(61, 72, 57, 55, 55, 65);
        super.setMove(new Rest(0, 100), new IceBeam(90, 100), new Flatter(0, 100));
    }
}