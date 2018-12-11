package buildings.threads;

import buildings.Interface.Floor;
import buildings.Interface.Space;

public class Cleaner extends Thread {
    private Floor floor;

    public Cleaner(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        int i = 1;
        for (Space space : floor) {
            System.out.printf("Cleaning room number %d with total area %.2f square meters.\n", i++, space.getArea());
        }
    }
}