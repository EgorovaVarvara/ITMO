package utils;

import Commands.Command;
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
        List<String> lines;
        try {
            lines = Files.readAllLines(scriptFile.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileMemory.add(scriptFile);
        for (int index = 0; index < lines.size(); index++) {
            String line = lines.get(index);
            CommandType commandType = CommandUtils.getCommandType(line.split(" ")[0]);
            String[] args = Arrays.copyOfRange(line.split(" "), 1, line.split(" ").length);
            if (commandType == CommandType.EXECUTE_SCRIPT) {
                if (fileMemory.contains(new File(args[0]))) {
                    System.err.println("Recursive file execution. Skipping line: " + line);
                    continue;
                }
                if (FileUtil.isFileExist(args[0])) {
                    this.readScript(new File(args[0]));
                    continue;
                }
            }
            if (Set.of(CommandType.UPDATE, CommandType.REMOVE_BY_ID).contains(commandType)) {
                if (args.length < 1 || index + 6 >= lines.size()) {
                    System.err.println("Not enough arguments/data for command " + commandType + ". Skipping line: " + line);
                    continue;
                }
                String[] musicBandArgs = lines.subList(index + 1, index + 7).toArray(new String[0]);
                args = new String[musicBandArgs.length + 1];
                args[0] = line.split(" ")[1];
                System.arraycopy(musicBandArgs, 0, args, 1, musicBandArgs.length);
                index += 6;
            }
            Command cmd = CommandFactory.createCommand(commandType, args);
            if (cmd != null) commandQueue.add(cmd);
        }
        fileMemory.pop();
        return this;
    }
    public ScriptExecutor readScript() {
        return this.readScript(scriptFile);
    }
}
