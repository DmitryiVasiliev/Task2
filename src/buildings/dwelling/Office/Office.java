package buildings.dwelling.Office;

import buildings.Interface.Space;

import java.io.Serializable;

public class Office implements Space, Serializable {
    private final float DEFAULT_AREA = 250;
    private final int DEFAULT_COUNT_ROOMS = 1;


    private float area;
    private int countRooms;

    public Office() {
        this.area = DEFAULT_AREA;
        this.countRooms = DEFAULT_COUNT_ROOMS;
    }

    public Office(float area) {
        this.area = area;
        this.countRooms = DEFAULT_COUNT_ROOMS;
    }

    public Office(float area, int countRooms) {
        this.area = area;
        this.countRooms = countRooms;
    }

    @Override
    public int getRooms() {
        return countRooms;
    }

    @Override
    public void setRooms(int rooms) {
        this.countRooms = rooms;
    }

    public float getArea() {
        return area;
    }

    @Override
    public void setArea(float area) {
        this.area = area;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getSimpleName() + " (" + this.getRooms() + ", " + this.getArea() + ")");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object object) {
        Office office = (Office) object;
        return (getClass() == object.getClass() && this.getArea() == office.getArea() && this.getRooms() == office.getRooms());
    }

    @Override
    public int hashCode() {
        return (int) new Byte((byte) this.getRooms()) ^ new Byte((byte) this.getArea());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Office cloned = (Office) super.clone();
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
