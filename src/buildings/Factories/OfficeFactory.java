package buildings.Factories;

import buildings.Interface.Building;
import buildings.Interface.BuildingFactory;
import buildings.Interface.Floor;
import buildings.Interface.Space;
import buildings.dwelling.Office.Office;
import buildings.dwelling.Office.OfficeBuilding;
import buildings.dwelling.Office.OfficeFloor;

public class OfficeFactory implements BuildingFactory {
    @Override
    public Space createSpace(float area) {
        return new Office(area);
    }

    @Override
    public Space createSpace(int roomsCount, float area) {
        return new Office(area, roomsCount);
    }

    @Override
    public Floor createFloor(int spaceCount) {
        return new OfficeFloor(spaceCount);
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        return new OfficeFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCount) {
        return new OfficeBuilding(floorsCount, spacesCount);
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        return new OfficeBuilding(floors);
    }
}