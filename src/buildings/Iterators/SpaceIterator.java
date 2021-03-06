package buildings.Iterators;

import buildings.Interface.Floor;
import buildings.Interface.Space;

import java.util.Iterator;

public class SpaceIterator implements Iterator<Space> {
    private Floor floor;
    private int index = 0;

    public SpaceIterator(Floor floor) {
        this.floor = floor;
    }

    @Override
    public boolean hasNext() {
        return index != floor.getCnt() - 1;
    }

    @Override
    public Space next() {
        return floor.getSpace(++index);
    }
}
