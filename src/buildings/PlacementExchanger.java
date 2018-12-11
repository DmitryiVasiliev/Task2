package buildings;

import buildings.Exceptions.InexchangeableFloorsException;
import buildings.Exceptions.InexchangeableSpacesException;
import buildings.Interface.Building;
import buildings.Interface.Floor;
import buildings.Interface.Space;

public class PlacementExchanger {
    public static boolean spaceExchangeTest(Space space, Space space1) {
        return (space.getArea() == space1.getArea() && space.getRooms() == space1.getRooms());

    }

    public static boolean floorExchangeTest(Floor floor, Floor floor1) {
        return (floor.getArea() == floor1.getArea() && floor.getCntRooms() == floor1.getCntRooms());
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException {
        if (!spaceExchangeTest(floor1.getSpace(index1), floor2.getSpace(index2)))
            throw new InexchangeableSpacesException("Error! The spaces can not be exchanged");
        Space tmp = floor1.getSpace(index1);
        floor1.setSpace(index1, floor2.getSpace(index2));
        floor2.setSpace(index2, tmp);

    }
    public static void exchangeBuildingFloors(Building building1,int index1,Building building2,int index2) throws InexchangeableFloorsException {
        if(!floorExchangeTest(building1.getFloor(index1),building2.getFloor(index2)))
            throw new InexchangeableFloorsException("Error! The floors can not be exchanged");
        Floor tmp = building1.getFloor(index1);
        building1.setFloor(index1,building2.getFloor(index2));
        building2.setFloor(index2,tmp);
    }
}
