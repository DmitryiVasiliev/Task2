package buildings.Factories;

import buildings.Interface.Building;
import buildings.Interface.BuildingFactory;
import buildings.Interface.Floor;
import buildings.Interface.Space;
import buildings.dwelling.Dwelling.Flat;
import buildings.dwelling.Dwelling.Hotel.Hotel;
import buildings.dwelling.Dwelling.Hotel.HotelFloor;

public class HotelFactory implements BuildingFactory {
    @Override
    public Space createSpace(float area) {
        return new Flat(area);
    }

    @Override
    public Space createSpace(int roomsCount, float area) {
        return new Flat(area, roomsCount);
    }

    @Override
    public Floor createFloor(int spaceCount) {
        return new HotelFloor(spaceCount);
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        return new HotelFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCount) {
        return new Hotel(floorsCount, spacesCount);
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        return new Hotel(floors);
    }
}