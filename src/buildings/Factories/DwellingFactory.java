package buildings.Factories;

import buildings.Interface.Building;
import buildings.Interface.BuildingFactory;
import buildings.Interface.Floor;
import buildings.Interface.Space;
import buildings.dwelling.Dwelling.Dwelling;
import buildings.dwelling.Dwelling.DwellingFloor;
import buildings.dwelling.Dwelling.Flat;

public class DwellingFactory implements BuildingFactory {
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
        return new DwellingFloor(spaceCount);
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        return new DwellingFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCount) {
        return new Dwelling(floorsCount, spacesCount);
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        return new Dwelling(floors);
    }
}
