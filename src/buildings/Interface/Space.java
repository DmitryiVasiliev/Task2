package buildings.Interface;

public interface Space extends Cloneable, Comparable<Space> {
    int getRooms();

    void setRooms(int rooms);

    float getArea();

    void setArea(float area);

    public Object clone() throws CloneNotSupportedException;
}
