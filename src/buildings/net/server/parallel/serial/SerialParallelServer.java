package buildings.net.server.parallel.serial;

import buildings.net.server.parallel.binary.MonoThreadClientBinary;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SerialParallelServer {
    private static ExecutorService executeIt = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("SerialParallelServer info:");
        try (ServerSocket server = new ServerSocket(1337)) {
            while (!server.isClosed()) {
                Socket client = server.accept();
                executeIt.execute(new MonoThreadClientBinary(client));
                System.out.println("Connection accepted.");
            }
            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
