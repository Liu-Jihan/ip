package oscarl;
/**
 * Represents a custom exception specific to the OscarL application.
 */

public class OscarLException extends Exception {

    /**
     * Constructs a new OscarLException with the specified detail message.
     *
     * @param message The detail message describing the exception.
     */

    public OscarLException(String message) {
        super(message);
    }
}
