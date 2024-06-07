package threadUtils;

import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Receiver extends Thread{
    private ExecutorService executor;
    private DatagramSocket socket;
    private Interpreter interpreter;
    public Receiver(DatagramSocket socket, Interpreter interpreter){
        executor = Executors.newCachedThreadPool();
        this.socket = socket;
        this.interpreter = interpreter;
    }
    @Override
    public void run(){
        ReceiverTask task = new ReceiverTask(this.socket, this.interpreter);
        executor.execute(task);
    }
}
