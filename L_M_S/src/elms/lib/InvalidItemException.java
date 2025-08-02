package elms.lib;

public class InvalidItemException extends Exception {
    public InvalidItemException(String itemId) {
        super(itemId + " is not a valid event id.");
    }
}