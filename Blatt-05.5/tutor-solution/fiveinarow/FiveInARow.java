package fiveinarow;

/**
 * This class models a Five-in-a-row game.
 *
 * @author Martin Thoma
 * @version 1.2
 *
 */
public class FiveInARow {

    /** The field of the game. */
    private final Board board;

    /** Whos turn is it? */
    private Player currentPlayer;

    /** Is the game finished? */
    private boolean isFinished;

    /** Did the game end with draw? */
    private boolean isDraw;

    /** Who is the winner? */
    private Player winner;

    /**
     * This initiates an empty Five-In-A-Row game.
     * The WHITE player starts
     * @param width the width of the board
     * @param height the height of the board
     */
    public FiveInARow(int width, int height) {
        currentPlayer = Player.WHITE;
        board = new Board(width, height);
    }

    /**
     * Which token has the player?
     * @param player the player whose token you want to get
     * @return the token of player
     */
    private Token playerToToken(Player player) {
        if (player == Player.WHITE) {
            return Token.WHITE;
        } else if (player == Player.BLACK) {
            return Token.BLACK;
        } else {
            throw new IllegalArgumentException(
                    "Token has to be either WHITE or BLACK.");
        }
    }

    public void move(Player player, int x, int y) {
        board.setTile(player, x, y);
        if (areFiveInARow(player)) {
            isFinished = true;
            winner = player;
        }

        if (board.isFull()) {
            isFinished = true;
            isDraw = true;
        }

        if(currentPlayer == Player.WHITE) {
            currentPlayer = Player.BLACK;
        } else {
            currentPlayer = Player.WHITE;
        }
    }

    /**
     * This Method tests if there are five in a row of the given player.
     *
     * @param player The player to check for five in a row.
     * @return {@code true} iff there are five in a row, otherwise {@code false}
     */
    private boolean areFiveInARow(Player player) {
        return isFive(player, 1, 1) // diagonal top right
                || isFive(player, -1, 1) // diagonal top left
                || isFive(player, 1, 0) // vertical
                || isFive(player, 0, 1); // horizontal
    }

    private boolean isFive(Player player, int xDir, int yDir) {
        boolean gotFive;

        for (int x = 0; x < board.width; x++) {
            for (int y = 0; y < board.height; y++) {
                gotFive = true;
                for (int c = 0; c < 5; c++) {
                    if (!board.isOnBoard(x + c * xDir, y + c * yDir)
                            || board.getTile(x + c * xDir, y + c
                                    * yDir) != playerToToken(player)) {
                        gotFive = false;
                        break;
                    }
                }

                if (gotFive) {
                    return true;
                }
            }
        }

        return false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Player getWinner() {
        if (!isFinished) {
            throw new IllegalStateException(
                    "The game is not finished.");
        }

        return winner;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isDraw() {
        return isDraw;
    }
}
