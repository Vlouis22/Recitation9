import java.util.ArrayList;

/**
 * This class implements the game we all love to
 * not play.
 *
 * @author (M Rasamny)
 * @version (03 / 13 / 2018)
 */
public class TicTacToe {
    // instance variables
    private GamePiece[][] board;
    private GamePiece[] player;
    private int nextPlayerIndex;
    private int numOfMoves;

    public TicTacToe(GamePiece p1, GamePiece p2) {
        board = new GamePiece[3][3];
        clear();
        player = new GamePiece[2];
        player[0] = new GamePiece(p1);
        player[1] = new GamePiece(p2);
        // use % 2
        nextPlayerIndex = 0;
        numOfMoves = 0;
    }


    /**
     * Returns true if the location is an integer that represents one of the squares on the board ; false otherwise
     *
     * @param location the integer representation of the square.
     * @return true if the location is an integer that represents one of the squares on the board ; false otherwise
     */
    public boolean isValid(int location) {
        return location >=0 && location < board.length* board.length;
    }

    /**
     * Returns true if the location is NOT occupied by a game piece; false otherwise
     *
     * @param location the integer representation of the square.
     * @return true if the location is NOT occupied by a game piece; false otherwise
     */
    public boolean isEmpty(int location) {
        int row = location / board.length;
        int column = location % board.length;
        return board[row][column] == null;

    }

    /**
     * Returns the number of moves remaining on the board
     *
     * @return the number of moves remaining on the board
     */
    public int movesRemaining() {
        return board.length*board.length - numOfMoves;
    }

    /**
     * Returns the game piece at the provided location
     *
     * @param location the integer representation of the square
     * @return the game piece at the provided location
     */
    public GamePiece getPiece(int location) {
        int row = location / board.length;
        int column = location % board.length;

        GamePiece x = board[row][column];
        //return board[column][row];
        GamePiece gamepiece = new GamePiece(x);
        return gamepiece;
    }
    public GamePiece getPiece2(int location) {
        int row = location / board.length;
        int column = location % board.length;

        GamePiece x = board[row][column];
        //return board[column][row];
        //GamePiece gamepiece = new GamePiece(x);
        return x;
    }


    /**
     * Returns the winner's GamePiece or null if there is no winner at the time the method is invoked
     *
     * @return the winner's GamePiece or null if there is no winner at the time the method is invoked
     */
    public GamePiece getWinner() {
        GamePiece winner = null;
        int[][] combos = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // horizontal winning combinations
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // vertical winning combinations
                {0, 4, 8}, {2, 4, 6}             // diagonal winning combinations
        };

        ArrayList<Integer> player0 = new ArrayList<>();
        ArrayList<Integer> player1 = new ArrayList<>();


        for(int i = 0; i<=8; i++){

            if(getPiece2(i)==player[0]){
                player0.add(i);
            }
            else if(getPiece2(i)==player[1]) {
                player1.add(i);
            }
        }
        for(int [] win: combos){
            if(player0.contains(win[0]) && player0.contains(win[1]) && player0.contains(win[2])){
                winner = player[0];
            }
            else if(player1.contains(win[0]) && player1.contains(win[1]) && player1.contains(win[2])){
                winner = player[1];
        }}
        return winner;
    }

    /**
     * Indicates the current player by returning the current player's game piece
     * @return the current player's game piece.
     */
    public GamePiece getCurrentPlayer() {
        return new GamePiece(player[nextPlayerIndex]);
    }

    /**
     * Places a game piece at the provided location if and only if the location is valid and is empty.  Returns
     * true if the operation is successful; false otherwise
     * @param location the integer representation of the square.
     * @return true if it is able to place a game piece at the specified location; false otherwise
     */
    public boolean add(int location) {
        if (isValid(location) && isEmpty(location)) {
            board[location / board.length][location % board.length] = player[nextPlayerIndex];
            nextPlayerIndex = nextPlayerIndex > 0 ? 0 : 1;
            numOfMoves++;
            return true;
        }
        return false;
    }

    /**
     * Clears the board of all game pieces
     */
    public void clear() {
        for(int i = 0; i< board.length; i++){
            for(int j = 0; j<board.length; j++){
                board[i][j] = null;
            }
        }
    }

    /**
     * Returns a string representation of the board
     * @return a string representation of the board
     */

    private String separator(int row){
        String s = "";
        for(int col = 0; col < board[row].length; col++){
            s += "+---";
        }
        s += "+\n";
        return s;
    }

    @Override
    public String toString() {
        String s = "";
        for(int row = 0; row < board.length; row++){
            s += separator(row);
            for(int col = 0; col < board[row].length; col++){
                int location = row*board[row].length + col;
                s += "| ";
                if (!isEmpty(location)){
                    s += getPiece(location);
                }else{
                    s += location ;
                }
                s += " ";
            }
            s += "|\n";
        }
        s += separator(board.length-1);
        return s;
    }
}