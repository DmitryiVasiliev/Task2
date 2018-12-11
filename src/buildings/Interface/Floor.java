package buildings.Interface;

public interface Floor extends Cloneable, Iterable<Space>,Comparable<Floor> {
    int getCnt();

    float getArea();

    int getCntRooms();

    Space[] getSpaces();

    Space getSpace(int n);

    void setSpace(int n);

    void setSpace(int n, Space space);

    void addSpace(int n, Space space);

    void delSpace(int n);

    Space getBestSpace();

    public Object clone() throws CloneNotSupportedException;
}
