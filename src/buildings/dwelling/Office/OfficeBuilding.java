package buildings.dwelling.Office;

import buildings.Exceptions.FloorIndexOutBoundsException;
import buildings.Interface.Building;
import buildings.Interface.Floor;
import buildings.Interface.Space;
import buildings.Iterators.FloorIterator;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeBuilding implements Building, Serializable {
    class ListElement implements Serializable {
        ListElement next;
        ListElement prev;
        Floor data;

        public ListElement(Floor data, ListElement next, ListElement prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private ListElement head;

    private ListElement getElement(int n) {

        if (head == null || n < 0)
            return null;
        ListElement tmp = head.next;
        int i = 0;
        while (tmp != head) {
            if (i++ == n)
                return tmp;
            tmp = tmp.next;
        }
        return null;
    }

    private void addElement(int n, ListElement listElement) {
        if (n < 0) return;
        if (n == 0 || (head == null)) {
            if (head == null) {
                head = new ListElement(null, null, null);
                head.next = new ListElement(listElement.data, head, head);
                head.prev = head.next;
            } else
                head.next = new ListElement(listElement.data, head.next, head);
        } else {
            ListElement tmp = this.getElement(n - 1);
            tmp.next = new ListElement(listElement.data, tmp.next, tmp.prev);
        }
    }

    private void delElement(int n) {
        if (head == null || n < 0)
            return;
        if (n == 0) {
            if (head.next.next != head) {
                ListElement tmp = head.next;
                head.next = tmp.next;
                head.next.prev = head;
            } else {
                head.next = head;
                head.prev = head;
            }
        } else {
            ListElement tmp = this.getElement(n - 1);
            tmp.next = tmp.next.next;
            tmp.next.prev = tmp;
        }
    }

    public OfficeBuilding(int n, int... cnt) {
        if (n < 0) {
            throw new FloorIndexOutBoundsException("Error! Number should be positive!");
        }
        for (int i = 0; i < n; i++)
            this.addElement(i, new ListElement(new OfficeFloor(cnt[i]), null, null));
    }

    public OfficeBuilding(Floor... floors) {
        for (int i = 0; i < floors.length; i++)
            this.addElement(i, new ListElement(floors[i], null, null));
    }

    public int getCntFloors() {
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


    public int getCntSpaces() {
        if (head == null || head.next == head)
            return 0;
        int j = 0;
        for (int i = 0; i < this.getCntFloors(); i++)
            j += this.getElement(i).data.getCnt();
        return j;
    }


    public float getAreaSpaces() {
        if (head == null || head.next == head)
            return 0;
        float res = this.getElement(0).data.getArea();
        for (int i = 1; i < this.getCntFloors(); i++)
            res += this.getElement(i).data.getArea();
        return res;
    }


    public int getCntRooms() {
        if (head == null)
            return 0;
        int res = 0;
        for (int i = 0; i < this.getCntFloors(); i++)
            res += this.getElement(i).data.getCntRooms();
        return res;
    }


    public Floor[] getFloors() {
        if (head == null || this.getCntFloors() == 0)
            return null;
        Floor[] res = new Floor[this.getCntFloors()];
        for (int i = 0; i < this.getCntFloors(); i++)
            res[i] = this.getElement(i).data;
        return res;
    }


    public Floor getFloor(int n) {
        if (n < 0) {
            throw new FloorIndexOutBoundsException("Error! Count should be positive");
        }
        if (n > this.getCntFloors()) {
            throw new FloorIndexOutBoundsException("Error! Number was not found");
        }
        if (head == null || this.getCntFloors() == 0 || n < 0)
            return null;
        return this.getElement(n).data;
    }


    public void setFloor(int n, Floor floor) {
        if (n < 0) {
            throw new FloorIndexOutBoundsException("Error! Count should be positive");
        }
        if (n > this.getCntFloors()) {
            throw new FloorIndexOutBoundsException("Error! Number was not found");
        }
        if (head == null)
            return;
        this.getElement(n).data = floor;
    }


    public Space getSpace(int n) {
        if (n < 0) {
            throw new FloorIndexOutBoundsException("Error! Count should be positive");
        }
        if (n > this.getCntSpaces()) {
            throw new FloorIndexOutBoundsException("Error! Number was not found");
        }
        int tmp_n = n;
        for (int i = 0; i < this.getCntFloors(); i++)
            for (int j = 0; j < this.getFloor(i).getCnt(); j++)
                if ((tmp_n--) == 0)
                    return this.getFloor(i).getSpace(j);
        return null;
    }


    public void setSpace(int n, Space space) {
        if (n < 0) {
            throw new FloorIndexOutBoundsException("Error! Count should be positive");
        }
        if (n > this.getCntSpaces()) {
            throw new FloorIndexOutBoundsException("Error! Number was not found");
        }
        int tmp_n = n;
        for (int i = 0; i < this.getCntFloors(); i++)
            for (int j = 0; j < this.getFloor(i).getCnt(); j++)
                if ((tmp_n--) == 0) {
                    this.getFloor(i).setSpace(j, space);
                    return;
                }
    }


    public void addSpace(int n, Space space) {
        if (n < 0) {
            throw new FloorIndexOutBoundsException("Error! Count should be positive");
        }
        if (n > this.getCntSpaces()) {
            throw new FloorIndexOutBoundsException("Error! Number was not found");
        }
        int tmp_n = n;
        for (int i = 0; i < this.getCntFloors(); i++)
            for (int j = 0; j < this.getFloor(i).getCnt(); j++)
                if ((tmp_n--) == 0) {
                    this.getFloor(i).addSpace(j, space);
                    return;
                }
    }

    public void delSpace(int n) {
        if (n < 0) {
            throw new FloorIndexOutBoundsException("Error! Count should be positive");
        }
        if (n > this.getCntSpaces()) {
            throw new FloorIndexOutBoundsException("Error! Number was not found");
        }
        int tmp_n = n;
        for (int i = 0; i < this.getCntFloors(); i++)
            for (int j = 0; j < this.getFloor(i).getCnt(); j++)
                if ((tmp_n--) == 0) {
                    this.getFloor(i).delSpace(j);
                    return;
                }
    }

    public void addFloor(Floor floor) {
        this.addElement(this.getCntFloors(), new ListElement(floor, null, null));
    }

    public Space getBestSpace() {
        if (head == null || this.getCntSpaces() == 0)
            return null;
        Space max = this.getSpace(0);
        for (int i = 1; i < this.getCntSpaces(); i++) {
            if (max.getArea() < this.getSpace(i).getArea())
                max = this.getSpace(i);
        }
        return max;
    }

    public Space[] getSortedSpaces() {
        Space tmp[] = new Space[this.getCntSpaces()];
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
        OfficeBuilding officeBuilding = (OfficeBuilding) object;
        return (getClass() == object.getClass() && this.getCntFloors() == officeBuilding.getCntFloors() && getFloors() == officeBuilding.getFloors());
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
        OfficeBuilding cloned = (OfficeBuilding) super.clone();
        cloned.head = null;
        Floor[] tmp_floors = this.getFloors();
        for (int i = 0; i < tmp_floors.length; i++)
            cloned.addFloor((Floor) this.getFloor(i).clone());
        return cloned;
    }

    @Override
    public Iterator<Floor> iterator() {
        return new FloorIterator(new OfficeBuilding(this.getFloors()));
    }


}
