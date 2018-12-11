package buildings.Exceptions;

public class InvalidRoomsCountException extends IllegalArgumentException {
    public InvalidRoomsCountException() {
        super();
    }

    public InvalidRoomsCountException(String message) {
        super(message);
    }
}
