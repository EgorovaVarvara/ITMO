package ru.itmo.lab2;


import ru.itmo.lab2.pokemons.*;
import ru.ifmo.se.pokemon.Battle;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Cradily cradily = new Cradily("Андрюха", 50);
        Groudon groudon = new Groudon("Работяга", 50);
        Lileep lileep = new Lileep("Никита", 50);
        NidoranM nidoranM = new NidoranM("Лешка", 50);
        Nidorino nidorino = new Nidorino("Леха", 50);
        Nidoking nidoking = new Nidoking("Алексей", 50);

        b.addAlly(nidorino);
        b.addAlly(lileep);
        b.addAlly(groudon);
        b.addFoe(nidoranM);
        b.addFoe(cradily);
        b.addFoe(nidoking);
        b.go();
    }
}