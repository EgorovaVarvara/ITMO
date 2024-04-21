package utils;

import baseClasses.MusicBand;
import baseClasses.MusicBandCreator;
import commands.*;
import baseClasses.CommandType;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class ScriptExecutor {
    private final ArrayList<Command> commandQueue = new ArrayList<>();
    private final File scriptFile;
    private final ArrayDeque<File> fileMemory = new ArrayDeque<>();
    public ScriptExecutor(File scriptFile){
        this.scriptFile = scriptFile;
    }
    public ArrayList<Command> getCommandList() {
        return commandQueue;
    }
    private ScriptExecutor readScript(File scriptFile) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(scriptFile.toPath(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        fileMemory.add(scriptFile);
        for (int index = 0; index < lines.size(); index++) {
            String line = lines.get(index);
            CommandType commandType = CommandUtils.getCommandType(line.split(" ")[0]);
            String[] args = Arrays.copyOfRange(line.split(" "), 1, line.split(" ").length);
            Command cmd = null;
            if (commandType == CommandType.EXECUTE_SCRIPT) {
                if (fileMemory.contains(new File(args[0]))) {
                    System.err.println("Обнаружена рекурсия, строка пропущена");
                    continue;
                }
                if (FileUtil.isFileExist(args[0])) {
                    this.readScript(new File(args[0]));
                    continue;
                }
            }
            if (Set.of(CommandType.UPDATE, CommandType.REMOVE_LOWER).contains(commandType)){
                if (args.length < 1 || index + 6 >= lines.size() || args.length > 1) {
                    System.out.println("Неверное количество аргументов для команды " + commandType + ". Строка пропущена");
                    continue;
                }
                String[] musicBandArgs = lines.subList(index + 1, index + 7).toArray(new String[0]);
                args = new String[musicBandArgs.length + 1];
                args[0] = line.split(" ")[1];
                System.arraycopy(musicBandArgs, 0, args, 1, musicBandArgs.length);
                index += 6;
                MusicBand musicBand = MusicBandCreator.createMusicBand(musicBandArgs);
                if (musicBand != null) {
                    try{
                        musicBand.setId(Integer.parseInt(args[0]));
                    } catch (NumberFormatException e){
                        System.out.println("Неверные аргументы для команды " + commandType + ". Команда пропущена");
                        continue;
                    }
                    if (commandType == CommandType.UPDATE){
                        cmd = new UpdateIdCommand(musicBand);
                    } else {
                        cmd = new RemoveLowerCommand(musicBand);
                    }
                } else {
                    System.out.println("Неверные аргументы для команды " + commandType + ". Команда пропущена");
                    continue;
                }
            } else if (Set.of(CommandType.ADD, CommandType.ADD_IF_MIN, CommandType.ADD_IF_MAX).contains(commandType)) {
                if (args.length != 0 || index + 6 >= lines.size()){
                    System.out.println("Неверное количество аргументов или неверные аргументы для команды " + commandType + ". Строка пропущена");
                    continue;
                }
                String[] musicBandArgs = lines.subList(index + 1, index + 7).toArray(new String[0]);
                args = new String[musicBandArgs.length + 1];
                index += 6;
                MusicBand musicBand = MusicBandCreator.createMusicBand(musicBandArgs);
                if (musicBand != null){
                    switch (commandType){
                        case ADD -> cmd = new AddCommand(musicBand);
                        case ADD_IF_MAX -> cmd = new AddIfMaxCommand(musicBand);
                        case ADD_IF_MIN -> cmd = new AddIfMinCommand(musicBand);
                    }
                } else {
                    System.out.println("Неверное количество аргументов или неверные аргументы для команды " + commandType + ". Строка пропущена");
                    continue;
                }
            } else {
                cmd = CommandFactory.createCommand(commandType, line.split(" "));
            }
            if (cmd != null) commandQueue.add(cmd);
        }
        fileMemory.pop();
        return this;
    }
    public ScriptExecutor readScript() {
        return this.readScript(scriptFile);
    }
}
