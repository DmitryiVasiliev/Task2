package buildings.dwelling.Dwelling;

import buildings.Interface.Space;

import java.io.Serializable;

public class Flat implements Space, Serializable {
    private static final int countRooms = 2;
    private static final int area = 50;
    private int numberOfRooms;
    private float areaOfFlat;

    public Flat() {
        this.numberOfRooms = countRooms;
        this.areaOfFlat = area;
    }

    public Flat(float area) {
        this.numberOfRooms = countRooms;
        this.areaOfFlat = area;
    }

    public Flat(float area, int countOfRooms) {
        this.numberOfRooms = countOfRooms;
        this.areaOfFlat = area;
    }


    @Override
    public int getRooms() {
        return this.numberOfRooms;
    }

    @Override
    public void setRooms(int rooms) {
        this.numberOfRooms = rooms;
    }

    @Override
    public float getArea() {
        return this.areaOfFlat;
    }

    @Override
    public void setArea(float area) {
        this.areaOfFlat = area;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getSimpleName() + " (" + this.getRooms() + ", " + this.getArea() + ")");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object object) {
        Flat flat = (Flat) object;
        return (getClass() == object.getClass() && this.getArea() == flat.getArea() && this.getRooms() == flat.getRooms());
    }

    @Override
    public int hashCode() {
        return (int) new Byte((byte) this.getRooms()) ^ new Byte((byte) this.getArea());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Flat cloned = (Flat) super.clone();
        return cloned;
    }

    @Override
    public int compareTo(Space space) {
        if (this.getArea() > space.getArea())
            return 1;
        if (this.getArea() == space.getArea())
            return 0;
        return -1;
    }

}
