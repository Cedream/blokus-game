package cedream.blokus.model;

import java.util.Arrays;

/**
 * Enumeration containing the differents types of pieces.
 * @author Cédric Thonus (cedream)
 */
public enum PieceType {
    ONE(new boolean[][] {
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, true, false, false},
            {false, false, false, false, false},
            {false, false, false, false, false}
    }),
    TWO(new boolean[][] {
            {false, false, false, false, false},
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, false, false, false, false},
            {false, false, false, false, false}
    }),
    THREE(new boolean[][] {
            {false, false, false, false, false},
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, false, false, false, false}
    }),
    FOUR(new boolean[][] {
            {false, false, false, false, false},
            {false, false, true, false, false},
            {false, false, true, true, false},
            {false, false, false, false, false},
            {false, false, false, false, false}
    }),
    FIVE(new boolean[][] {
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, false, false, false, false}
    }),
    SIX(new boolean[][] {
            {false, false, false, false, false},
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, true, true, false, false},
            {false, false, false, false, false}
    }),
    SEVEN(new boolean[][] {
            {false, false, false, false, false},
            {false, false, true, false, false},
            {false, false, true, true, false},
            {false, false, true, false, false},
            {false, false, false, false, false}
    }),
    EIGHT(new boolean[][] {
            {false, false, false, false, false},
            {false, true, true, false, false},
            {false, true, true, false, false},
            {false, false, false, false, false},
            {false, false, false, false, false}
    }),
    NINE(new boolean[][] {
            {false, false, false, false, false},
            {false, true, true, false, false},
            {false, false, true, true, false},
            {false, false, false, false, false},
            {false, false, false, false, false}
    }),
    TEN(new boolean[][] {
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, false, true, false, false}
    }),
    ELEVEN(new boolean[][] {
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, true, true, false, false},
            {false, false, false, false, false}
    }),
    TWELVE(new boolean[][] {
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, true, true, false, false},
            {false, true, false, false, false},
            {false, false, false, false, false}
    }),
    THIRTEEN(new boolean[][] {
            {false, false, false, false, false},
            {false, false, true, false, false},
            {false, false, true, true, false},
            {false, false, true, true, false},
            {false, false, false, false, false}
    }),
    FOURTEEN(new boolean[][] {
            {false, false, false, false, false},
            {false, false, true, true, false},
            {false, false, true, false, false},
            {false, false, true, true, false},
            {false, false, false, false, false}
    }),
    FIFTEN(new boolean[][] {
            {false, false, false, false, false},
            {false, false, true, false, false},
            {false, false, true, true, false},
            {false, false, true, false, false},
            {false, false, true, false, false}
    }),
    SIXTEEN(new boolean[][] {
            {false, false, false, false, false},
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, true, true, true, false},
            {false, false, false, false, false}
    }),
    SEVENTEEN(new boolean[][] {
            {false, false, true, false, false},
            {false, false, true, false, false},
            {false, false, true, true, true},
            {false, false, false, false, false},
            {false, false, false, false, false}
    }),
    EIGHTTEEN(new boolean[][] {
            {false, false, false, false, false},
            {false, true, true, false, false},
            {false, false, true, true, false},
            {false, false, false, true, false},
            {false, false, false, false, false}
    }),
    NINETEEN(new boolean[][] {
            {false, false, false, false, false},
            {false, true, false, false, false},
            {false, true, true, true, false},
            {false, false, false, true, false},
            {false, false, false, false, false}
    }),
    TWENTY(new boolean[][] {
            {false, false, false, false, false},
            {false, true, false, false, false},
            {false, true, true, true, false},
            {false, false, true, false, false},
            {false, false, false, false, false}
    }),
    TWENTYONE(new boolean[][] {
            {false, false, false, false, false},
            {false, false, true, false, false},
            {false, true, true, true, false},
            {false, false, true, false, false},
            {false, false, false, false, false}
    }),
    TWENTYTWO(new boolean[][] {
            {false, true, true, false, false},
            {false, true, true, false, false},
            {false, true, true, false, false},
            {false, false, false, false, false},
            {false, false, false, false, false}
    });

    private boolean[][] pattern;

    /**
     * Initialize pattern.
     * @param pattern
     */
    PieceType(boolean[][] pattern) {
        this.pattern = pattern;
    }

    /**
     * Get a copy of pattern of piece.
     * @return copy of pattern of piece
     */
    boolean[][] getPattern() {
        boolean[][] res = new boolean[pattern.length][pattern[0].length];
        for (int i = 0; i < pattern.length; i++) {
            res[i] = Arrays.copyOf(pattern[i], pattern[i].length);
        }
        return res;
    }

}
