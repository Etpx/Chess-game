package ChessGame.Logic.ValidMoves;

import ChessGame.GUI.BoardPane;
import ChessGame.Logic.Game;
import ChessGame.Logic.Piece;

public class Pawn extends MoveValidator{

    private BoardPane boardPane;

    public Pawn(Game game) {
        super(game);
    }

    @Override
    public boolean isValidMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {

        // The pawn may move forward to the unoccupied square immediately in front
        // of it on the same file, or on its first move it may advance two squares
        // along the same file provided both squares are unoccupied

        boolean isValid = false;

        if( super.isTargetLocationFree() ){
            if(sourceColumn == targetColumn){
                // same column

                if(super.sourcePiece.getColor().equals(Piece.COLOR_WHITE)){
                    // white
                    if( sourceRow + 1 == targetRow ){
                        // move one up
                        isValid = true;
                    }else{
                        boardPane.setLbl("not moving one up");
                        isValid = false;
                    }
                }else{
                    // black
                    if( sourceRow-1 == targetRow ){
                        // move one down
                        isValid = true;
                    }else{
                        boardPane.setLbl("not moving one down");
                        isValid =  false;
                    }
                }
            }else{
                // not the same column
                boardPane.setLbl("Not staying in same column");
                isValid = false;
            }

            // or it may move
            // to a square occupied by an opponent�s piece, which is diagonally in front
            // of it on an adjacent file, capturing that piece.
        }else if( super.isTargetLocationCaptured() ){

            if( sourceColumn + 1 == targetColumn || sourceColumn - 1 == targetColumn){
                // one column to the right or left
                if(super.sourcePiece.getColor().equals(Piece.COLOR_WHITE) ){
                    // white
                    if( sourceRow + 1 == targetRow ){
                        // move one up
                        isValid = true;

                    }else{
                        boardPane.setLbl("not moving one up");
                        isValid = false;
                    }
                }else{
                    // black
                    if( sourceRow - 1 == targetRow ){
                        // move one down
                        isValid = true;
                    }else{
                        boardPane.setLbl("not moving one down");
                        isValid = false;
                    }
                }
            }else{
                // note one column to the left or right
                boardPane.setLbl("not moving one column to left or right");
                isValid = false;
            }
        }

        // on its first move it may advance two squares
        // ..

        // The pawn has two special
        // moves, the en passant capture, and pawn promotion.

        // en passant
        // ..

        return isValid;

    }
}
