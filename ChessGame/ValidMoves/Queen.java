package ChessGame.Logic.ValidMoves;

import ChessGame.Logic.Game;

public class Queen extends MoveValidator {

    private Bishop bishop;
    private Rook rook;

    public Queen(Game game) {
        super(game);
    }

    @Override
    public boolean isValidMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {
        // The queen combines the power of the rook and bishop and can move any number
        // of squares along rank, file, or diagonal, but it may not leap over other pieces.

        boolean result = bishop.isValidMove(sourceRow, sourceColumn, targetRow, targetColumn);
        result |= rook.isValidMove(sourceRow, sourceColumn, targetRow, targetColumn);
        return result;
    }
}
