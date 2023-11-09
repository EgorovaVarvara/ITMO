package ru.itmo.lab2.pokemons;

import ru.itmo.lab2.moves.NidoranM.*;
import ru.itmo.lab2.moves.Nidorino.*;
import ru.itmo.lab2.moves.Nidoking.*;
import ru.ifmo.se.pokemon.*;

public class Nidoking extends Nidorino {
    public Nidoking(String name, int level) {
        super(name, level);
        super.setType(Type.POISON, Type.GROUND);
        super.setStats(81, 102, 77, 85, 75, 85);
        super.setMove(new IceBeam(90, 100), new Flatter(0, 100), new Thunderbolt(90, 100), new Rest(0, 100));
    }
}