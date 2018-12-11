package buildings.net.server.parallel.serial;

import buildings.Buildings;

import buildings.Exceptions.BuildingUnderArrestException;
import buildings.Interface.Building;
import buildings.dwelling.Dwelling.Dwelling;
import buildings.dwelling.Dwelling.Hotel.Hotel;
import buildings.dwelling.Office.OfficeBuilding;
import buildings.net.server.parallel.binary.MonoThreadClientBinary;


import java.io.*;
import java.net.Socket;

public class MonoThreadClientSerial implements Runnable {
    private static Socket client;
    private static int n = 0;

    public MonoThreadClientSerial(Socket client) {
        MonoThreadClientSerial.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println();
            System.out.println("Client №" + ++n);
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            System.out.println("ObjectOutputStream  created");

            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            System.out.println("ObjectInputStream created");
            System.out.println("Server reading from channel");
            int type = in.readInt();
            Building building = Buildings.deserialaizeBuilding(in);
            System.out.println("Server is sending price to client ...");

            try {
                out.writeFloat(costRate(type, building));
                out.flush();
            }
            catch (BuildingUnderArrestException e) {
                out.writeObject(e);
                out.flush();
            }

            Thread.sleep(3000);

            System.out.println("Price was sent to client");
            System.out.println("Client disconnected");

            in.close();
            out.close();
            client.close();

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.getMessage();
        }
    }

    private static float costRate(int type, Building building) throws BuildingUnderArrestException {
        float res = -1;
        int[] cost = {1000, 1500, 2000};
        if(Math.random()*100 <= 10)
            throw new BuildingUnderArrestException("This building is under arrest!");
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
