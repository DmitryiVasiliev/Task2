package buildings.Iterators;

import buildings.Interface.Building;
import buildings.Interface.Floor;

import java.util.Iterator;

public class FloorIterator implements Iterator<Floor> {
    private Building building;
    private int index = 0;

    public FloorIterator(Building building) {
        this.building = building;
    }

    @Override
    public boolean hasNext() {
        return index != building.getCntFloors() - 1;
    }

    @Override
    public Floor next() {
        return building.getFloor(++index);
    }
}
