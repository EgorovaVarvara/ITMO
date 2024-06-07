package utils;

import baseClasses.MusicBand;

import java.util.HashSet;

public interface Parser {
    void save();
    HashSet<MusicBand> load();
}
