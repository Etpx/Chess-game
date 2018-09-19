package ChessGame.Logic;

public class MoveValidator {

    private Game Game;
    private Piece sourcePiece;
    private Piece targetPiece;

    public MoveValidator(Game Game) {
        this.Game = Game;
    }


    // Checks if the specified move is valid, return true if move is valid, false if move is invalid

    public boolean isMoveValid(int sourceRow,
                               int sourceColumn, int targetRow, int targetColumn) {

        // Declaring the Pieces
        sourcePiece = Game.getNonCapturedPieceAtLocation(sourceRow, sourceColumn);
        targetPiece = this.Game.getNonCapturedPieceAtLocation(targetRow, targetColumn);

        // if the source piece does not exist
        if (sourcePiece == null) {
            System.out.println("No source piece");
            return false;
        }

        // if the source piece has right color
        if (sourcePiece.getColor().equals(Piece.COLOR_WHITE)
                && this.Game.getGameState() == Game.GAME_STATE_WHITE) {
            // ok
        } else if (sourcePiece.getColor().equals(Piece.COLOR_BLACK)
                && this.Game.getGameState() == Game.GAME_STATE_BLACK) {
            // ok
        } else {
            System.out.println("It's not your turn");
            return false;
        }

        // check if target location within boundaries
        if (targetRow < Piece.ROW_1 || targetRow > Piece.ROW_8
                || targetColumn < Piece.COLUMN_A || targetColumn > Piece.COLUMN_H) {

            System.out.println("Target row or column out of scope");
            return false;
        }

        // Validate piece movement rules
        boolean validPieceMove = false;
        switch (sourcePiece.getType()) {

            case Piece.TYPE_BISHOP:
                validPieceMove = isValidBishopMove(sourceRow, sourceColumn, targetRow, targetColumn);
                break;

            case Piece.TYPE_KING:
                validPieceMove = isValidKingMove(sourceRow, sourceColumn, targetRow, targetColumn);
                break;

            case Piece.TYPE_KNIGHT:
                validPieceMove = isValidKnightMove(sourceRow, sourceColumn, targetRow, targetColumn);
                break;

            case Piece.TYPE_PAWN:
                validPieceMove = isValidPawnMove(sourceRow, sourceColumn, targetRow, targetColumn);
                break;

            case Piece.TYPE_QUEEN:
                validPieceMove = isValidQueenMove(sourceRow, sourceColumn, targetRow, targetColumn);
                break;

            case Piece.TYPE_ROOK:
                validPieceMove = isValidRookMove(sourceRow, sourceColumn, targetRow, targetColumn);
                break;

            default:
                break;
        }
        if (!validPieceMove) {
            return false;
        } else {
            // ok
        }




        return true;
    }

    private boolean isTargetLocationCaptured() {

     if (!targetPiece.getColor().equals(sourcePiece.getColor())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isTargetLocationFree() {
        return targetPiece == null;
    }

    private boolean isValidBishopMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {

        //The bishop can move any number of squares diagonally, but may not leap over other pieces.

        // if the target location possible
        if (isTargetLocationFree() || isTargetLocationCaptured()) {
            //ok
        } else {
            return false;
        }

        boolean isValid = false;

        // first lets check if the path to the target is diagonally at all

        int diffRow = targetRow - sourceRow;
        int diffColumn = targetColumn - sourceColumn;

        if (diffRow == diffColumn && diffColumn > 0) {
            // moving diagonally up-right
            isValid = !arePiecesBetweenSourceAndTarget(sourceRow, sourceColumn, targetRow, targetColumn, +1, +1);

        } else if (diffRow == -diffColumn && diffColumn > 0) {
            // moving diagnoally down-right
            isValid = !arePiecesBetweenSourceAndTarget(sourceRow, sourceColumn, targetRow, targetColumn, -1, +1);

        } else if (diffRow == diffColumn && diffColumn < 0) {
            // moving diagnoally down-left
            isValid = !arePiecesBetweenSourceAndTarget(sourceRow, sourceColumn, targetRow, targetColumn, -1, -1);

        } else if (diffRow == -diffColumn && diffColumn < 0) {
            // moving diagnoally up-left
            isValid = !arePiecesBetweenSourceAndTarget(sourceRow, sourceColumn, targetRow, targetColumn, +1, -1);

        } else {
            // not moving diagonally
            isValid = false;
        }
        return isValid;
    }

    private boolean isValidQueenMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {

        // The queen combines the power of the rook and bishop and can move any number
        // of squares along rank, file, or diagonal, but it may not leap over other pieces.

        boolean result = isValidBishopMove(sourceRow, sourceColumn, targetRow, targetColumn);
        result = result | isValidRookMove(sourceRow, sourceColumn, targetRow, targetColumn); // '|' is OR operator.

        return result;
    }

    private boolean isValidPawnMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {

        boolean isValid = false;

        // The pawn may move forward to the unoccupied square immediately in front
        // of it on the same file, or on its first move it may advance two squares
        // along the same file provided both squares are unoccupied

        if (isTargetLocationFree()) {
            if (sourceColumn == targetColumn) {

                if (sourcePiece.getColor().equals(Piece.COLOR_WHITE)) {

                    // white
                    if (sourceRow + 1 == targetRow) {
                        // move one up
                        isValid = true;
                    } else {
                        isValid = false;
                    }
                } else {

                    // black
                    if (sourceRow - 1 == targetRow) {
                        // move one down
                        isValid = true;
                    } else {
                        isValid = false;
                    }
                }

                // Making a condition for the pawn to move 2 tiles in the first move.
                if (sourceRow == Piece.ROW_2 || sourceRow == Piece.ROW_7) {

                    if (sourcePiece.getColor().equals(Piece.COLOR_WHITE)) {

                        if (sourceRow + 2 == targetRow || sourceRow + 1 == targetRow) {
                            isValid = true;
                        } else
                            isValid = false;
                    } else {

                        // Black
                        if (sourceRow - 2 == targetRow  || sourceRow - 1 == targetRow) {
                            isValid = true;

                        } else {
                            isValid = false;
                        }
                    }

                }
            } else {
                // not the same column
                isValid = false;
            }

            // or it may move to a square occupied by an opponent�s piece, which is diagonally in front
            // of it on an adjacent file, capturing that piece.

        } else if (isTargetLocationCaptured()) {

            if (sourceColumn + 1 == targetColumn || sourceColumn - 1 == targetColumn) {
                // one column to the right or left
                if (sourcePiece.getColor().equals(Piece.COLOR_WHITE)) {
                    // white
                    if (sourceRow + 1 == targetRow) {
                        // move one up
                        isValid = true;
                    } else {
                        isValid = false;
                    }
                } else {
                    // black
                    if (sourceRow - 1 == targetRow) {
                        // move one down
                        isValid = true;
                    } else {
                        isValid = false;
                    }
                }
            } else {
                // note one column to the left or right
                isValid = false;
            }
        }

        return isValid;
    }

    private boolean isValidKnightMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {

        // The knight moves to any of the closest squares which are not on the same rank,
        // file or diagonal, thus the move forms an "L"-shape two squares long and one
        // square wide. The knight is the only piece which can leap over other pieces.

        // target location possible?
        if (isTargetLocationFree() || isTargetLocationCaptured()) {
            //ok
        } else {
            return false;
        }

        if (sourceRow + 2 == targetRow && sourceColumn + 1 == targetColumn) {
            // move up up right
            return true;
        } else if (sourceRow + 1 == targetRow && sourceColumn + 2 == targetColumn) {
            // move up right right
            return true;
        } else if (sourceRow - 1 == targetRow && sourceColumn + 2 == targetColumn) {
            // move down right right
            return true;
        } else if (sourceRow - 2 == targetRow && sourceColumn + 1 == targetColumn) {
            // move down down right
            return true;
        } else if (sourceRow - 2 == targetRow && sourceColumn - 1 == targetColumn) {
            // move down down left
            return true;
        } else if (sourceRow - 1 == targetRow && sourceColumn - 2 == targetColumn) {
            // move down left left
            return true;
        } else if (sourceRow + 1 == targetRow && sourceColumn - 2 == targetColumn) {
            // move up left left
            return true;
        } else if (sourceRow + 2 == targetRow && sourceColumn - 1 == targetColumn) {
            // move up up left
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidKingMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {

        // target location possible?
        if (isTargetLocationFree() || isTargetLocationCaptured()) {
            //ok
        } else {
            System.out.println("target location not free and not captureable");
            return false;
        }

        // The king moves one square in any direction, the king has also a special move which is
        // called castling and also involves a rook.
        boolean isValid = true;
        if (sourceRow + 1 == targetRow && sourceColumn == targetColumn) {
            //up
            isValid = true;
        } else if (sourceRow + 1 == targetRow && sourceColumn + 1 == targetColumn) {
            //up right
            isValid = true;
        } else if (sourceRow == targetRow && sourceColumn + 1 == targetColumn) {
            //right
            isValid = true;
        } else if (sourceRow - 1 == targetRow && sourceColumn + 1 == targetColumn) {
            //down right
            isValid = true;
        } else if (sourceRow - 1 == targetRow && sourceColumn == targetColumn) {
            //down
            isValid = true;
        } else if (sourceRow - 1 == targetRow && sourceColumn - 1 == targetColumn) {
            //down left
            isValid = true;
        } else if (sourceRow == targetRow && sourceColumn - 1 == targetColumn) {
            //left
            isValid = true;
        } else if (sourceRow + 1 == targetRow && sourceColumn - 1 == targetColumn) {
            //up left
            isValid = true;
        } else {
            System.out.println("moving too far");
            isValid = false;
        }

        // castling
        // ..

        return isValid;
    }

    private boolean isValidRookMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {

        // The rook can move any number of squares along any rank or file, but
        // may not leap over other pieces. Along with the king, the rook is also
        // involved during the king's castling move.

        if (isTargetLocationFree() || isTargetLocationCaptured()) {
            //ok
        } else {
            return false;
        }

        boolean isValid = false;

        // first lets check if the path to the target is straight at all
        int diffRow = targetRow - sourceRow;
        int diffColumn = targetColumn - sourceColumn;

        if (diffRow == 0 && diffColumn > 0) {
            // right
            isValid = !arePiecesBetweenSourceAndTarget(sourceRow, sourceColumn, targetRow, targetColumn, 0, +1);

        } else if (diffRow == 0 && diffColumn < 0) {
            // left
            isValid = !arePiecesBetweenSourceAndTarget(sourceRow, sourceColumn, targetRow, targetColumn, 0, -1);

        } else if (diffRow > 0 && diffColumn == 0) {
            // up
            isValid = !arePiecesBetweenSourceAndTarget(sourceRow, sourceColumn, targetRow, targetColumn, +1, 0);

        } else if (diffRow < 0 && diffColumn == 0) {
            // down
            isValid = !arePiecesBetweenSourceAndTarget(sourceRow, sourceColumn, targetRow, targetColumn, -1, 0);

        } else {
            // not moving diagonally
            isValid = false;
        }

        return isValid;
    }


    private boolean arePiecesBetweenSourceAndTarget(int sourceRow, int sourceColumn,
                                                    int targetRow, int targetColumn, int rowIncrementPerStep, int columnIncrementPerStep) {

        int currentRow = sourceRow + rowIncrementPerStep;
        int currentColumn = sourceColumn + columnIncrementPerStep;
        while (true) {
            if (currentRow == targetRow && currentColumn == targetColumn) {
                break;
            }
            if (currentRow < Piece.ROW_1 || currentRow > Piece.ROW_8
                    || currentColumn < Piece.COLUMN_A || currentColumn > Piece.COLUMN_H) {
                break;
            }

            if (this.Game.isNonCapturedPieceAtLocation(currentRow, currentColumn)) {
                System.out.println("Pieces in between source and target");
                return true;
            }

            currentRow += rowIncrementPerStep;
            currentColumn += columnIncrementPerStep;
        }
        return false;
    }
}
