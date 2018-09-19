package ChessGame.Logic;

import java.util.ArrayList;
import java.util.List;

// This class is for the internal representation of the game itself with the game state and all the pieces.
public class Game {

    // Sets the default game state. (Can be changed later after the turn ends).
    private int gameState = GAME_STATE_WHITE;

    // The game state variables is used to know whom's turn it is.
    public static final int GAME_STATE_WHITE = 0;
    public static final int GAME_STATE_BLACK = 1;
    public static final int GAME_STATE_END = 2;


    // List to store the pieces.
    private List<Piece> pieces = new ArrayList<Piece>();

    // Declaring MoveValidator object.
    private MoveValidator moveValidator;

    // Starts the game.
    public Game() {

        // Creating the object of MoveValidator class.
        this.moveValidator = new MoveValidator(this);

        // create and place white pieces
        createPiece(Piece.COLOR_WHITE, Piece.TYPE_ROOK, Piece.ROW_1, Piece.COLUMN_A);
        createPiece(Piece.COLOR_WHITE, Piece.TYPE_KNIGHT, Piece.ROW_1, Piece.COLUMN_B);
        createPiece(Piece.COLOR_WHITE, Piece.TYPE_BISHOP, Piece.ROW_1, Piece.COLUMN_C);
        createPiece(Piece.COLOR_WHITE, Piece.TYPE_QUEEN, Piece.ROW_1, Piece.COLUMN_D);
        createPiece(Piece.COLOR_WHITE, Piece.TYPE_KING, Piece.ROW_1, Piece.COLUMN_E);
        createPiece(Piece.COLOR_WHITE, Piece.TYPE_BISHOP, Piece.ROW_1, Piece.COLUMN_F);
        createPiece(Piece.COLOR_WHITE, Piece.TYPE_KNIGHT, Piece.ROW_1, Piece.COLUMN_G);
        createPiece(Piece.COLOR_WHITE, Piece.TYPE_ROOK, Piece.ROW_1, Piece.COLUMN_H);

        // for loop for pawns
        int currentColumn = Piece.COLUMN_A;
        for (int i = 0; i < 8; i++) {
            createPiece(Piece.COLOR_WHITE, Piece.TYPE_PAWN, Piece.ROW_2, currentColumn);
            currentColumn++;
        }

        // create and place black pieces
        createPiece(Piece.COLOR_BLACK, Piece.TYPE_ROOK, Piece.ROW_8, Piece.COLUMN_A);
        createPiece(Piece.COLOR_BLACK, Piece.TYPE_KNIGHT, Piece.ROW_8, Piece.COLUMN_B);
        createPiece(Piece.COLOR_BLACK, Piece.TYPE_BISHOP, Piece.ROW_8, Piece.COLUMN_C);
        createPiece(Piece.COLOR_BLACK, Piece.TYPE_QUEEN, Piece.ROW_8, Piece.COLUMN_D);
        createPiece(Piece.COLOR_BLACK, Piece.TYPE_KING, Piece.ROW_8, Piece.COLUMN_E);
        createPiece(Piece.COLOR_BLACK, Piece.TYPE_BISHOP, Piece.ROW_8, Piece.COLUMN_F);
        createPiece(Piece.COLOR_BLACK, Piece.TYPE_KNIGHT, Piece.ROW_8, Piece.COLUMN_G);
        createPiece(Piece.COLOR_BLACK, Piece.TYPE_ROOK, Piece.ROW_8, Piece.COLUMN_H);

        // for loop for pawns
        currentColumn = Piece.COLUMN_A;
        for (int i = 0; i < 8; i++) {
            createPiece(Piece.COLOR_BLACK, Piece.TYPE_PAWN, Piece.ROW_7, currentColumn);
            currentColumn++;
        }

    }

    // this method is for creating pieces.
    private void createPiece(String color, String type, int row, int column) {

        Piece piece = new Piece(color, type, row, column); // new Piece Object
        this.pieces.add(piece); // Add the Object to the ArrayList
    }


    public boolean movePiece(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {

        // if the piece moved an invalid move
        if (!this.moveValidator.isMoveValid(sourceRow, sourceColumn, targetRow,
                targetColumn)) {
            System.out.println("Invalid Move");
            return false;
        }

        // save the current piece into a variable.
        Piece piece = getNonCapturedPieceAtLocation(sourceRow, sourceColumn);

        // Now we tell the computer what is the color of the opponent based on the current player.
        String opponentColor = (piece.getColor().equals(Piece.COLOR_BLACK) ? Piece.COLOR_WHITE
                : Piece.COLOR_BLACK);

        // if the target location has an opponent piece capture it.
        if (isNonCapturedPieceAtLocation(opponentColor, targetRow, targetColumn)) {
            Piece opponentPiece = getNonCapturedPieceAtLocation(targetRow, targetColumn);

            opponentPiece.isCaptured(true);
        }

        // Move the piece to the new location.
        piece.setRow(targetRow);
        piece.setColumn(targetColumn);

        // Now check if the game ends or not.
        if (isGameEndConditionReached()) {
            this.gameState = GAME_STATE_END;
            System.out.println(piece.getColor() + " Wins!");
        } else
            this.changeGameState();

        return true;
    }

    // Method to check if the game ends or not.
    private boolean isGameEndConditionReached() {

        // look into each piece and see if the king has been captured or not.
        for (Piece piece : this.pieces) {
            if (piece.getType().equals(Piece.TYPE_KING) && piece.isCaptured())
                return true;
        }

        return false;
    }


    // gets the current state.
    public int getGameState() {
        return gameState;
    }

    // this method is used to change the state.
    public void changeGameState() {

        // check if game end condition has been reached

        if (this.isGameEndConditionReached()) {

            this.gameState = Game.GAME_STATE_END;
            return;
        }

        switch (this.gameState) {
            case GAME_STATE_BLACK:
                this.gameState = GAME_STATE_WHITE;
                break;

            case GAME_STATE_WHITE:
                this.gameState = GAME_STATE_BLACK;
                break;

            case GAME_STATE_END:
                break;

            default:
                throw new IllegalStateException("Unknown game state: " + this.gameState);
        }

    }

    // Checks whether the specified location has a piece or not.
    public boolean isNonCapturedPieceAtLocation(int row, int column) {
        for (Piece piece : this.pieces) {
            if (piece.getRow() == row && piece.getColumn() == column
                    && piece.isCaptured() == false) {
                return true;
            }
        }
        return false;
    }

     // Checks whether the specified location has a piece or not. If it does, get that piece.
    public Piece getNonCapturedPieceAtLocation(int row, int column) {

        for (Piece piece : this.pieces) {
            if( piece.getRow() == row
                    && piece.getColumn() == column
                    && piece.isCaptured() == false ){
                return piece;
            }
        }
        return null;
    }

    // Checks whether the specified location has a piece of a specified color or not.
    private boolean isNonCapturedPieceAtLocation(String color, int row, int column) {

        for (Piece piece : this.pieces) {
            if( piece.getRow() == row
                    && piece.getColumn() == column
                    && piece.isCaptured() == false
                    && piece.getColor().equals(color)){
                return true;
            }
        }
        return false;
    }

    // This method is for getting the pieces.
    public List<Piece> getPieces() {
        return pieces;
    }



}