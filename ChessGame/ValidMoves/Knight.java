package ChessGame.Logic.ValidMoves;

import ChessGame.GUI.BoardPane;
import ChessGame.Logic.Game;

public class Knight extends MoveValidator {

    private BoardPane boardPane;

    public Knight(Game game) {
        super(game);
    }

    @Override
    public boolean isValidMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {

        // The knight moves to any of the closest squares which are not on the same rank,
        // file or diagonal, thus the move forms an "L"-shape two squares long and one
        // square wide. The knight is the only piece which can leap over other pieces.

        // target location possible?
        if( isTargetLocationFree() || isTargetLocationCaptured()){
            //ok
        }else{
            boardPane.setLbl("target location not free and not captureable");
            return false;
        }

        if( sourceRow+2 == targetRow && sourceColumn+1 == targetColumn){
            // move up up right
            return true;

        }else if( sourceRow+1 == targetRow && sourceColumn+2 == targetColumn){
            // move up right right
            return true;

        }else if( sourceRow-1 == targetRow && sourceColumn+2 == targetColumn){
            // move down right right
            return true;

        }else if( sourceRow-2 == targetRow && sourceColumn+1 == targetColumn){
            // move down down right
            return true;

        }else if( sourceRow-2 == targetRow && sourceColumn-1 == targetColumn){
            // move down down left
            return true;

        }else if( sourceRow-1 == targetRow && sourceColumn-2 == targetColumn){
            // move down left left
            return true;

        }else if( sourceRow+1 == targetRow && sourceColumn-2 == targetColumn){
            // move up left left
            return true;

        }else if( sourceRow+2 == targetRow && sourceColumn-1 == targetColumn){
            // move up up left
            return true;

        }else{
            return false;
        }
    }
}
