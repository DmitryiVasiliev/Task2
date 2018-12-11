package buildings.Exceptions;

public class InvalidSpaceAreaException extends IllegalArgumentException {
    public InvalidSpaceAreaException() {
        super();
    }

    public InvalidSpaceAreaException(String message) {
        super(message);
    }
}
