package ChessGame.Logic.ValidMoves;

import ChessGame.Logic.Game;
import ChessGame.GUI.BoardPane;

public class Bishop extends MoveValidator {

    private static BoardPane boardPane;

    public Bishop(Game game) {
        super(game);
    }

    @Override
    public boolean isValidMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {

        //The bishop can move any number of squares diagonally, but may not leap
        //over other pieces.

        // if the target location possible.
        if( isTargetLocationFree() || isTargetLocationCaptured()){
            //ok
        }else{
            boardPane.setLbl("Target location is not free and can not be captured");
            return false;
        }

        boolean isValid = false;

        // first lets check if the path to the target is diagonally at all
        int diffRow = targetRow - sourceRow;
        int diffColumn = targetColumn - sourceColumn;

        if( diffRow == diffColumn && diffColumn > 0){

            // moving diagonally up-right
            isValid = !super.arePiecesBetweenSourceAndTarget(sourceRow, sourceColumn, targetRow, targetColumn,+1,+1);

        }else if( diffRow == -diffColumn && diffColumn > 0){

            // moving diagnoally down-right
            isValid = !super.arePiecesBetweenSourceAndTarget(sourceRow, sourceColumn, targetRow, targetColumn,-1,+1);

        }else if( diffRow == diffColumn && diffColumn < 0){

            // moving diagnoally down-left
            isValid = !super.arePiecesBetweenSourceAndTarget(sourceRow, sourceColumn, targetRow, targetColumn,-1,-1);

        }else if( diffRow==-diffColumn && diffColumn < 0){

            // moving diagnoally up-left
            isValid = !super.arePiecesBetweenSourceAndTarget(sourceRow, sourceColumn, targetRow, targetColumn,+1,-1);

        }else{
            // Not moving diagonally
            boardPane.setLbl("Not moving diagonally");
            isValid = false;
        }
        return isValid;
    }
}
