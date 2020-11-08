package cedream.blokus.model;

/**
 * Enumeration containing the different color of pieces.
 * @author Cédric Thonus (cedream)
 */
public enum PieceColor {
    BLUE("Bleu"),
    GREEN("Vert"),
    YELLOW("Jaune"),
    RED("Rouge");

    private String color;

    /**
     * Initialize attribute color.
     * @param color
     */
    PieceColor(String color) {
        this.color = color;
    }

    /**
     * Get color in format text.
     * @return color text
     */
    public String getColorText() {
        return color;
    }

}
