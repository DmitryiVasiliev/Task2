package buildings.net.server.sequental;



import buildings.Buildings;
import buildings.Interface.Building;
import buildings.dwelling.Dwelling.Dwelling;
import buildings.dwelling.Dwelling.Hotel.Hotel;
import buildings.dwelling.Office.OfficeBuilding;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SerialServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("SerialServer info:");
        try (ServerSocket server = new ServerSocket(1337)) {
            Socket client = server.accept();
            System.out.print("Connection accepted.");

            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            System.out.println("ObjectOutputStream  created");

            System.out.println("Server reading from channel");
            Integer type = in.readInt();
            Building building = Buildings.deserialaizeBuilding(in);
            System.out.println("Server is sending price to client ...");

            out.writeFloat(costRate(type, building));
            out.flush();

            Thread.sleep(3000);

            System.out.println("Price was sent to client");
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");

            in.close();
            out.close();
            client.close();

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static float costRate(Integer type, Building building) {
        float res = -1;
        Integer[] cost = {1000, 1500, 2000};
        switch (type) {
            case 0:
                res = (float) ((Dwelling) building).getAreaSpaces() * cost[type];
                break;
            case 1:
                res = (float) ((OfficeBuilding) building).getAreaSpaces() * cost[type];
                break;
            case 2:
                res = (float) ((Hotel) building).getAreaSpaces() * cost[type];
                break;
        }
        return res;
    }
}
