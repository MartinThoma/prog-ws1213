package fiveinarow;

public enum Player {
    WHITE {
        @Override
        public String toString() {
            return "w";
        }
    },
    BLACK {
        @Override
        public String toString() {
            return "b";
        }
    };

    /** Name of the white player */
    public static String whiteName;

    /** Name of the black player */
    public static String blackName;

    public static String getName(Player player) {
        if (player == WHITE) {
            return whiteName;
        } else {
            return blackName;
        }
    }
}
