package fiveinarow;

/**
 * This class models the shell interaction with a player of the game
 * Five-in-a-row.
 *
 * @author Martin Thoma
 * @version 1.2
 *
 */
public class Shell {
    public static FiveInARow game;

    public static void addTokenToField(Player player) {
        while (true) {
            int x = MyTerminal
                    .askInt("Enter position in x-direction: ");
            int y = MyTerminal
                    .askInt("Enter position in y-direction: ");
            if (game.getBoard().isEmpty(x, y)) {
                game.move(player, x, y);
                break;
            } else {
                System.out.println("The given Location isn't free.");
            }
        }
    }

    public static void addTokenToField(Token token) {
        while (true) {
            int x = MyTerminal
                    .askInt("Enter position in x-direction (1 is the bottom row): ");
            int y = MyTerminal
                    .askInt("Enter position in y-direction (1 is the left row): ");
            if (game.getBoard().isEmpty(x, y)) {
                game.getBoard().setTile(token, x, y);
                break;
            } else {
                System.out.println("The given Location isn't free.");
            }
        }
    }


    public static void main(String[] args) {
        Player.whiteName = MyTerminal
                .askString("Enter the first Players' name: ");
        Player.blackName = MyTerminal
                .askString("Enter the second Players' name: ");
        int height = MyTerminal
                .askInt("Enter the fields' height (as Integer): ");
        int width = MyTerminal
                .askInt("Enter the fields' width (as Integer): ");
        game = new FiveInARow(width, height);

        boolean preparingField = true;
        while (preparingField) {
            System.out.println(game.getBoard());
            int addOrStart = MyTerminal
                    .askInt("Choose what you want to do: "
                            + "1: Add Joker " + "2: Add Stopper "
                            + "3: Start Game! : ");
            switch (addOrStart) {
            case 1:
                addTokenToField(Token.JOKER);
                break;
            case 2:
                addTokenToField(Token.STOPPER);
                break;
            case 3:
                preparingField = false;
                break;
            default:
                System.out
                        .println("Invalid Input! Please enter 1, 2, or 3!");
            }
            if (game.getBoard().isFull()) {
                System.out
                        .println("Now the whole field is Full.. Start a new game!");
            }
        }

        while (true) {
            System.out.println(game.getBoard());
            System.out.println("It's your turn "
                    + Player.getName(game.getCurrentPlayer()) + ".");
            addTokenToField(game.getCurrentPlayer());
            if (game.isFinished()) {
                if (game.isDraw()) {
                    System.out
                    .println("The whole field is Full and you have a Draw! Better Start a new game!");
            break;
                }
                else {
                    System.out.println("Congratulations "
                            + game.getCurrentPlayer()
                            + "! You won the game!");
                    break;
                }

            }
        }

    }
}
