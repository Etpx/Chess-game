package ChessGame.Logic.ValidMoves;

import ChessGame.GUI.BoardPane;
import ChessGame.Logic.Game;

public class King extends MoveValidator {

    private BoardPane boardPane;

    public King(Game game) {
        super(game);
    }

    @Override
    public boolean isValidMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {

        // if the target location is possible.

        if( isTargetLocationFree() || isTargetLocationCaptured()){
            //ok
        }else{
            boardPane.setLbl("target location not free and not captureable");
            return false;
        }

        // The king moves one square in any direction, the king has also a special move which is
        // called castling and also involves a rook.

        boolean isValid = true;
        if( sourceRow+1 == targetRow && sourceColumn == targetColumn){
            //up
            isValid = true;

        }else if( sourceRow+1 == targetRow && sourceColumn+1 == targetColumn){
            //up right
            isValid = true;

        }else if( sourceRow == targetRow && sourceColumn+1 == targetColumn){
            //right
            isValid = true;

        }else if( sourceRow-1 == targetRow && sourceColumn+1 == targetColumn){
            //down right
            isValid = true;

        }else if( sourceRow-1 == targetRow && sourceColumn == targetColumn){
            //down
            isValid = true;

        }else if( sourceRow-1 == targetRow && sourceColumn-1 == targetColumn){
            //down left
            isValid = true;

        }else if( sourceRow == targetRow && sourceColumn-1 == targetColumn){
            //left
            isValid = true;

        }else if( sourceRow+1 == targetRow && sourceColumn-1 == targetColumn){
            //up left
            isValid = true;

        }else{
            boardPane.setLbl("moving too far");
            isValid = false;
        }

        // castling
        // ..

        return isValid;
    }
}
