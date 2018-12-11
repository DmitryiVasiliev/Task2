package buildings.net.client;

import buildings.Buildings;

import java.io.*;
import java.net.Socket;

public class BinaryClient {

    private static File fileInput1 = new File("in.bin");
    private static File fileInput2 = new File("in2.bin");

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("BinaryClient info: ");
        Socket socket = new Socket("localhost", 1337);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        while(!socket.isOutputShutdown()){
            System.out.println("Client start writing in channel...");
            Thread.sleep(1000);

            DataInputStream fis = new DataInputStream(new FileInputStream(fileInput2));
            int type = fis.readInt();
            fis.close();

            dos.writeInt(type);
            dos.flush();

            Buildings.outputBuilding(Buildings.inputBuilding(new FileInputStream(fileInput1)), dos);
            dos.flush();
            System.out.println("Client sent Building to server.");
            Thread.sleep(2000);

            System.out.println("Client sent message about Building & start waiting for data from server...");
            Thread.sleep(2000);


            System.out.println("waiting for price of Building...");
            Thread.sleep(1000);
            float price = dis.readFloat();
            System.out.printf("Price for this Building %.2f $\n", price);
            try {
                PrintWriter writer = new PrintWriter(new FileOutputStream("result.txt"));
                writer.print(price);
                writer.close();
            }
            catch (IOException e) { }
            break;
        }

        System.out.println("Closing connections & channels on clientSide - DONE.");
    }
}


