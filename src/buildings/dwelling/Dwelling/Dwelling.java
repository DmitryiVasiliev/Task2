package buildings.dwelling.Dwelling;

import buildings.Exceptions.FloorIndexOutBoundsException;
import buildings.Exceptions.SpaceIndexOutOfBoundsException;
import buildings.Interface.Building;
import buildings.Interface.Floor;
import buildings.Interface.Space;
import buildings.Iterators.FloorIterator;

import java.io.Serializable;
import java.util.Iterator;

public class Dwelling implements Building, Serializable {
    private Floor[] floors;

    public Dwelling(int n, int... cnt) {
        if (n < 0)
            throw new FloorIndexOutBoundsException("Error! Number should be positive!");
        floors = new Floor[n];
        for (int i = 0; i < n; i++)
            floors[i] = new DwellingFloor(cnt[i]);
    }

    public Dwelling(Floor... floors) {
        this.floors = new Floor[floors.length];
        for (int i = 0; i < floors.length; i++)
            this.floors[i] = floors[i];
    }


    public int getCntFloors() {
        return floors.length;
    }

    public int getCntSpaces() {
        int cnt = floors[0].getCnt();
        for (int i = 1; i < floors.length; i++)
            cnt += floors[i].getCnt();
        return cnt;
    }


    public float getAreaSpaces() {
        float area = floors[0].getArea();
        for (int i = 1; i < floors.length; i++)
            area += floors[i].getArea();
        return area;
    }


    public int getCntRooms() {
        int cnt = floors[0].getCntRooms();
        for (int i = 1; i < floors.length; i++)
            cnt += floors[i].getCntRooms();
        return cnt;
    }


    public Floor[] getFloors() {
        return floors;
    }


    public Floor getFloor(int n) {
        if (n < 0)
            throw new FloorIndexOutBoundsException("Error! Number should be positive!");
        if (n > this.getCntFloors())
            throw new FloorIndexOutBoundsException("Error! This number was not found!");
        return floors[n];
    }


    public void setFloor(int n, Floor floor) {
        if (n < 0)
            throw new FloorIndexOutBoundsException("Error! Number should be positive!");
        if (n > this.getCntFloors())
            throw new FloorIndexOutBoundsException("Error! This number was not found!");
        floors[n] = floor;
    }


    public Space getSpace(int n) {
        if (n < 0)
            throw new SpaceIndexOutOfBoundsException("Error! Number should be positive!");
        if (n > this.getCntRooms())
            throw new SpaceIndexOutOfBoundsException("Error! This number was not found!");
        int tmp_n = n;
        for (int i = 0; i < floors.length; i++)
            for (int j = 0; j < floors[i].getCnt(); j++)
                if ((tmp_n--) == 0)
                    return floors[i].getSpace(j);
        return null;
    }


    public void setSpace(int n, Space space) {
        if (n < 0)
            throw new SpaceIndexOutOfBoundsException("Error! Number should be positive!");
        if (n > this.getCntRooms())
            throw new SpaceIndexOutOfBoundsException("Error! This number was not found!");
        int tmp_n = n;
        for (int i = 0; i < floors.length; i++)
            for (int j = 0; j < floors[i].getCnt(); j++)
                if ((tmp_n--) == 0) {
                    floors[i].setSpace(j, space);
                    return;
                }
    }


    public void addSpace(int n, Space space) {
        if (n < 0)
            throw new SpaceIndexOutOfBoundsException("Error! Number should be positive!");
        if (n > this.getCntRooms())
            throw new SpaceIndexOutOfBoundsException("Error! This number was not found!");
        int tmp_n = n;
        for (int i = 0; i < floors.length; i++)
            for (int j = 0; j < floors[i].getCnt(); j++)
                if (((tmp_n--) == 0) || (j == floors[i].getCnt() - 1)) {
                    floors[i].addSpace(j, space);
                    return;
                }
    }


    public void delSpace(int n) {
        if (n < 0)
            throw new SpaceIndexOutOfBoundsException("Error! Number should be positive!");
        if (n > this.getCntRooms())
            throw new SpaceIndexOutOfBoundsException("Error! This number was not found!");
        int tmp_n = n;
        for (int i = 0; i < floors.length; i++)
            for (int j = 0; j < floors[i].getCnt(); j++)
                if ((tmp_n--) == 0) {
                    floors[i].delSpace(j);
                    return;
                }
    }


    public Space getBestSpace() {
        Space max = floors[0].getBestSpace();
        for (int i = 1; i < floors.length; i++) {
            if (max.getArea() < floors[i].getBestSpace().getArea())
                max = floors[i].getBestSpace();
        }
        return max;
    }


    public Space[] getSortedSpaces() {

        Space tmp[] = new Space[this.getCntRooms()];
        for (int i = 0; i < tmp.length; i++)
            tmp[i] = this.getSpace(i);
        quickSort(tmp, 0, tmp.length - 1);

        return tmp;
    }

    public void quickSort(Space[] spaces, int low, int high) {
        if (spaces.length == 0)
            return;

        if (low >= high)
            return;

        int middle = low + (high - low) / 2;
        double opora = spaces[middle].getArea();

        int i = low, j = high;
        while (i <= j) {
            while (spaces[i].getArea() > opora) {
                i++;
            }

            while (spaces[j].getArea() < opora) {
                j--;
            }

            if (i <= j) {
                Space temp = spaces[i];
                spaces[i] = spaces[j];
                spaces[j] = temp;
                i++;
                j--;
            }
        }


        if (low < j)
            quickSort(spaces, low, j);

        if (high > i)
            quickSort(spaces, i, high);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getSimpleName() + " (" + this.getCntFloors());
        for (int i = 0; i < this.getCntFloors(); i++) {
            stringBuilder.append(", ");
            stringBuilder.append(this.getFloor(i).toString());
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object object) {
        Dwelling dwelling = (Dwelling) object;
        return (getClass() == object.getClass() && this.getCntFloors() == dwelling.getCntFloors() && getFloors() == dwelling.getFloors());
    }

    @Override
    public int hashCode() {
        int hash = this.getCntFloors();
        for (int i = 0; i < this.getCntFloors(); i++)
            hash ^= this.getFloor(i).hashCode();

        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Dwelling cloned = (Dwelling) super.clone();
        cloned.floors = this.floors.clone();
        for (int i = 0; i < this.getCntFloors(); i++)
            cloned.setFloor(i, (Floor) this.getFloor(i).clone());
        for (int i = 0; i < this.getCntSpaces(); i++)
            cloned.setSpace(i, (Space) this.getSpace(i).clone());
        return cloned;
    }

    @Override
    public Iterator<Floor> iterator() {
        return new FloorIterator(new Dwelling(this.getFloors()));
    }


}


