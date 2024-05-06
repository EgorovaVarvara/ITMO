package threadUtils;

import commands.Command;
import connectionUtils.Request;
import connectionUtils.Response;
import utils.ServerLogger;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;

public class Interpreter extends Thread{
    //класс должен отправлять response sender-у
    private Request request;
    private DatagramSocket socket;
    private DatagramPacket packet;
    private Sender sender;
    private boolean hasNextPacket = false;
    public Interpreter(Sender sender, DatagramSocket socket){
        this.sender = sender;
        this.socket = socket;
    }
    public void putRequest(Request request, DatagramPacket packet){
        this.request = request;
        this.packet = packet;
        hasNextPacket = true;
    }
    @Override
    public void run(){
        while (!isInterrupted()){
            if (hasNextPacket){
                interpret(request, packet);
            }
        }
    }
    public void interpret(Request request, DatagramPacket packet){
        hasNextPacket = false;
        Command command = request.getCommand();
        ServerLogger.getLogger().log(Level.INFO, "Получена команда %s от %s".formatted((command).getCommandName().toUpperCase(), sender));
        Response response = command.run();
        sender.putResponse(response, packet);
    }
}
