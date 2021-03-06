package buildings.dwelling.Dwelling.Hotel;

import buildings.Interface.Space;
import buildings.dwelling.Dwelling.DwellingFloor;

public class HotelFloor extends DwellingFloor {
    private int stars;
    private static final int DEFAULT_STARS = 1;

    public HotelFloor(int cnt) {
        super(cnt);
        this.stars = DEFAULT_STARS;
    }

    public HotelFloor(Space... spaces) {
        super(spaces);
        this.stars = DEFAULT_STARS;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getSimpleName() + " (" + this.getStars() + ", " + this.getCnt());
        for (int i = 0; i < this.getCnt(); i++) {
            stringBuilder.append(", ");
            stringBuilder.append(this.getSpace(i).toString());
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof HotelFloor) {
            HotelFloor hotelFloor = (HotelFloor) object;
            return (getClass() == object.getClass() && this.getCnt() == hotelFloor.getCnt() && getSpaces() == hotelFloor.getSpaces() && this.getStars() == hotelFloor.getStars());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = this.getCnt();
        for (int i = 0; i < this.getCnt(); i++) {
            hash ^= this.getSpace(i).hashCode();
            hash ^= this.getStars();
        }
        return hash;
    }
}
