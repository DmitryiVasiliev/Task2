package buildings.net.client;

import buildings.Buildings;
import buildings.dwelling.Dwelling.*;

import java.io.*;
import java.net.Socket;

public class SerialClient  {
    private static File fileInput1 = new File("ins.txt");
    private static File fileInput2 = new File("ins2.txt");

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("SerialClient info: ");
        Socket socket = new Socket("localhost", 1337);

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        while(!socket.isOutputShutdown()){
            System.out.println("Client start writing in channel...");
            Thread.sleep(1000);

            ObjectInputStream fis = new ObjectInputStream(new FileInputStream(fileInput2));
            Integer type = fis.readInt();
            fis.close();

            oos.writeInt(type);
            oos.flush();

            Buildings.serializeBuilding(Buildings.deserialaizeBuilding(new FileInputStream(fileInput1)), oos);
            oos.flush();

            System.out.println("Client sent Building to server.");
            Thread.sleep(2000);

            System.out.println("Client sent message about Building & start waiting for data from server...");
            Thread.sleep(2000);


            System.out.println("waiting for price of Building...");
            Thread.sleep(1000);
            float price = ois.readFloat();
            System.out.printf("Price for this Building %.2f $\n", price);
            try {
                PrintWriter writer = new PrintWriter(new FileOutputStream("results.txt"));
                writer.print(price);
                writer.close();
            }
            catch (IOException e) { }
            break;
        }

        System.out.println("Closing connections & channels on clientSide - DONE.");
    }
}
