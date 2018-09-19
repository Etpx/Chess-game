package ChessGame.Logic.ValidMoves;

import ChessGame.GUI.BoardPane;
import ChessGame.Logic.Game;

public class Rook extends MoveValidator{

    private BoardPane boardPane;

    public Rook(Game game) {
        super(game);
    }

    @Override
    public boolean isValidMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {

        // The rook can move any number of squares along any rank or file, but
        // may not leap over other pieces. Along with the king, the rook is also
        // involved during the king's castling move.
        if( isTargetLocationFree() || isTargetLocationCaptured()){
            //ok
        }else{
            boardPane.setLbl("target location not free and not captureable");
            return false;
        }

        boolean isValid = false;

        // first lets check if the path to the target is straight at all
        int diffRow = targetRow - sourceRow;
        int diffColumn = targetColumn - sourceColumn;

        if( diffRow == 0 && diffColumn > 0){

            // right
            isValid = !arePiecesBetweenSourceAndTarget(sourceRow,sourceColumn,targetRow,targetColumn,0,+1);

        }else if( diffRow == 0 && diffColumn < 0){

            // left
            isValid = !arePiecesBetweenSourceAndTarget(sourceRow,sourceColumn,targetRow,targetColumn,0,-1);

        }else if( diffRow > 0 && diffColumn == 0){

            // up
            isValid = !arePiecesBetweenSourceAndTarget(sourceRow,sourceColumn,targetRow,targetColumn,+1,0);

        }else if( diffRow < 0 && diffColumn == 0){

            // down
            isValid = !arePiecesBetweenSourceAndTarget(sourceRow,sourceColumn,targetRow,targetColumn,-1,0);

        }else{

            // not moving diagonally
            boardPane.setLbl("not moving straight");
            isValid = false;
        }

        return isValid;
    }

}

