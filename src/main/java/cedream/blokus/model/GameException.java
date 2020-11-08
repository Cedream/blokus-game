package cedream.blokus.model;

/**
 * Class that handles the exception GameException.
 * @author Cédric Thonus (cedream)
 */
public class GameException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Initialize constructor from RuntimeException with a message.
     * 
     * @param m message
     */
    GameException(String m) {
        super(m);
    }

    /**
     * Initialize constructor from RuntimeException.
     */
    GameException() {
        super();
    }

}
