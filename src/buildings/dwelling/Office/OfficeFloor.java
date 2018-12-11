package buildings.dwelling.Office;

import buildings.Exceptions.FloorIndexOutBoundsException;
import buildings.Exceptions.SpaceIndexOutOfBoundsException;
import buildings.Interface.Floor;
import buildings.Interface.Space;
import buildings.Iterators.SpaceIterator;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeFloor implements Floor, Serializable {


    class ListElement implements Serializable {
        Space data;
        ListElement next;

        public ListElement(Space data, ListElement next) {
            this.data = data;
            this.next = next;
        }
    }

    private ListElement head;

    private ListElement getElement(int n) {
        if (head == null)
            return null;
        ListElement tmp = head.next;
        for (int i = 0; i < n; i++) {
            tmp = tmp.next;
        }
        return tmp;
    }

    private void addElement(int n, ListElement listElement) {
        if (n == 0 || head == null) {
            if (head == null) {
                head = new ListElement(listElement.data, null);
                head.next = head;
            } else
                head.next = new ListElement(listElement.data, head.next);
        } else {
            ListElement tmp = this.getElement(n - 1);
            tmp.next = new ListElement(listElement.data, tmp.next);
        }


    }

    private void delElement(int n) {
        if (head == null)
            return;
        if (n == 0) {
            if (head.next.next != head) {
                ListElement tmp = head.next;
                head.next = tmp.next;
            } else
                head.next = head;
        } else {
            ListElement tmp = this.getElement(n - 1);
            tmp.next = tmp.next.next;
        }
    }

    public OfficeFloor(int cnt) {
        if (cnt < 0)
            throw new FloorIndexOutBoundsException("Error! Count should be positive!");
        for (int i = 0; i < cnt; i++)
            this.addElement(i, new ListElement(new Office(), null));
    }

    public OfficeFloor(Space... spaces) {
        for (int i = 0; i < spaces.length; i++)
            this.addElement(i, new ListElement(spaces[i], null));
    }

    @Override
    public int getCnt() {
        if (head == null || head.next == head)
            return 0;
        int j = 0;
        ListElement tmp = this.getElement(j);
        if (tmp != null) {
            while (tmp.next != head) {
                tmp = tmp.next;
                j++;
            }
        }
        return ++j;
    }

    @Override
    public float getArea() {
        if (head == null || head.next == head)
            return 0;
        float res = this.getElement(0).data.getArea();
        for (int i = 1; i < this.getCnt(); i++)
            res += this.getElement(i).data.getArea();
        return res;
    }

    @Override
    public int getCntRooms() {
        if (head == null || head.next == head)
            return 0;
        int res = 0;
        for (int i = 0; i < this.getCnt(); i++)
            res += this.getElement(i).data.getRooms();
        return res;
    }

    @Override
    public Space[] getSpaces() {
        if (head == null || this.getCnt() == 0)
            return null;
        Space res[] = new Space[this.getCnt()];
        for (int i = 0; i < this.getCnt(); i++)
            res[i] = this.getElement(i).data;
        return res;
    }

    @Override
    public Space getSpace(int n) {
        if (n < 0)
            throw new SpaceIndexOutOfBoundsException("Error! Number should be positive!");
        if (n > this.getCnt())
            throw new SpaceIndexOutOfBoundsException("Error! This number was not found!");
        if (head == null || this.getCnt() == 0)
            return null;
        return this.getElement(n).data;
    }

    @Override
    public void setSpace(int n) {
        if (n < 0)
            throw new SpaceIndexOutOfBoundsException("Error! Number should be positive!");
        if (n > this.getCnt())
            throw new SpaceIndexOutOfBoundsException("Error! This number was not found!");
        if (head == null || head.next.next == null)
            return;
        this.getElement(n).data = new Office();
    }

    @Override
    public void setSpace(int n, Space space) {
        if (n < 0)
            throw new SpaceIndexOutOfBoundsException("Error! Number should be positive!");
        if (n > this.getCnt())
            throw new SpaceIndexOutOfBoundsException("Error! This number was not found!");
        if (head == null)
            return;
        this.getElement(n).data = space;
    }

    @Override
    public void addSpace(int n, Space space) {
        if (n < 0)
            throw new SpaceIndexOutOfBoundsException("Error! Number should be positive!");
        if (n > this.getCnt())
            throw new SpaceIndexOutOfBoundsException("Error! This number was not found!");
        this.addElement(n, new ListElement(space, null));
    }

    public void addSpace(Space space) {
        this.addElement(this.getCnt(), new ListElement(space, null));
    }

    @Override
    public void delSpace(int n) {
        if (n < 0)
            throw new SpaceIndexOutOfBoundsException("Error! Number should be positive!");
        if (n > this.getCnt())
            throw new SpaceIndexOutOfBoundsException("Error! This number was not found!");
        this.delElement(n);
    }

    @Override
    public Space getBestSpace() {
        if (head == null || this.getCnt() == 0)
            return null;
        Space max = this.getSpace(0);
        for (int i = 1; i < this.getCnt(); i++) {
            if (max.getArea() < this.getElement(i).data.getArea())
                max = this.getElement(i).data;
        }
        return max;
    }

    @Override
    public boolean equals(Object object) {
        OfficeFloor officeFloor = (OfficeFloor) object;
        return (getClass() == object.getClass() && this.getCnt() == officeFloor.getCnt() && getSpaces() == officeFloor.getSpaces());
    }

    @Override
    public int hashCode() {
        int hash = this.getCnt();
        for (int i = 0; i < this.getCnt(); i++) {
            hash ^= this.getSpace(i).hashCode();

        }
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        OfficeFloor cloned = (OfficeFloor) super.clone();
        cloned.head = null;
        Space[] tmp_spaces = this.getSpaces();
        for (int i = 0; i < tmp_spaces.length; i++)
            cloned.addSpace((Space) tmp_spaces[i].clone());
        return cloned;
    }

    @Override
    public Iterator<Space> iterator() {
        return new SpaceIterator(new OfficeFloor(this.getSpaces()));
    }

    @Override
    public int compareTo(Floor floor) {
        if (this.getCnt() > floor.getCnt())
            return 1;
        if (this.getCnt() == floor.getCnt())
            return 0;
        return -1;
    }


}
